import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by user on 20/05/2016.
 */
public class Vue extends JFrame {
    protected JPanel all;
    protected JPanel gen;
    protected JMenuBar menu;
    protected JPanel cadreTemps;
    protected JButton button;
    public JTextField textField;
    public String t;

    public ControlButton appuie;
    public JTextField txtTourJoueur;
    public JPanel contentPane;
    public JTextField txtTourjoueur;
    public JButton btnDeck;
    public JPanel panelMainJoueur;
    public JLabelV2 carteMainJoueur;
    public JPanel panelTapis;
    public JButton btnNeant;
    public JButton btnPasser;
    public JButton btnDefausse;
    public JLabelV2[] tableauCarteCentrale;
    public JPanel pane;
    public JLabel jlabelRunesEtAttaqueDispo;
    public JLabelV2 carteCentrale;
    public ControlButton cb;
    public Modele m;
    public JPanel panelLigneCentrale;
    public JLabel jlabelPtsHonneur;
    public JLabel jb;
    public JLabel carteMystique;
    public JLabel carteInfanteriesLourdes;
    public JLabel carteCultiste;
    public ControlMouse ctrm;
    public int imgWidth;
    public int imgHeight;
    public int positionCarteHabitantEnY;
    public int positionCarteHabitantEnX;
    public int positionCarteCentraleX;
    public int positionCarteCentraleY;
    public ArrayList<JLabelV2> listCarteDansLaMain;
    public JLabelV2 carteTapis;
    public ArrayList<JLabelV2> listCarteSurLeTapis;
    public int posXRetA;
    public int posYRetA;
    public JPanel panelMenu;
    public JButton jbuttonJouer;
    public JButton jbuttonQuitter;
    private JPanel panelJoueur;
    private JLabel jouer1Jln;
    private JLabel jouer2Jln;
    public JButton jbuttAddJoueur;
    private JLabel jouerJln;
    public ArrayList<JLabelV2> listeCarteConstructeur;
    public JPanel panelConstructeur;
    public JLabelV2 carteConstruct;
    public JButton btnDeConstruct;
    public JPanel panelDefausse;
    public ArrayList<JLabelV2> listeCarteDefausse;
    public JLabelV2 carteDefausse;
    private JLabel panelPhoto;
    private JPanel panelDeFin;
    private JTextField textDeFin;
    private JLabelV2 jlbaledDeFin;
    private JLabel panelGagnant;
    public JLabel jlabelPtsHonneurPlat;


