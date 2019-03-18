/**
 * Classe Mur
 *
 * Bloc impassable qui peut se faire creuser
 * par Zoe.
 */
public class Mur extends Bloc {
    
    /**
     * Constructeur
     *
     * @param x Position x
     * @param y Position y
     */
    public Mur(int x, int y) {
        // Spécification de '#' comme caractère
        super(x, y, '#', false);
    }
}
