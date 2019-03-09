/**
 * Cette classe donne une défintion plus précise du bloc , avec des caractéristques
 * d'un personnage du jeu ayant des propriétés propres à lui .
 */
abstract class Personnage extends Bloc{
    /**
     * Elle représente la sante du Personnage au cours du jeu
     */
    protected int healthPts;
    /**
     * Elle représente la santé maximale que peut avoir un Personnage
     * au cours de la partie
     */
    protected double maxHp;
    /**
     * Elle représente les points d'attaque du Personnage à chaque étape
     * du jeu
     */
    protected double damages;


    /**
     * @param healthPts
     * @param damages
     * @param xCoordinate
     * @param yCoordinate
     * @param bloc
     */
    public Personnage(int healthPts,double damages, int xCoordinate, int yCoordinate, char bloc){
        super(xCoordinate,yCoordinate,bloc);
        this.healthPts = healthPts;
        this.maxHp = healthPts;
        this.damages = damages;
    }

    /**
     * @return
     */
    public int getHealthPts(){
        return healthPts;
    }

    /**
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
     * @return
     */
    public double getMaxHp(){
        return maxHp;
    }

    /**
     * @return
     */
    public double getDamages(){
        return damages;
    }

    /**
     * @param newDamages
     */
    public void setDamages(double newDamages){
        this.damages = newDamages;
    }

    /**
     * @param autre
     */
    public void attaquer(Personnage autre){
        autre.setHealthPts((int) (autre.getHealthPts() - this.damages));
    }

    /**
     * 
     */
    public void move(){

    };

}