    public Vue(Modele m){
        this.m=m;
        init(m);
        creerWidget(m);
//        creerMenu(m);
        setSize(1200, 650);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void creerMenu(Modele m) {
        cb=new ControlButton(this,m);
        panelMenu= new JPanel();
        setContentPane(panelMenu);

        jbuttonJouer=new JButton("Jouer");
        jbuttonJouer.addActionListener(cb);
        panelMenu.add(jbuttonJouer);
        jbuttonQuitter=new JButton("Quitter");
        jbuttonQuitter.addActionListener(cb);
        panelMenu.add(jbuttonQuitter);

    }


    public void creerLesJoueurs(Modele m) {
        cb=new ControlButton(this,m);
        setSize(1200, 650);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelJoueur=new JPanel();
        setContentPane(panelJoueur);

        jouer1Jln=new JLabel("Joueur 1");
        jouer2Jln=new JLabel("Joueur 2");
        panelJoueur.add(jouer1Jln);
        panelJoueur.add(jouer2Jln);

        jbuttAddJoueur=new JButton("Ajouter  un joueur");
        jbuttAddJoueur.addActionListener(cb);
        panelJoueur.add(jbuttAddJoueur);



    }


    public void init(Modele m){
        setSize(1200, 650);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        cb=new ControlButton(this,m);
        ctrm=new ControlMouse(this,m);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 653, 480);

        ///////// Taille des images///////////
        imgWidth=140;
        imgHeight=195;

        //// Positions des Habitants ////////
        positionCarteHabitantEnX=1200-(imgWidth/2);
        positionCarteHabitantEnY=5;

        //// Positions Des Cartes Centrales //////
        positionCarteCentraleX=125;
        positionCarteCentraleY=5;

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentPane(contentPane);
        contentPane.setLayout(null);


//        test.setBounds();
//        contentPane.setBackground(Color.black);
        listeCarteConstructeur=new ArrayList<JLabelV2>();
        listeCarteDefausse=new ArrayList<JLabelV2>();
//        JPanel habitants = new JPanel();
//        habitants.setBounds(40, 20, 600, 250);
//        contentPane.add(habitants);
//        habitants.setLayout(new GridLayout(1, 0, 0, 0));

/////////////////////      Carte Mystic / Mystique            //////////////////////////////////////////////////////////////////////////////
        Icon icon=new ImageIcon("./img/Mystic-H.jpg");
        carteMystique = new JLabel(icon);
        carteMystique.setOpaque(false);
        carteMystique.addMouseListener(ctrm);
        carteMystique.setBounds(positionCarteHabitantEnX,positionCarteHabitantEnY,imgHeight,imgWidth);
        contentPane.add(carteMystique);

      /////////////////////      Carte Infanteries lourdes        //////////////////////////////////////////////////////////////////////////////
        icon=new ImageIcon("./img/Heavy-Infantry-H.jpg");
        carteInfanteriesLourdes = new JLabel(icon);
        carteInfanteriesLourdes.setOpaque(false);
        carteInfanteriesLourdes.addMouseListener(ctrm);
        carteInfanteriesLourdes.setBounds(positionCarteHabitantEnX,positionCarteHabitantEnY +(imgWidth+5),imgHeight,imgWidth);
        contentPane.add(carteInfanteriesLourdes);

      /////////////////////      Carte Cultiste        //////////////////////////////////////////////////////////////////////////////
        icon=new ImageIcon("./img/Cultist.jpg");
        carteCultiste = new JLabel(icon);
        carteCultiste.setOpaque(false);
        carteCultiste.addMouseListener(ctrm);
        carteCultiste.setBounds(positionCarteHabitantEnX,positionCarteHabitantEnY+(2*imgWidth+2*5),imgHeight,imgWidth);
        contentPane.add(carteCultiste);

//////////////////////////////////////////  Ligne  des Cartes Centrale ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        panelLigneCentrale = new JPanel();
//        panelLigneCentrale.setBounds(40, 137, 1150, 74);
//        contentPane.add(panelLigneCentrale);
//        panelLigneCentrale.setLayout(new GridLayout(1, 0, 0, 0));


        tableauCarteCentrale =new JLabelV2[6];
        for (int i=0; i<6; i++){
            icon=new ImageIcon("./img/"+m.p.ligneCentrale[i].getNom()+".jpg");
            carteCentrale = new JLabelV2(icon,i);
            carteCentrale.setOpaque(false);
            carteCentrale.addMouseListener(ctrm);
            carteCentrale.setBounds(positionCarteCentraleX+(i*imgWidth+i*10),positionCarteCentraleY,imgWidth,imgHeight);

            tableauCarteCentrale[i]= carteCentrale;
//            panelLigneCentrale.add(carteCentrale);
            contentPane.add(tableauCarteCentrale[i]);

        }

        ///////////////////////// Carte Dans la Mains du joueur //////////////////////////////////////////////////////

        panelMainJoueur=new JPanel();

        panelMainJoueur.setLayout(new GridLayout(1, 0, 0, 0));
        panelMainJoueur.setBounds(120,600-imgWidth+50,1000,imgWidth+50);
        creationMainJoueur();
        contentPane.add(panelMainJoueur);

        /////////////////////////               Tapis               //////////////////////////////////////////////////////

        panelTapis=new JPanel();
        panelTapis.setLayout(new GridLayout(1,0,0,0));
        panelTapis.setBackground(Color.RED);
        panelTapis.setBounds(120,205,1000,imgWidth+75);
        listCarteSurLeTapis=new ArrayList<JLabelV2>();
        contentPane.add(panelTapis);

     /////////////////////////               Constructeur               //////////////////////////////////////////////////////

        panelConstructeur=new JPanel();

     /////////////////////////               Defausse               //////////////////////////////////////////////////////

        panelDefausse=new JPanel();


//        btnNeant = new JButton("Neant");
//        btnNeant.addActionListener(cb);
//        btnNeant.setBounds(10, 10, 100, 52);
//        btnNeant.setForeground(Color.magenta);
//        contentPane.add(btnNeant);

//        btnDeck = new JButton("deck");
//        btnDeck.addActionListener(cb);
//        btnDeck.setBounds(10, 600, 100, 52);
//        contentPane.add(btnDeck);

        btnDefausse = new JButton("Défausse");
        btnDefausse.setBounds(1200, 440, 150, 52);
        btnDefausse.addActionListener(cb);
        contentPane.add(btnDefausse);

        btnPasser = new JButton("passer");
        btnPasser.addActionListener(cb);
        btnPasser.setBounds(10, 70, 100, 52);
        contentPane.add(btnPasser);

        btnDeConstruct = new JButton("Constructeur");
        btnDeConstruct.setBounds(1200, 500, 150, 52);
        btnDeConstruct.addActionListener(cb);
        contentPane.add(btnDeConstruct);

        jb= new JLabel("Tour du joueur : " + m.joueurActuel().nomJoueur);
        jb.setBounds(10, 190, 200, 52);

        jlabelPtsHonneur= new JLabel("Points d'Honneur : " + m.joueurActuel().getPtsHonneur());
        jlabelPtsHonneur.setBounds(10, 475, 200, 52);
        jlabelPtsHonneurPlat= new JLabel("Points d'Honneur Restant : " + m.p.getPtsHonneur());
        jlabelPtsHonneurPlat.setBounds(10, 425, 200, 52);

        contentPane.add(jlabelPtsHonneur);
        contentPane.add(jlabelPtsHonneurPlat);

        jlabelRunesEtAttaqueDispo= new JLabel("Rune :  " + m.joueurActuel().nbRunesDispo + "  Power : " + m.joueurActuel().attaqueDispo);
        posXRetA=10;
        posYRetA=462;
        jlabelRunesEtAttaqueDispo.setForeground(Color.RED);
        Icon iconeD=new ImageIcon("./img/power.png");
//        jlabelRunesEtAttaqueDispo.setIcon(iconeD);
        jlabelRunesEtAttaqueDispo.setBounds(posXRetA,posYRetA,500,52);

        contentPane.add(jlabelRunesEtAttaqueDispo);
        contentPane.add(jb);


    }



