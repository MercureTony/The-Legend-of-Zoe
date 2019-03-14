/**
 * Classe principale du programme.
 *
 * @author UYENDE EDZANG Anthony Montand
 * @author BEAULÉ Étienne
 */

import java.util.Scanner;

public class LegendOfZoe {

    public static void main(String[] args) {

        Messages.afficherIntro();

        Scanner scanner = new Scanner(System.in);

        Zoe zoe = new Zoe(0, 0);
        int niveauActuel = 0;
        Niveau niveau;
        int[] sortie = {0, 0};

        // Boucle pour chaque tour
        while (!(zoe.estMort() || zoe.hasWon())) {
            // Verifier pour la géneration de nouveau niveau
            // Zoe doit avoir l'hexaforce du niveau et être sur la sortie
            if (zoe.getNbrHexaforce() == niveauActuel && zoe.getX() == sortie[0] && zoe.getY == sortie[1]) {
                niveauActuel++;
                niveau = new Niveau(niveauActuel);
                sortie = niveau.getExit();
                zoe.setNiveau(niveau);
            }

            niveau.affichage();

            String actions = scanner.nextLine();

            for (char ch: actions.toCharArray()) {
                // Tour de Zoe

                switch (ch) {
                    case 'w':
                        zoe.deplacer(0, 1);
                        break;
                    case 'a':
                        zoe.deplacer(-1, 0);
                        break;
                    case 's':
                        zoe.deplacer(0, -1);
                        break;
                    case 'd':
                        zoe.deplacer(1, 0);
                        break;
                    case 'c':
                        zoe.creuser();
                        break;
                    case 'x':
                        zoe.attaquerRegion();
                        break;
                    case 'o':
                        zoe.ouvrirTresor();
                        break;
                    case 'q':
                        System.exit(0);
                }

                // Tours des monstres en vie
                Monstre[] monstres = niveau.getMonstres();
                monstreTour: for (Monstre monstre : monstres) {
                    if (!monstre.estMort()) {

                        // Attaquer Zoe si assez près
                        Bloc[] voisinage = niveau.voisinage(monstre);
                        for (Bloc bloc : voisinage) {
                            if (bloc instanceof Zoe) {
                                monstre.attaquer((int) Math.max(0.4 * niveau.getStage(), 1.0), bloc);
                                continue monstreTour;
                            }
                        }

                        // Approcher sinon
                        monstre.approcher(zoe);
                    }
                }
            }
        }

        if (zoe.hasLost()) {
            Messages.afficherDefaite();
        } else if (zoe.hasWon()) {
            Messages.afficherVictoire();
        }
    }
}
