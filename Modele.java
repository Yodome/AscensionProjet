
import javax.swing.*;

/**
 * Created by Nathan on 01/12/2016.
 */
public class Modele extends JPanel {
    Joueur joueur2;
    Plateau p;
    Joueur joueur;
    Joueur joueur3;
    Joueur joueur4;
    int aQuiDeJouer;


    public Modele() {
        p = new Plateau();
        joueur = new Joueur("j1");
        joueur2 = new Joueur("j2");

        joueur3 = new Joueur("j3");
        joueur4 = new Joueur("j4");
        p.listeDeJoueurs.add(joueur);
        p.listeDeJoueurs.add(joueur2);
        p.listeDeJoueurs.add(joueur3);
//        p.listeDeJoueurs.add(joueur4);
        p.quiCommence(p.listeDeJoueurs);
        aQuiDeJouer = 0;
        joueur.piocherMain();
        joueur2.piocherMain();
        joueur3.piocherMain();
//        joueur4.piocherMain();
        p.creerLespointsDHonneur();
        setLayout(null);
    }


    public Joueur joueurActuel() {
        return p.listeDeJoueurs.get(aQuiDeJouer);
    }


    public void finDuTour() {

        joueurActuel().finDuTour();
        p.acquerirHeroGratuitement = false;
        p.bannirCarteCentrale = false;
        p.discardACard = false;
        p.bannirUneCarteDansLaMain = false;
    }

    public void passerTour() {
        if (aQuiDeJouer >= p.listeDeJoueurs.size() - 1) {
            finDeLaPartie();
            aQuiDeJouer = 0;
            joueurActuel();
        } else {

            joueurActuel();
            aQuiDeJouer += 1;
        }

    }

    public void finDeLaPartie() {
        if (p.getPtsHonneur() <= 0) {
            //fin
            p.direQuiGagne(p.listeDeJoueurs);
            p.finDePartie = true;
        }

    }

    public String getNomCarteActuel(int i) {
        return joueurActuel().getHand().get(i).getNom();
    }


    public Cards carteJouer(int index) {
        return joueurActuel().getHand().get(index);
    }

    public String getNomCarteTapis(int i) {
        return joueurActuel().getTapis().get(i).getNom();
    }

    public void toucherUnecarte(int i) {

        int nb = p.toucherUneCarte(i);
        if (p.bannirCarteCentrale == true) {
            if (p.ligneCentrale[i].getEffet() != 43) {
                p.bannirUneCarteCentrale(i);
                p.bannirCarteCentrale = false;
            }

        } else if (p.acquerirHeroGratuitement == true) {
            if (p.ligneCentrale[i].getRunes() != 0) {
                p.acquerirHeroGratuitementEtLeplaceAuDessusDuDeck(i, joueurActuel());
                p.acquerirHeroGratuitement = false;
            }

        } else if (p.acquerirHeroGratuitementLevel3OuMoins == true) {
            if (p.ligneCentrale[i].getRunes() != 0 && p.ligneCentrale[i].getRunes()<=3) {
                p.acquerirHeroGratuitementEtLeplaceAuDessusDuDeck(i, joueurActuel());
                p.acquerirHeroGratuitementLevel3OuMoins = false;
            }

        } else if (p.toutGratuit == true) {
            if (p.ligneCentrale[i].getRunes() != 0) {
                p.acquerirHeroGratuitement(i, joueurActuel());
                p.toutGratuit = false;
            } else {
                p.tuerUneCarteGratuitement(i, joueurActuel());
                p.toutGratuit = false;

            }
        } else if (p.tueruneCarte4 == true) {
            if (p.ligneCentrale[i].getRunes() == 0 && p.ligneCentrale[i].getAttaque() < 5) {
                p.tuerUneCarteGratuitementI(i, joueurActuel(), 4);
                p.tueruneCarte4 = false;
            }
        } else if (p.tueruneCarte6 == true) {
            if (p.ligneCentrale[i].getRunes() == 0 && p.ligneCentrale[i].getAttaque() < 7) {
                p.tuerUneCarteGratuitementI(i, joueurActuel(), 6);
                p.tueruneCarte6 = false;
            }
        } else {

            if (nb == 0) {
                joueurActuel().tuerUneCarteAvecDeLattaque(p.ligneCentrale[i], p);
            } else {
                joueurActuel().acquerirUneCarteRunes(p.ligneCentrale[i], p);
            }
        }

    }

