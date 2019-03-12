/**
 * Cette classe donne une défintion plus précise du bloc, avec des caractéristques
 * d'un personnage du jeu ayant des propriétés propres à lui .
 */
abstract class Personnage extends Bloc{
    /**
     * Elle représente la sante du Personnage au cours du jeu
     */
    protected int healthPts;


    /**
     * Constructeur de la classe Personnage
     *
     * @param healthPts
     * @param xCoordinate
     * @param yCoordinate
     * @param bloc
     */
    public Personnage(int healthPts, int xCoordinate, int yCoordinate, char bloc){
        super(xCoordinate,yCoordinate,bloc);
        this.healthPts = healthPts;
    }

    /**
     * Getter pour retourner les points de vie du Personnage
     *
     * @return
     */
    public int getHealthPts(){
        return healthPts;
    }

    /**
     * Setter pour modifier la santé du Personnage
     *
     * @param newHealthPts
     */
    public void setHealthPts(int newHealthPts){
        if(healthPts < 0)
        {
            this.healthPts = 0;
        }else {
            this.healthPts = newHealthPts;
        }
    }

    /**
     * Méthode qui permet d'attaquer un autre Personnage
     *
     * @param autre
     */
    public void attack(Personnage autre){
        autre.setHealthPts((int) (autre.getHealthPts() - 1));
    }

    /**
     * Elle permet de déplacer le joueur
     */
    public void move(){

    }

}