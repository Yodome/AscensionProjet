public class Partie {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Modele m = new Modele();
                ControlGroup controler=new ControlGroup(m);

            }
        });
    }









//        /////////////////////// Choisir son deck , initialisé les decks //////////////////////////////////
//        for (Joueur j : plateau.listeDeJoueurs){
//            j.deck.initialiserDeck();
//        }
//
////        // Répartir les points entre les joueurs
//        plateau.repartirLesPointsAuxJoueurs();
////
////        // Détermine le premier joueur
//        plateau.quiCommence(plateau.listeDeJoueurs);
////
////        // Creéer la ligne centrale C FAIT
//       plateau.faireLaLigneCentrale();
////
////        // Creéer les habitants
//     plateau.creerLesHabitants();
//////        // le jeu
////      //  plateau.gereLeJeu(plateau.listeDeJoueurs);
////
//        j1.deck.piocherMain();
//        plateau.afficherLaLigneCentrale();
//
//        Cards c1=j1.deck.hand.get(0);
//        c1.print();
//        j1.jouerUneCarte(c1);
//        System.out.println("nb Runes " + j1.nbRunesDispo);
//        System.out.println("nb Attaque " + j1.attaqueDispo);
//        System.out.println("nb T " + j1.deck.hand.size());
//
//        j1.acquerirUneCarteRunes(plateau.ligneCentrale[0],plateau);
//        System.out.println("nb T " + j1.deck.hand.size());
//
////       j1.deck.piocher(1);
//       j1.deck.afficherLesCartesDansSaMain();


}

