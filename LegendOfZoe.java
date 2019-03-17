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

        Niveau niveau = new Niveau(1);
        niveau.creerGrille();
        Zoe zoe = niveau.getZoe();
        int[] sortie = niveau.getExit();

        // Boucle pour chaque tour
        while (!(zoe.estMort() || zoe.hasWon())) {
            // Verifier pour la géneration de nouveau niveau
            // Zoe doit avoir l'hexaforce du niveau et être sur la sortie
            if (zoe.getNbrHexaforce() == niveau.getStage() &&
                (zoe.getX() == sortie[0] && zoe.getY() == sortie[1])) {

                niveau = new Niveau(niveau.getStage() + 1);
                niveau.creerGrille();
                sortie = niveau.getExit();
                zoe = niveau.getZoe();
            }

            // Afficher la vie restante en symbole
            String vie =  "";
            for (int i = 0; i < zoe.getHealthPts(); i++){
                // Affiche ♥, qui est textuellement le nombre de vie de Zoé
                vie += "\u2665";
            }
            for (int i = 0; i < (zoe.MAX_VIES - zoe.getHealthPts()); i++){
                // Affiche ♡
                vie += "\u2661";
            }

            // Afficher les hexaforces possédés en Unicode
            String hx =  "";
            for (int i = 0; i < zoe.getNbrHexaforce(); i++){
                // Affiche ▲, qui représente le nombre d'hexaforce que possède Zoé
                hx += "\u25B2";
            }
            for (int i = 0; i < (zoe.MAX_HX- zoe.getNbrHexaforce()); i++){
                // Affiche △
                hx += "\u25B3";
            }

            System.out.println("Vies : " + vie + " ; HF : " + hx);
            niveau.affichage();

            String actions = scanner.nextLine();

            for (char ch: actions.toCharArray()) {
                // Tour de Zoe

                switch (ch) {
                    case 'w':
                        zoe.deplacer(0, -1);
                        break;
                    case 'a':
                        zoe.deplacer(-1, 0);
                        break;
                    case 's':
                        zoe.deplacer(0, 1);
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
                                monstre.attaquer((int) Math.max(0.4 * niveau.getStage(), 1.0), (Zoe) bloc,"Le monstre a attaque Zoe!");
                                continue monstreTour;
                            }
                        }

                        // Approcher sinon
                        monstre.approcher(zoe);
                    }
                }
            }
        }

        if (zoe.estMort()) {
            Messages.afficherDefaite();
        } else if (zoe.hasWon()) {
            Messages.afficherVictoire();
        }
    }
}
