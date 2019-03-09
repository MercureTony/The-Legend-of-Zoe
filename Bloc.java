/**
 * Cette classe est la classe mère de toutes les autres classes du personnage
 * En effet , elle englobe les méthode de base du personnage .
 */
abstract class Bloc {

    protected int xCoordinate;

    protected int yCoordinate;

    protected char bloc;


    public Bloc(int xCoordinate , int yCoordinate , char bloc){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Méthode qui permet de retourner la valeur de l'abscisse
     *
     * @return
     */
    public int getX(){
        return xCoordinate;
    }

    /**
     * Méthode qui permet de retourner la valeur de l'ordonnée
     *
     * @return
     */
    public int getY(){
        return yCoordinate;
    }

    /**
     * Méthode qui change la valeur de l'abscisse
     *
     * @param x
     */
    public void setX(int x){
        this.xCoordinate = x;
    }

    /**
     * Méthode qui change la valeur de l'ordonnée
     *
     * @param y
     */
    public void setY(int y){
        this.yCoordinate = y;
    }

    /**
     * Méthode qui permet d'afficher le Bloc
     *
     * @return
     */
    public char affichage(){
        return this.bloc;
    }
}