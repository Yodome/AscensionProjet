
/**
 * Created by Nathan on 01/12/2016.
 */
public class ControlGroup {
    public Modele m;
    public Vue fen;
    public ControlButton ctrb;
    public ControlMouse ctrm;

    public ControlGroup(Modele m) {
        this.m = m;
        fen = new Vue(m);
        fen.display();
    }

    public void finDeLaPartie() {
        fen.finDeLaPartie();
    }
}
