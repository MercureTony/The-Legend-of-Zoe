public class Niveau {

    private Bloc[] grille = new Bloc[LevelGenerator.LARGEUR * LevelGenerator.HAUTEUR];
    private int stage;
    private Zoe zoe;

    private int exitX, exitY;

    public Niveau(int stage, Zoe zoe) {
        this.stage = stage;
        this.zoe = zoe;
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
                    this.zoe.setPosition((int) itemParts[1], (int) itemParts[2]);
                    break;
            }
        }
    }

    public int[] getExit() {
        return {this.exitX, this.exitY};
    }
}
