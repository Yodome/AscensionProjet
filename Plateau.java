import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toString;
import static java.util.Collections.reverse;
import static java.util.Collections.shuffle;

/**
 * Created by Nathan on 23/11/2016.
 */
public class Plateau {

    public Cards[] ligneCentrale;
    public Cards c;
    public ArrayList<Cards> neant;
    public ArrayList<Cards> deck;
    public ArrayList<Joueur> listeDeJoueurs;
    public int nombreDePtsDHonneur;
    public Cards[] tableauHabitantsR;
    public boolean finDePartie;
    public int intGagnant;
    public int indexGagnant;
    public String ligneCentralePrint;
    public int retour;
    public boolean bannirCarteCentrale;
    public boolean acquerirHeroGratuitement;
    public boolean bannirUneCarteDansLaMain;
    public boolean discardACard;
    public boolean toutGratuit;
    public boolean tueruneCarte4;
    public boolean tueruneCarte6;
    public boolean acquerirHeroGratuitementLevel3OuMoins;
    public boolean copyLesEffets;
    public boolean piocherUneCarteConstructeur;
    public boolean tourEnPlus;
    public Joueur[] tabJoueur;


    public Plateau(){
        ligneCentralePrint="Ligne centrale : ";
        ligneCentrale=new Cards[6];
        tableauHabitantsR=new Cards[3];
        creerLesHabitants();
        deck=new ArrayList<Cards>();
        creerDeckDuPlateau();
        faireLaLigneCentrale();
        neant=new ArrayList<Cards>();
        listeDeJoueurs= new ArrayList<Joueur>();
        bannirCarteCentrale=false;
        acquerirHeroGratuitement=false;
        bannirUneCarteDansLaMain=false;
        tueruneCarte4=false;
        tueruneCarte6=false;
        finDePartie=false;
        piocherUneCarteConstructeur=false;
        tourEnPlus=false;
    }

    public ArrayList<Cards> getNeant() {return neant;}

    public Cards getCartecentrale(int indexDelaCarte) { return ligneCentrale[indexDelaCarte];   }


    public void ajouterUnJoueurJ(Joueur j){
        if (verifNombreDeJoueurs()){listeDeJoueurs.add(j);}
    }

    public boolean verifNombreDeJoueurs(){
        if (listeDeJoueurs.size()<3){
            return true;
        }
        return false;
    }



    public Cards[] faireLaLigneCentrale() {
        for (int i=0; i<6; i++){
            c=deck.get(0);
            deck.remove(c);
            ligneCentrale[i]=c;
        }
        return ligneCentrale;
    }

    public void remplacerLaLigneCentrale(Cards c) {
        remplirLeDeck();
        for (int i=0; i< ligneCentrale.length; i++){
            if (ligneCentrale[i].equals(c)){
                ligneCentrale[i]=deck.get(0);
                deck.remove(0);

            }
        }
    }

    public  String afficherLaLigneCentrale(){
        for (int i=0; i<ligneCentrale.length;i++){
            ligneCentrale[i].print();
        }
        return ligneCentralePrint;
    }

    public void afficherLeNeant() {
        for (Cards c : neant){
            c.print();

        }

    }

    public void remplirLeDeck(){
        if (deck.size()==0){
            for( Cards c : neant){
                deck.add(c);
            }
            neant.clear();
        }
    }

    public int supprimerCarteCentrale(Cards carteASupprimer){
        for (int i=0; i<ligneCentrale.length;i++){
            if (carteASupprimer.equals(ligneCentrale[i])){
                ligneCentrale[i]=null;
                retour=i;
            }
        }

        return retour;
    }

    public ArrayList<Joueur> quiCommence(ArrayList<Joueur> liste){
        shuffle(liste);
        return liste;
    }

    public int creerLespointsDHonneur(){
        // set le nombre de pts d'honneur
//        nombreDePtsDHonneur=listeDeJoueurs.size()*30;
        nombreDePtsDHonneur=30;
        return nombreDePtsDHonneur;

    }


    public void remplirLigneCentrale() {
        for (int i=0; i<ligneCentrale.length;i++){
            if (ligneCentrale[i]==null && deck.size()!=0){
                ligneCentrale[i]=deck.get(0);
                deck.remove(0);
            }
        }
    }

