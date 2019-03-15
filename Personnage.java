/**
 * Cette classe donne une défintion plus précise du bloc, avec des caractéristques
 * d'un personnage du jeu ayant des propriétés propres à lui .
 */
abstract class Personnage extends Bloc {
    /**
     * Elle représente la sante du Personnage au cours du jeu
     */
    protected int healthPts;

    protected Niveau niveau;

    /**
     * Constructeur de la classe Personnage
     *
     * @param healthPts
     * @param x
     * @param y
     * @param bloc
     * @param niveau
     */
    public Personnage(int healthPts, int x, int y, char bloc, Niveau niveau) {
        super(x, y, bloc);
        this.healthPts = healthPts;
        this.niveau = niveau;
    }

    /**
     * Getter pour retourner les points de vie du Personnage
     *
     * @return
     */
    public int getHealthPts() {
        return this.healthPts;
    }

    /**
     * Setter pour modifier la santé du Personnage
     *
     * @param newHealthPts
     */
    public void setHealthPts(int newHealthPts) {
        this.healthPts = newHealthPts;
    }

    /**
     * Déterminer si le personnage est mort. Il l'est quand il n'a
     * plus de vies (attqué par un autre)
     *
     * @return boolean Si le personnage n'a plus de vies
     */
    public boolean estMort() {
        return this.healthPts <= 0;
    }

    /*
     * Enlèver des vies au personnage
     *
     * @param dommages Le montant de dommages
     */
    public void enleverVie(int dommages) {
        this.healthPts -= dommages;
    }

    /**
     * Méthode qui permet d'attaquer un autre Personnage
     *
     * @param damage Les points à enlèver
     * @param target Le personnage à affecter
     */
    public void attaquer(int damage, Personnage target) {
        target.endommager(damage, this);
    }

    /**
     * Procédure quand un personnage est endommagé
     *
     * @param damage Les points à enlèver
     * @param source Le personnage résponse
     */
    public void endommager(int damage, Personnage source) {
        // Ne fais rien si déjà mort
        if (this.estMort()) { return; }

        this.enleverVie(damage);

        if (this.estMort()) { this.die(source); }
    }

    /**
     * Procédure quand le personnage meurt
     *
     * @param source Le personnage résponsable
     */
    protected void die(Personnage source) {}

    /**
     * Elle permet de déplacer le personnage
     *
     * @param dx Déplacement dans l'axe des x
     * @param dy Déplacement dans l'axe des y
     */
    public void deplacer(int dx, int dy) {
        if (this.niveau.checkVide(this.getX() + dx, this.getY() + dy)) {
            this.deplacerX(dx); this.deplacerY(dy);
        }
    }
}
