/**
 *
 */
abstract class Bloc {
    /**
     * Coordonnée sur l'axe des abscisses du bloc
     */
    private int xCoordinate;
    /**
     * Coordomnée sur l'axe des ordonnées du bloc
     */
    private  int yCoordinate;


    public Bloc(int xCoordinate , int yCoordinate){
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
    public String affichage(){
        return "";
    }
}