    public  void creerLesHabitants(){
        tableauHabitantsR[0]=new Cards("Mystic",8,"Héros",3,0,"",1);
        tableauHabitantsR[1]=new Cards("Heavy-Infantry",4,"Héros",2,0,"",1);
        tableauHabitantsR[2]=new Cards("Cultist",400,"Monstre",0,2,"",1);


    }


    public boolean tourDeJeu(Joueur j){
        j.estEntraindeJouer=true;
        while (j.estEntraindeJouer!=false){
            // Completer dedans
            j.afficherLesCartesDansSaMain();

        }


//        j.nbRunesDispo=0;
//        j.attaqueDispo=0;

        return true;
    }

//    public void gereLeJeu(ArrayList<Joueur> listeDeJoueurs){
//        while (finDePartie!=0){
//            for (int k=0;k<listeDeJoueurs.size();k++){
//                tourDeJeu(listeDeJoueurs.get(k));
//                System.out.print('r');
//                if (k==listeDeJoueurs.size()-1) k=0;
//            }
//        }
//        System.out.println("yopla");
//
//    }

    /////////////// en gros si c'est la fin de la partie le onombre de poitns d'honneur =0 , mais il faut laisser jouer les autres ////////////////
    public boolean EstCeLafinDeLaPartie(){
        if (nombreDePtsDHonneur==0){
            return true;
        }
        return false;
    }

    public void direQuiGagne(ArrayList<Joueur> listeDeJ){
        for (Joueur jj : listeDeJ){
            jj.calculerPtsHonneurFinaux();

        }

//        tabJoueur=new Joueur[listeDeJ.size()-1];
//        for (int i=0; i<listeDeJ.size();i++){
//            tabJoueur[i]=listeDeJ.get(i);
//        }
//        int valeur=0;
//        for (int k=0;k<tabJoueur.length;k++){
//            if (tabJoueur[k].ptsHonneur>valeur){
//
//            }
//
//        }



    }




