public class Monstre extends Personnage {

    private String item;
    private int difficulte;
    
    public Monstre(int x, int y, String item, int difficulte) {
        super((int) Math.max(0.6 * difficulte, 1.0), x, y, '@');
        this.item = item;
        this.difficulte = difficulte;
    }

    public void endommager(int damage, Zoe zoe) {
        this.enleverVie(damage);

        if (this.estMort()) { this.die(zoe); }
    }

    private void die(Zoe zoe) {
        this.bloc = 'x';
        zoe.affecterItem(this.item);
    }
}
