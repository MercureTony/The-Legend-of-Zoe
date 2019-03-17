public class Niveau {

    private Bloc[] grille = new Bloc[LevelGenerator.LARGEUR * LevelGenerator.HAUTEUR];
    private Zoe zoe;

    private int stage;

    private int exitX, exitY;

    public Niveau(int stage) {
        this.stage = stage;
    }

    /**
     * @return La stage du niveau
     */
    public int getStage() { return this.stage; }

    /*
     * Convertir la grille auto-générée en objets
     * Puis d'applatir la grille en tableau 1D
     */
    public void creerGrille() {
        // Prendre niveau pré-génerée
        Paire<boolean[][], String[]> generee = LevelGenerator.generateLevel(this.getStage());

        // Créer les objects Mur
        boolean[][] murs = generee.getKey();
        for (int y = 0; y < murs.length; y++) {
            for (int x = 0; x < murs[0].length; x++) {
                if (murs[y][x]) {
                    this.grille[y * LevelGenerator.HAUTEUR + x] = new Mur(x, y);
                }
            }
        }

        // Créer les autres objets
        String[] items = generee.getValue();

        for (String item : items) {
            String[] itemParts = item.split(":");

            int caseX, caseY;

            switch (itemParts[0]) {
                case "tresor":
                    caseX = Integer.parseInt(itemParts[2]);
                    caseY = Integer.parseInt(itemParts[3]);
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = new Tresor(caseX, caseY, itemParts[1]);
                    break;
                case "monstre":
                    caseX = Integer.parseInt(itemParts[2]);
                    caseY = Integer.parseInt(itemParts[3]);
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = new Monstre(
                        caseX, caseY, this, itemParts[1]);
                    break;
                case "sortie":
                    this.exitX = Integer.parseInt(itemParts[1]);
                    this.exitY = Integer.parseInt(itemParts[2]);
                    this.grille[this.exitY * LevelGenerator.HAUTEUR + this.exitX] = new Sortie(this.exitX, this.exitY);
                    break;
                case "zoe":
                    caseX = Integer.parseInt(itemParts[1]);
                    caseY = Integer.parseInt(itemParts[2]);
                    this.zoe = new Zoe(caseX, caseY, this);
                    this.grille[caseY * LevelGenerator.HAUTEUR + caseX] = this.zoe;
                    break;
            }
        }
    }

    /**
     * @return Prendre coordonnées de la sortie
     */
    public int[] getExit() {
        int[] coord = {this.exitX, this.exitY};
        return coord;
    }

    /**
     * @return Zoe
     */
    public Zoe getZoe() { return this.zoe; }

    /**
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
                if (blocChar != '\u0000') { // Null 0x0000
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
        Bloc[] voisinage = new Bloc[8]; // Voisinage de 3*3 - centre

        int j = 0;
        for (int i = 0; i < this.grille.length; i++) {
            Bloc bloc = this.grille[i];
            if (bloc != null) {
                if (Math.abs(bloc.getX() - p.getX()) <= 1 && Math.abs(bloc.getY() - p.getY()) <= 1) {
                    voisinage[j] = bloc;
                    j++;
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
        // Si en-dehors de la carte
        if (x < 0 || y < 0) { return false; }
        else if (x >= LevelGenerator.LARGEUR || y >= LevelGenerator.HAUTEUR) {
            return false;
        }

        // Si bloc actif
        for (Bloc bloc : this.grille) {
            if (bloc != null && bloc.getX() == x && bloc.getY() == y) {
                // Blocs non-actives
                if (bloc instanceof Tresor) {
                    Tresor t = (Tresor) bloc;
                    return t.estOuvert();
                }
                else if (bloc instanceof Monstre) {
                    Monstre m = (Monstre) bloc;
                    return m.estMort();
                }
                else if (bloc instanceof Sortie) { return true; }
                    return false;
            }
        }
        return true;
    }
}