    public void creerDeckDuPlateau(){
//        public Cards(String nom, int effet, String type, int runes, int attaque, String description, int recompense) {

        Cards arhaInitiate1= new Cards("Arha-Initiate", 0,"Héros",1,0,"Nellin observed the whirling movements of the initiates. \"Are they practicing forms ? \" he asked. \" No, \" came the intructor's reply.\"They are meditating. \"",1);
        Cards arhaInitiate2= new Cards("Arha-Initiate", 0,"Héros",1,0,"Nellin observed the whirling movements of the initiates. \"Are they practicing forms ? \" he asked. \" No, \" came the intructor's reply.\"They are meditating. \"",1);
        Cards arhaInitiate3= new Cards("Arha-Initiate", 0,"Héros",1,0,"Nellin observed the whirling movements of the initiates. \"Are they practicing forms ? \" he asked. \" No, \" came the intructor's reply.\"They are meditating. \"",1);
        Cards ArhaTemplar1= new Cards("Arha-Templar", 15,"Héros",4,0,"Shapen the mind, and the sword will follow",3);
        Cards ArhaTemplar2= new Cards("Arha-Templar", 15,"Héros",4,0,"Shapen the mind, and the sword will follow",3);
        Cards Ascetic1= new Cards("Ascetic-of-the-Lidless-Eye", 1,"Héros",5,0,"",2);
        Cards Ascetic2= new Cards("Ascetic-of-the-Lidless-Eye", 1,"Héros",5,0,"",2);
        Cards MasterDhartha= new Cards("Master-Dhartha", 2,"Héros",7,0,"",3);
        Cards OziahThePeerless= new Cards("Oziah-the-Peerless", 16,"Héros",6,0,"",3);
        Cards SeerOfTheForkedPath1= new Cards("Seer-of-The-Forked-Path", 17,"Héros",2,0,"",1);
        Cards SeerOfTheForkedPath2= new Cards("Seer-of-The-Forked-Path", 17,"Héros",2,0,"",1);
        Cards SeerOfTheForkedPath3= new Cards("Seer-of-The-Forked-Path", 17,"Héros",2,0,"",1);
        Cards TempleLibrarian1= new Cards("Temple-Librarian", 18,"Héros",2,0,"",1);
        Cards TempleLibrarian2= new Cards("Temple-Librarian", 18,"Héros",2,0,"",1);
        Cards TempleLibrarian3= new Cards("Temple-Librarian", 18,"Héros",2,0,"",1);
        Cards TwofoldAskara= new Cards("Twofold-Askara", 19,"Héros",4,0,"",2);
        Cards TheAllSeeingEye= new Cards("The-All-Seeing-Eye", 20,"Constructeur",6,0,"",2);
        Cards TabletOfTimesDawwn= new Cards("ToTD", 21,"Constructeur",5,0,"",2);
        Cards CetraWeaverOfStars= new Cards("Cetra-Weaver-of-Stars", 22,"Héros LifeBound",7,0,"",4);
        Cards DruidsOfTheStoneCircle1= new Cards("Druids-of-the-Stone-Circle", 49,"Héros Lifebound",4,0,"",3);
        Cards DruidsOfTheStoneCircle2= new Cards("Druids-of-the-Stone-Circle", 49,"Héros Lifebound",4,0,"",3);
        Cards FlytrapWitch1= new Cards("Flytrap-Witch", 14,"Héros Lifebound",5,0,"",2);
        Cards FlytrapWitch2= new Cards("Flytrap-Witch", 14,"Héros Lifebound",5,0,"",2);
        Cards Landtalker= new Cards("Landtalker", 9,"Héros Lifebound",6,0,"",3);
        Cards LifeboundInitiate1= new Cards("Lifebound-Initiate", 23,"Héros Lifebound",1,0,"",1);
        Cards LifeboundInitiate2= new Cards("Lifebound-Initiate", 23,"Héros Lifebound",1,0,"",1);
        Cards LifeboundInitiate3= new Cards("Lifebound-Initiate", 23,"Héros Lifebound",1,0,"",1);
        Cards RunicLycanthrop1= new Cards("Runic-Lycanthrope", 24,"Héros Lifebound",3,0,"",1);
        Cards RunicLycanthrop2= new Cards("Runic-Lycanthrope", 24,"Héros Lifebound",3,0,"",1);
        Cards WolfShaman1= new Cards("Wolf-Shaman", 25,"Héros Lifebound",3,0,"",1);
        Cards WolfShaman2= new Cards("Wolf-Shaman", 25,"Héros Lifebound",3,0,"",1);
        Cards WolfShaman3= new Cards("Wolf-Shaman", 25,"Héros Lifebound",3,0,"",1);
        Cards SnapDragon1= new Cards("Snapdragon", 26,"Constructeur Lifebound",5,0,"",2);
        Cards SnapDragon2= new Cards("Snapdragon", 26,"Constructeur Lifebound",5,0,"",2);
        Cards YggrasilStaff1= new Cards("Yggdrasil-Staff", 27,"Constructeur Lifebound",4,0,"",2);
        Cards YggrasilStaff2= new Cards("Yggdrasil-Staff", 27,"Constructeur Lifebound",4,0,"",2);
        Cards AvatarGolem= new Cards("Avatar-Golem", 99999,"Hero Mechana",3,0,"",2);
        Cards KorTheFerromancer= new Cards("Kor-the-Ferromancer", 28,"Hero Mechana",3,0,"",2);
        Cards MechanaInitiate1= new Cards("Mechana-Initiate", 29,"Hero Mechana",1,0,"",1);
        Cards MechanaInitiate2= new Cards("Mechana-Initiate", 29,"Hero Mechana",1,0,"",1);
        Cards ReactorMon1= new Cards("Reactor-Monk", 30,"Hero Mechana",4,0,"",2);
        Cards ReactorMon2= new Cards("Reactor-Monk", 30,"Hero Mechana",4,0,"",2);
        Cards BurrowerMarkII1= new Cards("Burrower-Mark-II", 31,"Constructeur Mechana",3,0,"",3);
        Cards BurrowerMarkII2= new Cards("Burrower-Mark-II", 31,"Constructeur Mechana",3,0,"",3);
        Cards BurrowerMarkII3= new Cards("Burrower-Mark-II", 31,"Constructeur Mechana",3,0,"",3);
        Cards TheGrandDesign1= new Cards("The-Grand-Design", 32,"Constructeur Mechana",6,0,"",6);
        Cards TheGrandDesign2= new Cards("The-Grand-Design", 32,"Constructeur Mechana",6,0,"",6);
        Cards HedronCannon= new Cards("Hedron-Cannon", 33,"Constructeur Mechana",8,0,"",8);
        Cards HedronLinkDevice= new Cards("Hedron-Link-Device", 34,"Constructeur Mechana",7,0,"",7);
        Cards RocketCouturierX991= new Cards("Rocket-Courier-X-99", 35,"Constructeur Mechana",4,0,"",4);
        Cards RocketCouturierX992= new Cards("Rocket-Courier-X-99", 35,"Constructeur Mechana",4,0,"",4);
        Cards WatchmalersAltar1= new Cards("Watchmakers-Altar", 36,"Constructeur Mechana",5,0,"",5);
        Cards WatchmalersAltar2= new Cards("Watchmakers-Altar", 36,"Constructeur Mechana",5,0,"",5);
        Cards ArbiterOfThePrecipice1= new Cards("Arbiter-of-the-Precipice", 37,"Héros",4,0,"",1);
        Cards ArbiterOfThePrecipice2= new Cards("Arbiter-of-the-Precipice", 37,"Héros",4,0,"",1);
        Cards DemonSlayer1= new Cards("Demon-Slayer", 5,"Héros",4,0,"",2);
        Cards DemonSlayer2= new Cards("Demon-Slayer", 5,"Héros",4,0,"",2);
        Cards EmriOneWithTheVoid= new Cards("Emri-One-with-the-Void", 6,"Héros",6,0,"",3);
        Cards ShadeOfTheBlackWatch1= new Cards("Shade-of-the-Black-Watch", 38,"Héros",3,0,"",1);
        Cards ShadeOfTheBlackWatch2= new Cards("Shade-of-the-Black-Watch", 38,"Héros",3,0,"",1);
        Cards ShadeOfTheBlackWatch3= new Cards("Shade-of-the-Black-Watch", 38,"Héros",3,0,"",1);
        Cards SpikeVixen1= new Cards("Spike-Vixen", 11,"Héros",2,0,"",1);
        Cards SpikeVixen2= new Cards("Spike-Vixen", 11,"Héros",2,0,"",1);
        Cards VoidInitiate1= new Cards("Void-Initiate", 39,"Héros",1,0,"",1);
        Cards VoidInitiate2= new Cards("Void-Initiate", 39,"Héros",1,0,"",1);
        Cards VoidInitiate3= new Cards("Void-Initiate", 39,"Héros",1,0,"",1);
        Cards Muramasa= new Cards("Muramasa", 40,"Constructeur",7,0,"",4);
        Cards ShadowStar1= new Cards("Shadow-Star", 41,"Constructeur",3,0,"",2);
        Cards ShadowStar2= new Cards("Shadow-Star", 41,"Constructeur",3,0,"",2);
        Cards Voidthirster1= new Cards("Voidthirster", 42,"Constructeur",5,0,"",3);
        Cards Voidthirster2= new Cards("Voidthirster", 42,"Constructeur",5,0,"",3);
        /////// MONSTRE ////////////
        Cards AvatarOfTheFallen= new Cards("Avatar-of-the-Fallen", 43,"Monstre",0,7,"",4);
        Cards Corrosivewidow1= new Cards("Corrosive-Widow", 44,"Monstre",0,4,"",3);
        Cards Corrosivewidow2= new Cards("Corrosive-Widow", 44,"Monstre",0,4,"",3);
        Cards Corrosivewidow3= new Cards("Corrosive-Widow", 44,"Monstre",0,4,"",3);
        Cards Corrosivewidow4= new Cards("Corrosive-Widow", 44,"Monstre",0,4,"",3);
        Cards EarthTyrant1= new Cards("Earth-Tyrant", 1,"Monstre",0,6,"",5);
        Cards EarthTyrant2= new Cards("Earth-Tyrant", 1,"Monstre",0,6,"",5);
        Cards Mephit1= new Cards("Mephit", 45,"Monstre",0,3,"",2);
        Cards Mephit2= new Cards("Mephit", 45,"Monstre",0,3,"",2);
        Cards Mephit3= new Cards("Mephit", 45,"Monstre",0,3,"",2);
        Cards MistakeOfCreation1= new Cards("Mistake-of-Creation", 46,"Monstre",0,4,"",4);
        Cards MistakeOfCreation2= new Cards("Mistake-of-Creation", 46,"Monstre",0,4,"",4);
        Cards MistakeOfCreation3= new Cards("Mistake-of-Creation", 46,"Monstre",0,4,"",4);
        Cards MistakeOfCreation4= new Cards("Mistake-of-Creation", 46,"Monstre",0,4,"",4);
        Cards SamaelsTrickster1= new Cards("Samaels-Trickster", 7,"Monstre",0,3,"",1);
        Cards SamaelsTrickster2= new Cards("Samaels-Trickster", 7,"Monstre",0,3,"",1);
        Cards SamaelsTrickster3= new Cards("Samaels-Trickster", 7,"Monstre",0,3,"",1);
        Cards SamaelsTrickster4= new Cards("Samaels-Trickster", 7,"Monstre",0,3,"",1);
        Cards Seatyrant1= new Cards("Sea-Tyrant", 47,"Monstre",0,5,"",5);
        Cards Seatyrant2= new Cards("Sea-Tyrant", 47,"Monstre",0,5,"",5);
        Cards Seatyrant3= new Cards("Sea-Tyrant", 47,"Monstre",0,5,"",5);
        Cards TormentedSoul1= new Cards("Tormented-Soul", 0,"Monstre",0,3,"",1);
        Cards TormentedSoul2= new Cards("Tormented-Soul", 0,"Monstre",0,3,"",1);
        Cards TormentedSoul3= new Cards("Tormented-Soul", 0,"Monstre",0,3,"",1);
        Cards WindTyrant1= new Cards("Wind-Tyrant",9 ,"Monstre",0,5,"",3);
        Cards WindTyrant2= new Cards("Wind-Tyrant", 9,"Monstre",0,5,"",3);
        Cards WindTyrant3= new Cards("Wind-Tyrant", 9,"Monstre",0,5,"",3);
        Cards XeronDukeOfLies= new Cards("Xeron-Duke-of-Lies", 48,"Monstre",0,6,"",3);


        deck.add(XeronDukeOfLies);
        deck.add(WindTyrant3);

        deck.add(RunicLycanthrop2);
        deck.add(LifeboundInitiate1);
        deck.add(WindTyrant1);
        deck.add(TormentedSoul3);
        deck.add(TormentedSoul2);
        deck.add(TormentedSoul1);
        deck.add(Seatyrant3);

        deck.add(Seatyrant1);
        deck.add(WindTyrant2);

        deck.add(SamaelsTrickster4);
        deck.add(SamaelsTrickster3);
        deck.add(SamaelsTrickster2);
        deck.add(SamaelsTrickster1);
//        deck.add(MistakeOfCreation1);
//        deck.add(MistakeOfCreation2);
//        deck.add(MistakeOfCreation3);
//        deck.add(MistakeOfCreation4);
//        deck.add(Mephit3);
//        deck.add(Mephit2);
//        deck.add(Mephit1);
        deck.add(EarthTyrant2);
        deck.add(EarthTyrant1);
//        deck.add(Corrosivewidow1);
//        deck.add(Corrosivewidow2);
//        deck.add(Corrosivewidow3);
//        deck.add(Corrosivewidow4);
        deck.add(AvatarOfTheFallen);
//        deck.add(Voidthirster2);
//        deck.add(Voidthirster1);
        deck.add(ShadowStar2);
        deck.add(ShadowStar1);
        deck.add(Muramasa);
//        deck.add(VoidInitiate3);
//        deck.add(VoidInitiate2);
//        deck.add(VoidInitiate1);
        deck.add(SpikeVixen2);
        deck.add(SpikeVixen1);
//        deck.add(ShadeOfTheBlackWatch3);
//        deck.add(ShadeOfTheBlackWatch2);
//        deck.add(ShadeOfTheBlackWatch1);
        deck.add(EmriOneWithTheVoid);
        deck.add(DemonSlayer2);
        deck.add(DemonSlayer1);
        deck.add(ArbiterOfThePrecipice2);
        deck.add(ArbiterOfThePrecipice1);
//        deck.add(WatchmalersAltar2);
//        deck.add(WatchmalersAltar1);
//        deck.add(RocketCouturierX992);
//        deck.add(RocketCouturierX991);
//        deck.add(HedronLinkDevice);
//        deck.add(HedronCannon);
//        deck.add(TheGrandDesign2);
//        deck.add(TheGrandDesign1);
//        deck.add(BurrowerMarkII1);
//        deck.add(BurrowerMarkII2);
//        deck.add(BurrowerMarkII3);
//        deck.add(ReactorMon2);
//        deck.add(ReactorMon1);
//        deck.add(MechanaInitiate1);
//        deck.add(MechanaInitiate2);
        deck.add(KorTheFerromancer);
        deck.add(arhaInitiate2);
//        deck.add(AvatarGolem);
        deck.add(YggrasilStaff2);
        deck.add(YggrasilStaff1);
        deck.add(SnapDragon1);
        deck.add(ArhaTemplar1);
        deck.add(SnapDragon2);
        deck.add(WolfShaman1);
        deck.add(ArhaTemplar2);
        deck.add(WolfShaman2);
        deck.add(RunicLycanthrop1);
        deck.add(Seatyrant2);
        deck.add(LifeboundInitiate3);
        deck.add(LifeboundInitiate2);
        deck.add(Landtalker);
        deck.add(arhaInitiate1);

        deck.add(arhaInitiate3);


        deck.add(WolfShaman3);

        deck.add(Ascetic1);
        deck.add(Ascetic2);
        deck.add(MasterDhartha);
        deck.add(OziahThePeerless);
        deck.add(SeerOfTheForkedPath1);
       deck.add(SeerOfTheForkedPath2);
        deck.add(SeerOfTheForkedPath3);
        deck.add(TempleLibrarian1);
        deck.add(TempleLibrarian2);
        deck.add(TempleLibrarian3);
        deck.add(TwofoldAskara);
        deck.add(TheAllSeeingEye);
        deck.add(TabletOfTimesDawwn);
        deck.add(CetraWeaverOfStars);
        deck.add(DruidsOfTheStoneCircle1);
        deck.add(DruidsOfTheStoneCircle2);
        deck.add(FlytrapWitch1);
        deck.add(FlytrapWitch2);
        shuffle(deck);
    }


