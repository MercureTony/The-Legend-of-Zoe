/**
 * Cette classe donne une défintion plus précise du bloc , avec des caractéristques
 * d'un personnage du jeu ayant des propriétés propres à lui .
 */
abstract class Personnage extends Bloc{
    /**
     * Elle représente la sante du Personnage au cours du jeu
     */
    private int healthPts;

    /**
     * Elle représente la santé maximale que peut avoir un Personnage
     * au cours de la partie
     */
    private double maxHp;

    /**
     * Elle représente les points d'attaque du Personnage à chaque étape
     * du jeu
     */
    private double damages;

    /**
     * Coordonnée sur l'axe des abscisses du bloc
     */
    private int x;

    /**
     * Coordomnée sur l'axe des ordonnées du bloc
     */
    private  int y;

    /**
     * @param healthPts
     * @param maxHp
     * @param damages
     */
    public Personnage(int healthPts,double maxHp,double damages){
        super(x,y);
        this.healthPts = healthPts;
        this.maxHp = maxHp;
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
        this.healthPts = newHealthPts;
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

}