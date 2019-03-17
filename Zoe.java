public class Zoe extends Personnage {

    private int hexaforce = 0;

    protected static final int ZOE_MAX_VIES = 5;
    protected static final int ZOE_DAMAGE = 1;
    public static final int MAX_HX = 6;

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
     * @return nombre de pieces Hexaforce
     */
    public int getNbrHexaforce() { return this.hexaforce; }

    /**
     * Constructeur de la classe Zoe
     *
     * @param x
     * @param y
     * @oaram niveau
     */
    public Zoe(int x, int y, Niveau niveau) {
        super(Zoe.ZOE_MAX_VIES, x, y, '&', niveau);
        this.hexaforce = niveau.getStage() - 1;
    }

    /**
     * Donner un effet sur Zoe grace à un item
     * donnée soit par un trésor ou monstre.
     *
     * @param item L'item obtenue
     */
    public void affecterItem(String item) {
        System.out.println("Item : " + item);
        switch (item) {
            case "coeur":
                if (this.getHealthPts() != this.ZOE_MAX_VIES) {
                    // Pas plus que le max!
                    this.enleverVie(-1); // L'effet d'en ajouter
                }
                break;
            case "potionvie":
                if (this.getHealthPts() != this.ZOE_MAX_VIES) {
                    this.setHealthPts(this.ZOE_MAX_VIES);
                }
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
            if (bloc instanceof Monstre) {
                Monstre m = (Monstre) bloc;
                this.attaquer(this.ZOE_DAMAGE, m);
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
                Tresor t = (Tresor) bloc;
                t.ouvrir(this);
            }
        }
    }
}