    public void display() {

        setVisible(true);
    }

    public void undisplay() {

        setVisible(false);
    }


    public void creerWidget(Modele m){
        setContentPane(contentPane);
    }








    public void actualiserJ(Joueur joueur, Modele m){
        jlabelRunesEtAttaqueDispo.setText("Rune :  " + joueur.getRunes() + "  Power : " + joueur.attaqueDispo);


        jlabelPtsHonneur.setText("Points d'Honneur : " + m.joueurActuel().getPtsHonneur());
        jlabelPtsHonneurPlat.setText("Points d'Honneur Restant : " + m.p.getPtsHonneur());



        invalidate();
        validate();
        repaint();

    }

    public void actualiserRunesEtAttaqueJoueur(Joueur joueur,Modele m) {
        jlabelRunesEtAttaqueDispo.setText("Rune :  " + joueur.getRunes() + "  Power : " + joueur.attaqueDispo);
        jlabelPtsHonneurPlat.setText("Points d'Honneur Restant : " + m.p.getPtsHonneur());
        invalidate();
        validate();
        repaint();
    }





    public void actualiserPtsHonneur(Joueur joueur, Modele m) {
//        contentPane.remove(jlabelPtsHonneur);
//        jlabelPtsHonneur= new JLabel("Points d'Honneur : " + m.joueurActuel().getPtsHonneur());
//        jlabelPtsHonneur.setBounds(1200,460,500,52);
//        contentPane.add(jlabelPtsHonneur);

        jlabelPtsHonneur.setText("Points d'Honneur : " + m.joueurActuel().getPtsHonneur());

        invalidate();
        validate();
        repaint();
    }


