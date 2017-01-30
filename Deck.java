import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.shuffle;

/**
 * Created by Nathan on 21/11/2016.
 */
public class Deck extends ArrayList {

    public ArrayList<Cards>list;
    public ArrayList<Cards> hand;
    public ArrayList<Cards> defausse;
    public ArrayList<Cards> tapis;
    public ArrayList<Cards> construc;

    public int nbDeCarteRestanteAPiocher;

    public int tailleDeLaListe;


    public Deck(){
        this.list=new ArrayList<Cards>();
        this.hand=new ArrayList<Cards>();
        this.defausse=new ArrayList<Cards>();
        this.tapis=new ArrayList<Cards>();
        this.construc=new ArrayList<Cards>();
    }


    public ArrayList<Cards> getHand() {return hand;}
    public ArrayList<Cards> getTapis() {return tapis;}
    public ArrayList<Cards> getDefausse() {return defausse;}
    public ArrayList<Cards> getList() {return list;}
    public ArrayList<Cards> getConstruc() {return construc;}




    public void piocher(int nbDeCartePiocher) {
        for (int i=0; i<nbDeCartePiocher; i++){
            if (list.size()==0){
                remplirDeck();
            }
            getHand().add(getList().get(0));

            getList().remove(getList().get(0));

        }
    }

    public void melange(ArrayList<Cards> idk) {
        shuffle(idk);
    }

    public int tailleMain() {
        return hand.size();
    }

    public int nombreDeCarteDansSonDeck() {
        return list.size();
    }

    public boolean isListVide() {
        if (list.size()==0) return true;
        else return false;
    }

    public void remplirDeck(){
        if (isListVide()){
            for (int i=0;i<defausse.size();i++){
                Cards c =defausse.get(i);
                list.add(c);
            }
            defausse.clear();
            melange(list);
        }
    }

    public void piocherMain() {
        piocher(5);

    }

    public void initialiserDeck(){
        Cards apprentis1=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis2=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis3=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis4=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis5=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis6=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis7=new Cards("Apprentice",7,"Héros",1,0,"",0);
        Cards apprentis8=new Cards("Apprentice",7,"Héros",1,0,"",0);

        list.add(apprentis1);
        list.add(apprentis2);
        list.add(apprentis3);
        list.add(apprentis4);
        list.add(apprentis5);
        list.add(apprentis6);
        list.add(apprentis7);
        list.add(apprentis8);
        Cards milice1=new Cards("Militia",3,"Héros",0,1,"",0);
        Cards milice2=new Cards("Militia",3,"Héros",0,1,"",0);
        list.add(milice1);
        list.add(milice2);

        shuffle(list);
    }

    public void afficherLesCartesDansSaMain(){
        for (Cards c : hand) {
            c.print();
        }
    }


    public void afficherLesCartesDansSonDeck() {
        for (Cards c : list){
            c.print();
        }
    }
}
