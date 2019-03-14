public class Zoe extends Personnage {

    private int hexaforce = 0;

    private static final int ZOE_MAX_VIES = 5;
    private static final int ZOE_DAMAGE = 1;

    /**
     * Déterminer si Zoe à gagné toutes les niveaux.
     * Il y a 6 niveaux et chaqu'un donne une morceau
     * du hexaforce.
     *
     * @return Si Zoe à toutes les pièces de l'Hexaforce
     */
    public boolean hasWon() {
        return this.hexaforce == 6;
    }

    /**
     * Constructeur de la classe Zoe
     *
     * @param x
     * @param y
     */
    public Zoe(int x, int y) {
        super(this.ZOE_MAX_VIES, x, y, '&');
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;

        int[] entree = this.niveau.getEntrance();
        this.x = entree[0];
        this.y = entree[1];
    }

    /**
     * Donner un effet sur Zoe grace à un item
     * donnée soit par un trésor ou monstre.
     *
     * @param item L'item obtenue
     */
    public void affecterItem(String item) {
        switch (item) {
            case "coeur":
                this.enleverVie(-1); // L'effet d'en ajouter
                break;
            case "potionvie":
                this.setHealthPts(this.ZOE_MAX_VIES);
                break;
            case "hexaforce":
                this.hexaforce++;
                break;
        }
    }

    /**
     * Creuser les murs adjacents à Zoe
     */
    public void creuser() {
        Bloc[] blocs = this.niveau.voisinage(this);

        for (Bloc bloc : blocs) {
            if (bloc instanceof Mur) {
                int[] coords = {bloc.getX(), bloc.getY()};
                this.niveau.detruireMur(coords);
            }
        }
    }

    /**
     * Donner un (1) point de dommage aux monstres
     * dans le voisinage de Zoe
     */
    public void attaquerRegion() {
        Bloc[] blocs = this.niveau.voisinage(this);

        for (Bloc bloc : blocs) {
            if (bloc instanceof Personnage) {
                (Personnage) bloc.attaquer(this.ZOE_DAMAGE, this);
            }
        }
    }

    /*
     * Ouvrir trésors dans le voisinage et d'en affecter Zoe
     */
    public void ouvrirTresor() {
        Bloc[] blocs = this.niveau.voisinage(this);

        for (Bloc bloc : blocs) {
            if (bloc instanceof Tresor) {
                (Tresor) bloc.ouvrir(this);
            }
        }
    }
}
