/**
 * Classe trésor
 *
 * Les trésors sont placés autour de chaque niveau
 * avec un item qui affecte Zoe, avec effet quand ouvert.
 */
public class Tresor extends Bloc {

    private String item;
    private Boolean ouvert = false;

    /**
     * Constructeur de trésor
     * Chaque trésor commence fermé avec un item
     * à disposition.
     *
     * @param int x Position x
     * @param int y Position y
     * @param String item L'effet du trésor sur Zoe
     */
    public Tresor(int x, int y, String item) {
        super(x, y, '$', false);
        this.item = item;
    }

    /**
     * Ouvrir le coffre
     * Rends le trésor inutilisable
     *
     * @param zoe Zoe à affecter
     */
    public void ouvrir(Zoe zoe) {
        this.bloc = '_';
        this.ouvert = true;
        this.passable = true;
        zoe.affecterItem(this.item);
    }

    public Boolean estOuvert() {
        return this.ouvert;
    }
}
