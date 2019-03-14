/**
 * Classe principale du programme.
 *
 * @author UYENDE EDZANG Anthony Montand
 * @author BEAULÉ Étienne
 */
public class LegendOfZoe {

    public static void main(String[] args) {

        Messages.afficherIntro();

        Zoe zoe = new Zoe(0, 0);
        int niveauActuel = 0;
        Niveau niveau;

        // Boucle pour chaque tour
        while (!(zoe.hasLost() || zoe.hasWon())) {
            // Verifier pour la géneration de nouveau niveau
            if (zoe.getNbrHexaforce() == niveauActuel) {
                niveauActuel++;
                niveau = new Niveau(niveauActuel, zoe);
            }

            // Scanner...
        }

        if (zoe.hasLost()) {
            Messages.afficherDefaite();
        } else if (zoe.hasWon()) {
            Messages.afficherVictoire();
        }
    }
}
