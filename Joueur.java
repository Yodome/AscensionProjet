import javax.smartcardio.Card;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nmolle2 on 18/11/16.
 */
public class Joueur {
    public int nbRunesDispo;
    public int degats;
    public int ptsHonneur;
    public int attaqueDispo;
    public String nomJoueur;
    Deck deck;
    public boolean estEntraindeJouer;
    public boolean verifLife;
    private int sizeC;
    public boolean lifeHeroPlayed;
    public boolean convertir;
    public boolean consrtructPiocher;

    public Joueur(int ptsHonneur) {
        this.ptsHonneur=ptsHonneur;
    }

    public Joueur(String nomJoueur) {
        convertir=true;
        consrtructPiocher=true;
        this.nomJoueur=nomJoueur;
        deck = new Deck();
        deck.initialiserDeck();
        estEntraindeJouer=false;
        verifLife=false;
        lifeHeroPlayed=false;
        setAttaque(0);
        setRunes(0);
        setPtsHonneurs(0);
    }

    public int setRunes(int nbRunesDispo) {
        this.nbRunesDispo = nbRunesDispo;
        return this.nbRunesDispo;
    }

    public int setAttaque(int attaqueDispo) {
        this.attaqueDispo = attaqueDispo;
        return this.attaqueDispo;
    }

    public int setPtsHonneurs(int ptsHonneurs) {
        this.ptsHonneur = ptsHonneurs;
        return this.ptsHonneur;
    }

    public int getPtsHonneur() {
        return ptsHonneur;
    }

    public Cards getCarteJouer(int indexDelaCarte) {
        return getHand().get(indexDelaCarte);
    }

    public int getRunes() {return nbRunesDispo;}

    public ArrayList<Cards> getHand(){return deck.getHand();}
    public ArrayList<Cards> getTapis() { return  deck.getTapis();}
    public ArrayList<Cards> getList() { return  deck.getList();}
    public ArrayList<Cards> getDefausse() { return  deck.getDefausse();}
    public ArrayList<Cards> getConstruct() { return  deck.getConstruc();}

    public void gagnerPointsHonneur(int pts) {
        ptsHonneur+=pts;
    }

    public int gagnerDegats(int degats) {
        attaqueDispo+=degats;
        return attaqueDispo;
    }

    public int gagnerRunes(int runes) {
        nbRunesDispo+=runes;
        return nbRunesDispo;
    }


    public int toucherAUneCarteCentrale(Cards c, Plateau plateau){
        if (c.getRunes()==0){
            return tuerUneCarteAvecDeLattaque(c,plateau);

        }else if (c.getAttaque()==0){
            return acquerirUneCarteRunes(c,plateau);
        }else {
            return -1;
        }

    }
    public int acquerirUneCarteRunes(Cards c1,Plateau plat) {
        if (c1.getRunes()>0 && nbRunesDispo>=c1.getRunes()){
            getDefausse().add(c1);
            if (plat.deck.size()>0){
                plat.deck.remove(c1);

            }else
            {
                plat.remplirLeDeck();
                acquerirUneCarteRunes(c1,plat);
            }
            nbRunesDispo-=c1.getRunes();
            plat.remplacerLaLigneCentrale(c1);
            return 2;

        }
        else return -1;
    }

    public int tuerUneCarteAvecDeLattaque(Cards c1, Plateau plat) {

        if (attaqueDispo>=c1.getAttaque() && c1.getAttaque()!=0){
            attaqueDispo-=c1.getAttaque();
            gagnerPointsHonneur(c1.getRecompense());

            plat.getNeant().add(c1);
            c1.effetDeCarte(c1.getEffet(),this,plat);
            plat.perdreDesPointsDhonneurs(c1);

            if (plat.deck.size() > 0){
                plat.remplacerLaLigneCentrale(c1);
                plat.neant.add(c1);
            }
//            plat.remplacerLaLigneCentrale(plat.supprimerCarteCentrale(c1));
            return 1;

        }
        else return -1;
    }