    public void perdreDesPointsDhonneurs(Cards c1) {
        nombreDePtsDHonneur-=c1.getRecompense();
        if (nombreDePtsDHonneur<0) {
            nombreDePtsDHonneur=0;
        }

    }

    public void bannirUneCarteCentrale(int index) {
        neant.add(ligneCentrale[index]);
        ligneCentrale[index]=deck.get(0);
        deck.remove(0);
    }

    public int toucherUneCarte(int i) {
        if (ligneCentrale[i].getRunes()==0){
            // tuer une carte
            return 0;
        }else {
            //acquerir
            return 1;
        }
    }

    public void acquerirHeroGratuitementEtLeplaceAuDessusDuDeck(int index, Joueur joueur) {
        joueur.deck.list.add(0,ligneCentrale[index]);
//        reverse(joueur.deck);
        ligneCentrale[index]=deck.get(0);
        deck.remove(0);

    }

    public void acquerirHeroGratuitement(int i, Joueur joueur) {
        joueur.deck.defausse.add(ligneCentrale[i]);
        ligneCentrale[i]=deck.get(0);
        deck.remove(0);
    }

    public void tuerUneCarteGratuitement(int i, Joueur joueur) {

            joueur.gagnerPointsHonneur(ligneCentrale[i].getRecompense());
            getNeant().add(ligneCentrale[i]);
        ligneCentrale[i].effetDeCarte(ligneCentrale[i].getEffet(),joueur,this);
            perdreDesPointsDhonneurs(ligneCentrale[i]);

            if (deck.size() > 0){
                remplacerLaLigneCentrale(ligneCentrale[i]);
                neant.add(ligneCentrale[i]);
            }

    }

