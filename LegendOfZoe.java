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
        int[] sortie = {0, 0};

        // Boucle pour chaque tour
        while (!(zoe.hasLost() || zoe.hasWon())) {
            // Verifier pour la géneration de nouveau niveau
            // Zoe doit avoir l'hexaforce du niveau et être sur la sortie
            if (zoe.getNbrHexaforce() == niveauActuel && zoe.getX() == sortie[0] && zoe.getY == sortie[1]) {
                niveauActuel++;
                niveau = new Niveau(niveauActuel, zoe);
                sortie = niveau.getExit();
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