    public void jouerUneCarte(Cards cards, Plateau plateau){
        if (cards.getType().equals("Constructeur")||
                cards.getType().equals("Constructeur Lifebound")==true
                || cards.getType().equals("Constructeur Mechana")==true){
            getConstruct().add(cards);
            getHand().remove(cards);
        }else {
            if (cards.getType().equals("Héros Lifebound")) lifeHeroPlayed=true;
            cards.effetDeCarte(cards.getEffet(),this, plateau);
            getTapis().add(cards);
            getHand().remove(cards);
        }

    }

    public void piocherMain(){
        deck.piocherMain();
    }

    public void piocher(int nbCarteAPiocher){ deck.piocher(nbCarteAPiocher); }

    public void finDuTour(){
        if (getTapis().size()>0){
            for (Cards carteF : getTapis()) {
                getDefausse().add(carteF);
            }
            getTapis().clear();
        }
        if (getHand().size()>0){
            for (Cards carteH : getHand()){
                getDefausse().add(carteH);
            }
            getHand().clear();
        }
        attaqueDispo=0;
        nbRunesDispo=0;
        piocherMain();
//        estEntraindeJouer=false;
    }

    public void printPtsHonneur(){
        System.out.println(nomJoueur + " nombreDePoints D'Honneur : " + ptsHonneur);
    }

    public int calculerPtsHonneurFinaux() {
        //////////////// vider la défausse et pour chaque carte ajouter pts honneur au pts honneur/////////:
        if (getDefausse().size()!=0) {
            for (Cards cA : getDefausse()){
                getList().add(cA);
            }
        }
        if (getHand().size()!=0){
            for (Cards cA : getHand()){
                getList().add(cA);
            }
        }
        if (getConstruct().size()!=0){
            for (Cards cA : getConstruct()){
                getList().add(cA);
            }
        }
        for (Cards cards : getList()){
            ptsHonneur+=cards.getRecompense();
        }
        return  ptsHonneur;
    }


    public void afficherLesCartesDansSaMain() {
        deck.afficherLesCartesDansSaMain();
    }


    public void printHand() {
        if (!getHand().isEmpty()){
            for (Cards c : getHand()){
                c.print();
            }

        }

    }

    public void afficherLesCartesDansSonDeck() {
        deck.afficherLesCartesDansSonDeck();
    }

    public void piquerDesCartes(ArrayList<Joueur> listeDeJoueurs) {
        for (Joueur j : listeDeJoueurs){
            if (j.equals(this)==false){
                Random r=new Random();
                int ran=r.nextInt(4);
                this.getHand().add(j.getHand().get(ran));
                System.out.println(j+"   carte  "+ j.getHand().get(ran).getNom() + "    " +ran );
                j.getHand().remove(ran);
            }
        }

        for (Cards c : getHand()){
            System.out.println(c.getNom());
        }
    }


    public void jouerUneCarteUneCopie(Cards cards, Plateau p) {
        cards.effetDeCarte(cards.getEffet(),this, p);

    }

    public void effetConstruct(Plateau plateau) {
        if (getConstruct().size()>0){
            sizeC=getConstruct().size();
            for (int i=0; i<sizeC;i++){
                if (getConstruct().get(i).getEffet()==26 ||
                        getConstruct().get(i).getEffet()==27 ||
                        getConstruct().get(i).getEffet()==41 ||
                        getConstruct().get(i).getEffet()==40  )
                    {
                        getConstruct().get(i).effetDeCarte(getConstruct().get(i).getEffet(),this,plateau);
                    }
            }
        }

    }

    public void perdreDesRunes(int i) {
        nbRunesDispo-=4;
    }

    public void suppDesConstructSauf1() {
        while (getConstruct().size()>1){
            getConstruct().remove(0);
        }
    }

    public String getNom() {
        return nomJoueur;
    }
}
