public class Monstre extends Personnage {

    private String item;
    private int difficulte;
    
    /**
     * Constructeur du monstre
     *
     * @param x
     * @param y
     * @param niveau
     * @param item
     */
    public Monstre(int x, int y, Niveau niveau, String item) {
        super((int) Math.max(0.6 * niveau.getStage(), 1.0), x, y, niveau, '@');
        this.item = item;
    }

    @Override
    private void die(Zoe zoe) {
        this.bloc = 'x';
        zoe.affecterItem(this.item);
    }

    /**
     * Approcher d'un autre personnage
     *
     * @param target Personnage où se rendre
     */
    public void approcher(Personnage target) {
        int dx, dy;
        // Axe des x
        if (target.getX() < this.getX()) {
            dx = -1;
        } else if (this.getX() < target.getX()) {
            dx = 1;
        }

        // Axe des y
        if (target.getY() < this.getY()) {
            dy = -1;
        } else if (this.getY() < target.getY()) {
            dy = 1;
        }

        this.deplacer(dx, dy);
    }
}
