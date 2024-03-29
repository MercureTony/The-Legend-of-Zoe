/**
 * Cette classe est la classe mère de toutes les autres classes du personnage
 * En effet, elle englobe les méthode de base du personnage.
 */
abstract class Bloc {

    /**
     * Coordonnée sur l'axe des abscisses du Personnage
     */
    protected int x;

    /**
     * Coordomnée sur l'axe des ordonnées du Personnage
     */
    protected int y;

    /**
     * Représentation du Personnage
     */
    protected char bloc;

    /**
     * Booléen qui determine si un personnage peut passer sur le bloc
     */
    protected boolean passable;


    public Bloc(int x, int y, char bloc, boolean passable) {
        this.x = x;
        this.y = y;
        this.bloc = bloc;
        this.passable = passable;
    }

    /**
     * Méthode qui permet de retourner la valeur de l'abscisse
     *
     * @return
     */
    public int getX() { return this.x; }

    /**
     * Méthode qui permet de retourner la valeur de l'ordonnée
     *
     * @return
     */
    public int getY() { return this.y; }

    /**
     * Méthode qui change la coordonné des x par une valeur
     *
     * @param dx
     */
    public void deplacerX(int dx) { this.x += dx; }

    /**
     * Méthode qui change la coordonné des y par une valeur
     *
     * @param dy
     */
    public void deplacerY(int dy) { this.y += dy; }

    /**
     * Méthode qui permet d'afficher une réprésentation textuelle
     * du bloc
     *
     * @return
     */
    public char affichage() { return this.bloc; }

    /**
     * Méthode qui permet de retourner l'état d'un bloc (s'il est
     * passable)
     *
     * @return
     */
    public boolean estPassable() { return this.passable; }
}