    public void acquerirCarteMystic() {
        Cards mystic = new Cards("Mystic", 8, "Héros", 3, 0, "", 1);
        if (p.toutGratuit == true) {
            joueurActuel().getList().add(mystic);
            p.toutGratuit = false;

        } else if (p.acquerirHeroGratuitement == true) {
            joueurActuel().getList().add(0, mystic);
            p.acquerirHeroGratuitement = false;

        } else if (p.acquerirHeroGratuitementLevel3OuMoins == true) {
            joueurActuel().getList().add(0, mystic);
            p.acquerirHeroGratuitementLevel3OuMoins = false;

        } else {
            joueurActuel().acquerirUneCarteRunes(mystic, p);
        }


    }

    public void acquerirCarteHeavyInfantry() {
        Cards heavyI = new Cards("Heavy-Infantry", 4, "Héros", 2, 0, "", 1);
        if (p.toutGratuit == true) {
            joueurActuel().getList().add(heavyI);
            p.toutGratuit = false;

        } else if (p.acquerirHeroGratuitement == true) {
            joueurActuel().getList().add(0, heavyI);
            p.acquerirHeroGratuitement = false;

        } else if (p.acquerirHeroGratuitementLevel3OuMoins == true) {
            joueurActuel().getList().add(0, heavyI);
            p.acquerirHeroGratuitementLevel3OuMoins = false;

        } else {
            joueurActuel().acquerirUneCarteRunes(heavyI, p);
        }


    }

    public void tuerLeCultiste() {

        if (p.toutGratuit == true) {
            joueurActuel().gagnerPointsHonneur(1);
            p.nombreDePtsDHonneur -= 1;

            p.toutGratuit = false;
        } else if (p.tueruneCarte4 == true) {
            joueurActuel().gagnerPointsHonneur(1);
            p.nombreDePtsDHonneur -= 1;
            p.tueruneCarte4 = false;

        } else if (p.tueruneCarte6 == true) {
            joueurActuel().gagnerPointsHonneur(1);
            p.nombreDePtsDHonneur -= 1;
            p.tueruneCarte6 = false;


        } else if (joueurActuel().attaqueDispo >= 2) {
            joueurActuel().gagnerPointsHonneur(1);
            joueurActuel().gagnerDegats(-2);
            p.nombreDePtsDHonneur -= 1;
        }
    }

    public void initialiserJ(Joueur joueur) {
        if (p.listeDeJoueurs.size() == 2) {
            joueur3 = new Joueur("j3");
            p.ajouterUnJoueurJ(joueur3);


        } else if (p.listeDeJoueurs.size() == 3) {
            joueur3 = new Joueur("j4");
            p.ajouterUnJoueurJ(joueur4);

        }
    }

    public void debutTour() {
        joueurActuel().effetConstruct(p);
    }

    public void tourEnPlus() {
        if (joueurActuel().getTapis().size()>0){
            for (Cards carteF : joueurActuel().getTapis()) {
                joueurActuel().getDefausse().add(carteF);
            }
            joueurActuel().getTapis().clear();
        }
        if (joueurActuel().getHand().size()>0){
            for (Cards carteH : joueurActuel().getHand()){
                joueurActuel().getDefausse().add(carteH);
            }
            joueurActuel().getHand().clear();
        }
        joueurActuel().attaqueDispo=0;
        joueurActuel().nbRunesDispo=0;
        joueurActuel().piocherMain();
        p.tourEnPlus=false;

    }

    public void convertir4Runes() {
        if (joueurActuel().getRunes()>=4 && joueurActuel().convertir==true){
            joueurActuel().perdreDesRunes(4);
            joueurActuel().gagnerPointsHonneur(3);
            joueurActuel().convertir=false;

        }
    }
}