    public String getNomCarteActuel(int i) {
        return m.getNomCarteActuel(i);
    }



    public void actualiserMain(Joueur joueur, Modele m) {
        creationMainJoueur();
        creationTapis();

        invalidate();
        validate();
        repaint();

    }

    public void creationMainJoueur() {
        listCarteDansLaMain=new ArrayList<JLabelV2>();
        listCarteDansLaMain.clear();
        panelMainJoueur.removeAll();
        for (int i=0; i< m.joueurActuel().getHand().size();i++){
            Icon icon=new ImageIcon("./img/"+getNomCarteActuel(i)+".jpg");
            carteMainJoueur=new JLabelV2(icon,i);

            listCarteDansLaMain.add(carteMainJoueur);
            listCarteDansLaMain.get(i).addMouseListener(ctrm);
            panelMainJoueur.add(carteMainJoueur);
        }
    }

    public void creationTapis() {
        listCarteSurLeTapis.clear();
        panelTapis.removeAll();
            for (int i =0; i< m.joueurActuel().getTapis().size();i++){
                Icon icon=new ImageIcon("./img/"+getNomCarteTapis(i)+".jpg");
                carteTapis=new JLabelV2(icon,i);
                listCarteSurLeTapis.add(carteTapis);
                panelTapis.add(carteTapis);
            }

        }

    private String getNomCarteTapis(int i) {
        return m.getNomCarteTapis(i);
    }


    public void supprimerListener(int jlbv2) {
        for (JLabelV2 jLabelV2 : listCarteDansLaMain){
            if (jLabelV2.getId()==jlbv2){
                jLabelV2.removeMouseListener(ctrm);
            }
        }
    }

    public void jouerUneCarte(int id) {
        listCarteDansLaMain.remove(id);
        creationMainJoueur();
        creationTapis();

        invalidate();
        repaint();
        validate();
    }

    public void actualiserLigneCentrale(int i) {
        contentPane.remove(tableauCarteCentrale[i]);
        Icon icon=new ImageIcon("./img/"+m.p.ligneCentrale[i].getNom()+".jpg");
        tableauCarteCentrale[i]=new JLabelV2(icon,i);
        tableauCarteCentrale[i].setOpaque(false);
        tableauCarteCentrale[i].addMouseListener(ctrm);
        tableauCarteCentrale[i].setBounds(positionCarteCentraleX+(i*imgWidth+i*10),positionCarteCentraleY,imgWidth,imgHeight);
        contentPane.add(tableauCarteCentrale[i]);

        invalidate();
        repaint();
        validate();

    }

    public void actualiserTour(Modele m){
        jb.setText("Tour du joueur : " + m.joueurActuel().nomJoueur);
        invalidate();
        repaint();
        validate();


    }

    public void bannirUneCarte() {

        contentPane.setBackground(Color.BLUE);
        for (int i=0;i<tableauCarteCentrale.length;i++){
            tableauCarteCentrale[i].setBackground(Color.MAGENTA);
        }

    }


    public void creerUnJenPlus() {
        jouerJln=new JLabel("Joueur"+m.p.listeDeJoueurs.size()+1);
        panelJoueur.add(jouerJln);
    }

