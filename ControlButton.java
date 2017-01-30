/**
 * Created by Nathan on 01/12/2016.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import static java.lang.System.exit;

/**
 * Created by user on 20/05/2016.
 */
public class ControlButton implements ActionListener {
    public Modele m;
    public Vue fen;
    private boolean constructAfficher;
    private boolean defausseAfiicher;


    public ControlButton(Vue fen, Modele m) {
        this.m = m;
        this.fen = fen;
        constructAfficher=false;
        defausseAfiicher=false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (m.p.finDePartie==true){
            fen.finDeLaPartie();
        }

        if (e.getSource() == fen.btnNeant) {

        }
        if (e.getSource() == fen.btnPasser) {
            if (m.p.tourEnPlus==true){
                m.p.tourEnPlus=false;
                m.tourEnPlus();
                fen.creationMainJoueur();
                fen.creationTapis();
                fen.creerConstructeur();
                m.debutTour();
                fen.actualiserJ(m.joueurActuel(),m);
                fen.actualiserTour(m);


            }else {
                m.finDuTour();

                m.passerTour();
                fen.creationMainJoueur();
                fen.creationTapis();
                m.debutTour();
                fen.actualiserJ(m.joueurActuel(),m);
                fen.actualiserTour(m);
                if (m.p.finDePartie==true){
                    fen.finDeLaPartie();
                }

            }

            m.joueurActuel().convertir=true;
            m.joueurActuel().consrtructPiocher=true;

        }
        if (e.getSource() == fen.btnDefausse) {
            if (defausseAfiicher==true){
                fen.OffDefausse();
                defausseAfiicher=false;
            }else{
                fen.afficherDefausse();
                constructAfficher=false;
                defausseAfiicher=true;

            }
        }

        if (e.getSource()==fen.btnDeConstruct){
            if (constructAfficher==true){
                fen.OffConstruct();
                constructAfficher=false;
            }else{
                fen.afficherConstruct();
                defausseAfiicher=false;
                constructAfficher=true;

            }

        }


        if (e.getSource()==fen.jbuttonJouer){
            fen.panelMenu.removeAll();
            fen.creerLesJoueurs(m);
        }

        if (e.getSource()==fen.jbuttonQuitter){

            exit(0);
        }

        if (e.getSource()==fen.jbuttAddJoueur){
                if (m.p.listeDeJoueurs.size()==2) {
                    m.p.ajouterUnJoueurJ(m.joueur3);
                    m.initialiserJ(m.joueur3);
                    fen.creerUnJenPlus();


                }else if (m.p.listeDeJoueurs.size()==3) {
                    m.p.ajouterUnJoueurJ(m.joueur4);
                    m.initialiserJ(m.joueur4);

                    fen.creerUnJenPlus();

                }

        }

    }

}