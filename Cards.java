/**
 * Created by nmolle2 on 14/11/16.
 */
public class Cards {
    private int effet;
    private String description;
    private String nom;
    private String[] tabtype = new String[] {"évents","Monstre","Héros","Constructeur","Héros Lifebound", "Constructeur Lifebound", "Héros Mechana", "Constructeur Mechana"};
    private String type;
    private int runes;
    private int recompense;
    private int attaque;


    ///Le plus gros constructeur//
    public Cards(String nom, int effet, String type, int runes, int attaque, String description, int recompense) {
        this.nom=nom;
        this.recompense=recompense;
        this.effet=effet;
        this.type=type;
        this.runes=runes;
        this.attaque=attaque;
        this.description=description;
    }




    //////////////// Getteur //////////////////////
    public String getNom() {
        return nom;
    }

    public int getRecompense() { return recompense;}

    public String getDescription() {
        return description;
    }

    public int getRunes() {
        return runes;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getEffet() {return effet; }




    public int gagnerDesRunes(Joueur j, int runes) {
        System.out.println( "joueur " + j.nomJoueur +"runes  "+j.nbRunesDispo +" " );
        j.nbRunesDispo= j.nbRunesDispo+runes;
        System.out.println( "runes  " +j.nbRunesDispo + " +");
        return j.nbRunesDispo;
    }

    public int gagnerDesDegats(Joueur joueur, int degats) {return joueur.gagnerDegats(degats);}

    public boolean estDetype(String type) {
        for (int i = 0; i< tabtype.length; i++){
            if ( type.equals(tabtype[i])){
                this.type = type;
                return true;
            }
        }
        return  false;
    }

    public  void print(){
        System.out.println(""+nom+ " runes " + runes + " attaque : " + attaque);
    }

    public void effetDeCarte(int idEffet, Joueur joueurQuiJoueLaCarte, Plateau plateau) {
        if (plateau.discardACard==true){
            plateau.discardACard=false;
        }
        if (plateau.acquerirHeroGratuitement==true) plateau.acquerirHeroGratuitement=false;
        if (plateau.tueruneCarte4==true) plateau.tueruneCarte4=false;
        if (plateau.tueruneCarte6==true) plateau.tueruneCarte6=false;
        if (plateau.acquerirHeroGratuitementLevel3OuMoins==true) plateau.acquerirHeroGratuitementLevel3OuMoins=false;

        switch (idEffet) {
            case 0:
                joueurQuiJoueLaCarte.deck.piocher(1);
                break;
            case 1:
                joueurQuiJoueLaCarte.deck.piocher(2);
                break;
            case 2:
                joueurQuiJoueLaCarte.deck.piocher(3);
                break;
            case 3:
                joueurQuiJoueLaCarte.gagnerDegats(1);
                break;
            case 4:
                joueurQuiJoueLaCarte.gagnerDegats(2);
                break;
            case 5:
                joueurQuiJoueLaCarte.gagnerDegats(3);

                break;
            case 6:
                joueurQuiJoueLaCarte.gagnerDegats(4);
                break;
            case 7:
                joueurQuiJoueLaCarte.gagnerRunes(1);
                break;
            case 8:
                gagnerDesRunes(joueurQuiJoueLaCarte,2);
                break;
            case 9:
                gagnerDesRunes(joueurQuiJoueLaCarte,3);
                break;
            case 11:
                gagnerDesRunes(joueurQuiJoueLaCarte,1);
                joueurQuiJoueLaCarte.piocher(1);
                break;
            case 12:
                joueurQuiJoueLaCarte.gagnerPointsHonneur(1);
                break;
            case 14:
                joueurQuiJoueLaCarte.gagnerPointsHonneur(2);
                joueurQuiJoueLaCarte.piocher(1);
                break;
            case 15:
                plateau.tueruneCarte4=true;
                break;
            case 16:
                plateau.tueruneCarte6=true;
                break;
            case 17:
                joueurQuiJoueLaCarte.deck.piocher(1);
                plateau.bannirCarteCentrale=true;
                break;
            case 18:
                plateau.discardACard=true;
                break;
            case 19:
                plateau.copyLesEffets=true;
                break;
            case 20:
                plateau.piocherUneCarteConstructeur=true;
                break;
            case 21:
                plateau.tourEnPlus=true;
                break;
            case 22:
                plateau.acquerirHeroGratuitement=true;
                break;
            case 23:
                joueurQuiJoueLaCarte.gagnerPointsHonneur(1);
                gagnerDesRunes(joueurQuiJoueLaCarte,1);
                break;
            case 24:
                gagnerDesRunes(joueurQuiJoueLaCarte,2);
                verifLifebound(joueurQuiJoueLaCarte);
                break;
            case 25:
                gagnerDesRunes(joueurQuiJoueLaCarte,1);
                joueurQuiJoueLaCarte.deck.piocher(1);
                break;
            case 26:
                System.out.println("Once per turn, gain 1 runes, the first time you play a lifebound hero, each turn gain 1 recompense");
                joueurQuiJoueLaCarte.gagnerRunes(1);
                if (joueurQuiJoueLaCarte.lifeHeroPlayed==true){
                    joueurQuiJoueLaCarte.gagnerPointsHonneur(1);

                }
                break;
            case 27:
                System.out.println("Once per turn, you may spend 4r to gain 3 recompense");
                joueurQuiJoueLaCarte.gagnerDegats(1);
                break;
            case 28:
                gagnerDesDegats(joueurQuiJoueLaCarte,2);
                plusDeDeuxConstructeur(joueurQuiJoueLaCarte);
                break;
            case 29:
                System.out.println("Gain 1d or 1r");
                break;
            case 30:
                joueurQuiJoueLaCarte.gagnerPointsHonneur(2);
                System.out.println("Gain 2r You pay 1R less the next time you acquire a Construc this turn");
                break;
            case 31:
                joueurQuiJoueLaCarte.gagnerPointsHonneur(2);
                System.out.println("Once per turn, when you put a Mechana Construc into play (including this one) , draw a card");
                break;
            case 32:
                joueurQuiJoueLaCarte.gagnerPointsHonneur(2);
                System.out.println("Once per turn, gain 2d.You may spend it only to acquire Mechana Construct");
                break;
            case 33:
                System.out.println(" Once per turn");
                gagner1DParTourPourChaqueMechaConstruct(joueurQuiJoueLaCarte);

                break;
            case 34:
                System.out.println("You may treat all Construcs as Mechana Construcs");
                break;
            case 35:
                System.out.println("Once per turn, when you acquire another Mechana Construc, you may put it directly into play");
                break;
            case 36:
                System.out.println("Once per turn ,gain 1 r . you may spend it only to acquire Mechana Construc");
                break;
            case 37:
                joueurQuiJoueLaCarte.deck.piocher(2);
                plateau.bannirUneCarteDansLaMain=true;
                break;
            case 38:
                gagnerDesDegats(joueurQuiJoueLaCarte,2);
                System.out.println("Banish a card in you hand or discard pile");
                break;
            case 39:
                gagnerDesRunes(joueurQuiJoueLaCarte,1);
                System.out.println("Banish a card in you hand or discard pile");
                break;
            case 40:
                joueurQuiJoueLaCarte.gagnerDegats(3);
                break;
            case 41:
                joueurQuiJoueLaCarte.gagnerDegats(1);
                break;
            case 42:
                System.out.println("Once per turn gain 1 The first time you deafeat a monster in the center row each turn, gain 1 R");
                break;
            case 43:
                plateau.toutGratuit=true;
                break;
            case 44:
                System.out.println("Each opponent must destryo a Construc he controls");
                break;
            case 45:
                System.out.println("Banish a card un the center row and/or in you discard pile");
                break;
            case 46:
                System.out.println("Banish a card un the center row");
                break;
            case 47:
                plateau.detruireDesConstructeurs(joueurQuiJoueLaCarte);
                break;
            case 48:
                joueurQuiJoueLaCarte.piquerDesCartes(plateau.listeDeJoueurs);
                break;
            case 49:
                plateau.acquerirHeroGratuitementLevel3OuMoins=true;
                break;
        }

    }

    public void gagner1DParTourPourChaqueMechaConstruct(Joueur joueurQuiJoueLaCarte) {
        int gainD=0;
        if (!joueurQuiJoueLaCarte.getConstruct().isEmpty()){
            for (Cards carte : joueurQuiJoueLaCarte.getConstruct()){
                if (carte.type.equals("Constructeur Mechana")){
                    gainD+=1;
                }
            }
        }

        gagnerDesDegats(joueurQuiJoueLaCarte,gainD);
    }

    public void verifLifebound(Joueur joueurQuiJoueLaCarte) {
        for (Cards carte : joueurQuiJoueLaCarte.getTapis()){
            if (carte.type.equals("Héros Lifebound")){
                joueurQuiJoueLaCarte.verifLife=true;
            }
        }

        if (joueurQuiJoueLaCarte.verifLife==true){
            gagnerDesDegats(joueurQuiJoueLaCarte,2);
            joueurQuiJoueLaCarte.verifLife=false;
        }
    }

    public void plusDeDeuxConstructeur(Joueur joueurQuiJoueLaCarte) {
        int i=0;
        if (!joueurQuiJoueLaCarte.getConstruct().isEmpty()){
            for (Cards c : joueurQuiJoueLaCarte.getConstruct()){
                if (c.type.equals("Constructeur Mechana") || c.type.equals("Constructeur")
                        || c.type.equals("Constructeur Lifebound")){
                    i++;
                }
            }
        }
        if (i>1){
            joueurQuiJoueLaCarte.piocher(1);
        }

    }


    public String getType() {
        return type;
    }
}
