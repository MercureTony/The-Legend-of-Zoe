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
     *
     * Constructeur de la classe Personnage
     *
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
     *
     * Getter pour retourner les points de vie du Personnage
     *
     * @return
     */
    public int getHealthPts(){
        return healthPts;
    }

    /**
     *
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
     * @return
     */
    public double getMaxHp(){
        return maxHp;
    }

    /**
     *
     * Méthoode qui permet de modifier la santé max du Personnage
     *
     * @return
     */
    public void setMaxHp(){

    }

    /**
     *
     * Méthode qui permet de retourner les points d'attaque du Personnage
     *
     * @return
     */
    public double getDamages(){
        return damages;
    }

    /**
     *
     * Méthode qui permet de changer les points d'attaque du Personnage
     *
     * @param newDamages
     */
    public void setDamages(double newDamages){
        this.damages = newDamages;
    }

    /**
     *
     * Méthode qui permet d'attaquer un autre Personnage
     *
     * @param autre
     */
    public void attack(Personnage autre){
        autre.setHealthPts((int) (autre.getHealthPts() - this.damages));
    }

    /**
     * Elle permet de déplacer le joueur
     */
    public void move(){

    }

}