    public void tuerUneCarteGratuitementI(int i, Joueur joueur, int i1) {
        if (ligneCentrale[i].getAttaque()<=i1){
            joueur.gagnerPointsHonneur(ligneCentrale[i].getRecompense());
            perdreDesPointsDhonneurs(ligneCentrale[i]);
            getNeant().add(ligneCentrale[i]);
            ligneCentrale[i].effetDeCarte(ligneCentrale[i].getEffet(),joueur,this);
        }
        if (deck.size() > 0){
            remplacerLaLigneCentrale(ligneCentrale[i]);
            neant.add(ligneCentrale[i]);
        }

    }

    public int getPtsHonneur() {
        System.out.println(" Pts H P " + nombreDePtsDHonneur);
        return nombreDePtsDHonneur;
    }

    public void detruireDesConstructeurs(Joueur joueurQuiJoueLaCarte) {
        for (Joueur joueur :listeDeJoueurs){
            if (!joueur.equals(joueurQuiJoueLaCarte)){
                joueur.suppDesConstructSauf1();
            }
        }
    }

    public String gagne() {
        int index=0;
        int psss=0;
        for (int i=0;i<listeDeJoueurs.size();i++){
            if (listeDeJoueurs.get(i).getPtsHonneur()>psss){
                psss=listeDeJoueurs.get(i).getPtsHonneur();
                index=i;
            }
        }

        return listeDeJoueurs.get(index).getNom();
    }
}