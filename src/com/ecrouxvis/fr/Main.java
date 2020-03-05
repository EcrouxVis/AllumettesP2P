package com.ecrouxvis.fr;

import com.ecrouxvis.fr.Controller.GameController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("| Bienvenue dans la partie |");
        System.out.println("============================");
        System.out.println("");

        // Initialisation du Scanner pour le clavier
        Scanner sc = new Scanner(System.in);

        // Instanciation du controller
        GameController gameController = new GameController();

        System.out.print("creer (c) ou rejoindre (r) une partie");
        String choix = sc.nextLine();


        System.out.print("Entrez l'adresse IP du voisin : ");
        String ipAdrr = sc.nextLine();
        gameController.setIpVoisin(ipAdrr);
        

        // Création de la partie ...
        if(choix.equals("c")) { gameController.creerPartie(); }

        // Rejoindre partie
        if (choix.equals("r")) { gameController.rejoindrePartie(); }

        // Connexion au voisin
        gameController.connecterClient();

        int nbAllumDem = 0;
        System.out.println("Il y a " + gameController.getNbAllumettes() + " allumettes");

        // Indique la fin de partie si égal à true
        boolean finPartie = false;

        while(!finPartie){
            do {
                System.out.println("Combien d'allumettes voulez vous enlever : 1, 2, ou 3 : ");
                nbAllumDem = sc.nextInt();
            } while(nbAllumDem<1 || nbAllumDem>3);

            int nbAllumRestantes = gameController.enleverAllumettes(nbAllumDem);

            // Si on rencontre le cas d'erreur, la partie est terminé
            if( nbAllumRestantes == -1 ) {
                finPartie = true;
            } else {
                // Sinon, la partie continue
                System.out.println("Il reste "+ nbAllumRestantes +" allumettes");
            }
        }
    }
}
