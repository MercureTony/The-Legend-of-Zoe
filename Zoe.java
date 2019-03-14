public class Zoe extends Personnage {
    private int hexaforce = 0;

    /*
     * Déterminer si Zoe à gagné toutes les niveaux.
     * Il y a 6b niveaux et chaqu'un donne une morceau
     * du hexaforce.
     *
     * @return Boolean Si Zoe à toutes les pièces de l'Hexaforce
     */
    public boolean hasWon() {
        return this.hexaforce == 6;
    }

    /*
     * Déterminer si Zoe est morte. Elle l'est quand elle n'a
     * plus de vies (attqué par les monstres)
     *
     * @return boolean Si Zoe n'a plus de vies
     */
    public boolean hasLost() {
        return this.healthPts <= 0;
    }

    /**
     * Constructeur de la classe Personnage
     *
     * @param x
     * @param y
     */
    public Zoe(int x, int y) {
        super(x, y, '&');
        this.healthPts = 5;
    }

    /*
     * Donner un effet sur Zoe grace à un item
     * donnée soit par un trésor ou monstre.
     *
     * @param item L'item obtenue
     */
    public void affecterItem(String item) {
        switch (item) {
            case "coeur":
                this.enleverVie(-1);
                break;
            case "potionvie":
                this.setHealthPts(5); // Max de Zoe: 5
                break;
            case "hexaforce":
                this.hexaforce++;
                break;
        }
    }
}
