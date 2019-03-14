public class Niveau {

    private Bloc[] grille = new Bloc[LevelGenerator.LARGEUR * LevelGenerator.HAUTEUR];
    private int stage;

    private int entrX, entrY;
    private int exitX, exitY;

    public Niveau(int stage) {
        this.stage = stage;
        this.getGrille();
    }

    /**
     * @return La stage du niveau
     */
    public int getStage() { return this.stage; }

    /*
     * Convertir la grille auto-générée en objets
     * Puis d'applatir la grille en tableau 1D
     */
    private void getGrille() {
        // Prendre niveau pré-génerée
        Paire<boolean[][], String[]> generee = LevelGenerator.generateLevel(this.stage);

        // Créer les objects Mur
        boolean[][] murs = generee.getKey();
        for (int y = 0; y < murs.length(), y++) {
            for (int x = 0; x < murs[0].length(), x++) {
                if (murs[y][x]) {
                    this.grille[y * LevelGenerator.HAUTEUR + x] = new Mur(x, y);
                }
            }
        }

        // Créer les autres objets
        String[] items = generee.getValue();

        for (String item : items) {
            String[] itemParts = item.split(":");

            switch (itemParts[0]) {
                case "tresor":
                    int caseX = Integer.parseInt(itemParts[2]);
                    int caseY = Integer.parseInt(itemParts[3]);
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = new Tresor(
                        caseX, caseY, this.stage, itemParts[1]);
                    break;
                case "monstre":
                    int caseX = Integer.parseInt(itemParts[2]);
                    int caseY = Integer.parseInt(itemParts[3]);
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = new Monstre(
                        caseX, caseY, this.stage, itemParts[1], niveau);
                    break;
                case "sortie":
                    this.exitX = Integer.parseInt(itemParts[1]);
                    this.exitY = Integer.parseInt(itemParts[2]);
                    this.grille[this.exitY * LevelGenerator.HAUTEUR + this.exitX] = new Sortie(this.exitX, this.exitY);
                    break;
                case "zoe":
                    this.entrX = Integer.parseInt(itemParts[1]);
                    this.entrY = Integer.parseInt(itemParts[2]);
                    break;
            }
        }
    }

    public int[] getExit() {
        return {this.exitX, this.exitY};
    }

    public int[] getEntrance() {
        return {this.entrX, this.exitY};
    }

    /*
     * Imprime le niveau dans la console
     */
    public void affichage() {

        // Créer tableau 2D pour tous les blocs
        char[][] textGrille = new char[LevelGenerator.HAUTEUR][LevelGenerator.LARGEUR];

        for (Bloc bloc : this.grille) {
            if (bloc != null) {
                textGrille[bloc.getY()][bloc.getX()] = bloc.affichage();
            }
        }

        // Convertir en tableau 1D de Strings (& les imprimer)
        for (char[] rangee : textGrille) {
            String concat = "";
            for (char blocChar : rangee) {
                if (blocChar != null) {
                    concat += blocChar;
                } else {
                    concat += " ";
                }
            }
            System.out.println(concat);
        }
    }

    /*
     * Prendre toutes les blocs dans le voisinage de 1-près
     * du personnage.
     *
     * @param Personnage le personnage à examiner
     * @return Bloc[] Les blocs adjacents aux coordonnées
     */
    public Bloc[] voisinage(Personnage p) {
        Bloc[] voisinage = new Bloc[8] // Voisinage de 3*3 - centre

        for (int i = 0; i < this.grille.length(); i++) {
            Bloc bloc = this.grille[i];
            if (bloc != null) {
                if (Math.abs(bloc.getX() - p.getX()) == 1 && Math.abs(bloc.getY() - p.getY()) == 1) {
                    voisinage[i] = bloc;
                }
            }
        }
        return voisinage;
    }

    /*
     * Détruire les murs dans la grille
     * L'objet Zoe décide quel bloc
     *
     * Vue que les murs ne déplacent pas, on peut calculer la position
     * dans le tableau de blocs (this.grille) à partir des coordonnées
     *
     * @param int[] mur à enlèver (format {x, y})
     */
    public void detruireMur(int[] mur) {
        this.grille[mur[1] * LevelGenerator.HAUTEUR + mur[0]] = null;
    }

    /*
     * Prendre tout les monstres du niveau
     *
     * @return Monstre[] Les monstres
     */
    public Monstre[] getMonstres() {
        Monstre[] monstres = new Monstre[(int) (2 * Math.sqrt(this.stage))];

        int i = 0;
        for (Bloc bloc : this.grille) {
            if (bloc instanceof Monstre) {
                monstres[i] = (Monstre) bloc;
                i++;
            }
        }
        return monstres;
    }

    /**
     * Verifier si une case particulière est vide
     *
     * @param x Axe des x
     * @param y Axe des y
     * @return Disponibilité
     */
    public Boolean checkVide(int x, int y) {
        for (Bloc bloc : this.grille) {
            if (bloc.getX() == x && bloc.getY() == y) {
                return false;
            }
        }
        return true;
    }
}
