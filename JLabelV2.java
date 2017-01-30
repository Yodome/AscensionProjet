import javax.swing.*;

/**
 * Created by Nathan on 04/01/2017.
 */
public class JLabelV2 extends JLabel {

    public int idJlabel;

    public JLabelV2(Icon icon, int idJlabel){
        super(icon);
        this.idJlabel=idJlabel;
    }

    public JLabelV2(String nom,int idJlabel) {
        super(nom);
        this.idJlabel=idJlabel;

    }

    public int getId() {
        return idJlabel;
    }
}
