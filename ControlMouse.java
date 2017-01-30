import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Nathan on 04/01/2017.
 */
public class ControlMouse implements MouseListener, MouseMotionListener {

    public Vue fen;
    public Modele m;
    boolean carteJ=false;
    int indexJlb;
    private boolean discardC;
    private boolean effet=false;
    private boolean effetPiocher;
    private boolean effetTourEnPlus;

    public ControlMouse(Vue fen, Modele m) {
        this.fen=fen;
        this.m=m;
        discardC=false;
        effet=false;
        effetPiocher=false;
        effetTourEnPlus=false;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        for (JLabelV2 jlbv2 : fen.listCarteDansLaMain){
           if (e.getSource()==jlbv2){
               indexJlb=jlbv2.getId();

               if (m.p.bannirUneCarteDansLaMain==true){
                   if (!m.joueurActuel().getHand().get(indexJlb).getNom().equals("Mystic")
                           && !m.joueurActuel().getHand().get(indexJlb).getNom().equals("Heavy-Infantry")
                           && !m.joueurActuel().getHand().get(indexJlb).getNom().equals("Militia")
                           && !m.joueurActuel().getHand().get(indexJlb).getNom().equals("Apprentice")){
                       m.p.deck.add(m.joueurActuel().getHand().get(indexJlb));
                   }
                   m.joueurActuel().getHand().remove(indexJlb);
                   fen.actualiserJ(m.joueurActuel(),m);
                   carteJ=true;
                   m.p.bannirUneCarteDansLaMain=false;

               }
               else{
                    if (m.p.discardACard==true){
                        discardC=true;
                    }else{
                        if (m.carteJouer(jlbv2.getId()).getType().equals("Constructeur")){
                            fen.creerConstructeur();
                        }
                        m.joueurActuel().jouerUneCarte(m.carteJouer(jlbv2.getId()), m.p);
                        fen.actualiserJ(m.joueurActuel(), m);
                        carteJ = true;

                    }
               }
           }
       }
        if (carteJ==true){
            fen.jouerUneCarte(indexJlb);
            carteJ=false;
        }

        if(discardC==true) {
            m.joueurActuel().getDefausse().add(m.joueurActuel().getHand().get(indexJlb));
            m.joueurActuel().getHand().remove(indexJlb);
            m.joueurActuel().piocher(2);
            fen.creationMainJoueur();
            fen.creationTapis();
            fen.actualiserJ(m.joueurActuel(),m);
            m.p.discardACard=false;
            discardC=false;
        }

            for (int i = 0; i < fen.tableauCarteCentrale.length; i++) {
                if (e.getSource() == fen.tableauCarteCentrale[i]) {

                    m.toucherUnecarte(i);
                    fen.actualiserLigneCentrale(i);
                    fen.actualiserJ(m.joueurActuel(), m);
                    fen.actualiserMain(m.joueurActuel(),m);
                }
            }


        if (e.getSource()==fen.carteMystique){
            m.acquerirCarteMystic();
            fen.actualiserJ(m.joueurActuel(),m);


        }
        if (e.getSource()==fen.carteInfanteriesLourdes){
            m.acquerirCarteHeavyInfantry();
            fen.actualiserJ(m.joueurActuel(),m);


        }
        if (e.getSource()==fen.carteCultiste){
            m.tuerLeCultiste();
            fen.actualiserJ(m.joueurActuel(),m);
        }

        if (m.p.copyLesEffets==true){
            fen.creationCopierDesEffets();

        }
        for (JLabelV2 jbv2 : fen.listCarteSurLeTapis){
            if (e.getSource()==fen.carteTapis && m.p.copyLesEffets==true){
                indexJlb=jbv2.getId();
                System.out.println("Id " + indexJlb);
                m.joueurActuel().jouerUneCarteUneCopie(m.joueurActuel().getTapis().get(indexJlb),m.p);
                fen.actualiserJ(m.joueurActuel(), m);
                effet=true;
                m.p.copyLesEffets=false;
            }
        }

        if (effet==true){
            fen.finDeLaCopie();
            fen.creationTapis();
            fen.creationMainJoueur();
            effet=false;
            fen.invalidate();
            fen.repaint();
            fen.validate();
        }




        for (JLabelV2 jbv2 : fen.listeCarteConstructeur){
            if (e.getSource()==fen.carteConstruct){
                indexJlb=jbv2.getId();
                if ( m.joueurActuel().getConstruct().get(indexJlb).getEffet()!=26 &&
                        m.joueurActuel().getConstruct().get(indexJlb).getEffet()!=27 &&
                        m.joueurActuel().getConstruct().get(indexJlb).getEffet()!=40 &&
                        m.joueurActuel().getConstruct().get(indexJlb).getEffet()!=41
                        ){
                    m.joueurActuel().getConstruct().get(indexJlb).effetDeCarte(m.joueurActuel().getConstruct().get(indexJlb).getEffet(),m.joueurActuel(), m.p);

                }


                if (m.p.piocherUneCarteConstructeur==true){
                        m.joueurActuel().piocher(1);
                        effetPiocher=true;
                    m.joueurActuel().consrtructPiocher=false;

                        m.p.piocherUneCarteConstructeur=false;
                    }else if (m.p.tourEnPlus==true){
                        m.p.getNeant().add(m.joueurActuel().getConstruct().get(indexJlb));
                        effetTourEnPlus=true;

                    }else if (m.joueurActuel().getConstruct().get(indexJlb).getEffet()==27){
                        m.convertir4Runes();
                }
                fen.carteConstruct.removeMouseListener(fen.ctrm);

            }
            fen.actualiserJ(m.joueurActuel(),m);

        }

        if (effetPiocher==true){
            fen.creationMainJoueur();
            effetPiocher=false;
            fen.invalidate();
            fen.repaint();
            fen.validate();
        }

        if (effetTourEnPlus==true){
            m.joueurActuel().getConstruct().remove(indexJlb);
            fen.creerConstructeur();
            fen.invalidate();
            fen.repaint();
            fen.validate();
            effetTourEnPlus=false;

        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