    public void creationCopierDesEffets() {

        for(int i=0; i<listCarteSurLeTapis.size();i++){
            listCarteSurLeTapis.get(i).addMouseListener(ctrm);
        }

        invalidate();
        repaint();
        validate();
    }

    public void finDeLaCopie() {
        for (JLabelV2 jb : listCarteSurLeTapis){
            jb.removeMouseListener(ctrm);
        }
    }

    public void creerConstructeur() {
        listeCarteConstructeur.clear();
        panelConstructeur.removeAll();

        for (int i =0; i< m.joueurActuel().getConstruct().size();i++){
            Icon icon=new ImageIcon("./img/"+getNomCarteConstruct(i)+".jpg");
            carteConstruct=new JLabelV2(icon,i);
            listeCarteConstructeur.add(carteConstruct);
            listeCarteConstructeur.get(i).addMouseListener(ctrm);
            panelConstructeur.add(carteConstruct);
        }

        invalidate();
        repaint();
        validate();
    }

    public void creerDefausse() {
        listeCarteDefausse.clear();
        panelDefausse.removeAll();

        for (int i =0; i< m.joueurActuel().getDefausse().size();i++){
            Icon icon=new ImageIcon("./img/"+getNomCarteDefausse(i)+".jpg");
            carteDefausse=new JLabelV2(icon,i);
            listeCarteDefausse.add(carteDefausse);
            panelDefausse.add(carteDefausse);
        }

        invalidate();
        repaint();
        validate();
    }

    private String getNomCarteDefausse(int i) {
        return m.joueurActuel().getDefausse().get(i).getNom();
    }


    private String getNomCarteConstruct(int i) {
        return m.joueurActuel().getConstruct().get(i).getNom();
    }

    public void afficherConstruct() {

        panelConstructeur.setBackground(Color.GREEN);
        panelConstructeur.setLayout(new GridLayout(1, 0, 0, 0));
        panelConstructeur.setOpaque(true);
        panelConstructeur.setBounds(120,450-imgWidth+50,1000,imgWidth+50);
        contentPane.add(panelConstructeur,1);

        creerConstructeur();
        invalidate();
        repaint();
        validate();
    }

    public void OffConstruct() {
        contentPane.remove(panelConstructeur);
        OffDefausse();

        invalidate();
        repaint();
        validate();
    }

    public void OffDefausse() {
        contentPane.remove(panelDefausse);

        invalidate();
        repaint();
        validate();
    }

    public void afficherDefausse() {

        OffConstruct();

        panelDefausse.setBackground(new Color(109, 173, 230));
        panelDefausse.setLayout(new GridLayout(1, 0, 0, 0));
        panelDefausse.setOpaque(true);
        panelDefausse.setBounds(120,450-imgWidth+50,1000,imgWidth+50);
        contentPane.add(panelDefausse,1);

        creerDefausse();
        invalidate();
        repaint();
        validate();
    }


    public void finDeLaPartie() {
        contentPane.removeAll();
        panelDeFin=new JPanel();
        panelDeFin.setLayout(new GridLayout(0,1,0,0));
        panelDeFin.setBounds(100,250,300,100);
        m.p.direQuiGagne(m.p.listeDeJoueurs);
        panelGagnant= new JLabel("Le Gagnant est donc " + m.p.gagne());
        panelGagnant.setForeground(Color.RED);
        for (int i=0; i<m.p.listeDeJoueurs.size();i++){
            jlbaledDeFin=new JLabelV2(m.p.listeDeJoueurs.get(i).getNom() + " possède " + m.p.listeDeJoueurs.get(i).calculerPtsHonneurFinaux() + " Points d'Honneur. ",i);
            panelDeFin.add(jlbaledDeFin);


        }
        panelGagnant.setBounds(900,250,300,100);
        contentPane.add(panelGagnant);
        contentPane.add(panelDeFin);
        invalidate();
        repaint();
        validate();
    }


}