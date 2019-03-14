public class Niveau {

    private Bloc[] grille = new Bloc[LevelGenerator.LARGEUR * LevelGenerator.HAUTEUR];
    private int stage;

    private int entrX, entrY;
    private int exitX, exitY;

    public Niveau(int stage) {
        this.stage = stage;
        this.getGrille();
    }

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
                    caseX = (int) itemParts[2];
                    caseY = (int) itemParts[3];
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = new Tresor(caseX, caseY, this.stage, itemParts[1]);
                    break;
                case "monstre":
                    caseX = (int) itemParts[2];
                    caseY = (int) itemParts[3];
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = new Monstre(caseX, caseY, this.stage, itemParts[1]);
                    break;
                case "sortie":
                    this.exitX = (int) itemParts[1];
                    this.exitY = (int) itemParts[2];
                    this.grille[this.exitY * LevelGenerator.HAUTEUR + this.exitX] = new Sortie(this.exitX, this.exitY);
                    break;
                case "zoe":
                    this.entrX = (int) itemParts[1];
                    this.entrY = (int) itemParts[2];
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
     * des coordonnées fournis.
     *
     * @param int x Coordonnée des x
     * @param int y Coordonnée des y
     * @return Bloc[] Les blocs adjacents aux coordonnées
     */
    public Bloc[] voisinage(int x, int y) {
        Bloc[] voisinage = new Bloc[8] // Voisinage de 3*3 - centre

        for (int i = 0; i < this.grille.length(); i++) {
            Bloc bloc = this.grille[i];
            if (bloc != null) {
                if (Math.abs(bloc.getX() - x) == 1 && Math.abs(bloc.getY() - y) == 1) {
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
}
