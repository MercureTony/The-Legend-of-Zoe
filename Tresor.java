public class Tresor extends Bloc {

    private String item;

    /*
     * Constructeur de trésor
     * Chaque trésor commence fermé avec un item
     * à disposition.
     *
     * @param int x Position x
     * @param int y Position y
     * @param String item L'effet du trésor sur Zoe
     */
    public Tresor(int x, int y, String item) {
        super(x, y, '$');
        this.item = item;
    }

    /*
     * Ouvrir le coffre
     * Rends le trésor inutilisable
     *
     * @param zoe Zoe à affecter
     */
    public void ouvrir(Zoe zoe) {
        this.bloc = '_';
        zoe.affecterItem(this.item);
    }
}
