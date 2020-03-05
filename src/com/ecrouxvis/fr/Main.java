package com.ecrouxvis.fr;

import com.ecrouxvis.fr.Controller.GameController;

import java.net.SocketException;
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
        GameController gameController = null;
        try {
            gameController = new GameController();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        System.out.print("creer (c) ou rejoindre (r) une partie");
        String choix = sc.nextLine();


        System.out.print("Entrez l'adresse IP du voisin : ");
        String ipAdrr = sc.nextLine();
        gameController.setIpVoisin(ipAdrr);

        // Création de la partie ...
        if(choix.equals("c")) { gameController.creerPartie(); }

        // Rejoindre partie
        if (choix.equals("r")) { gameController.rejoindrePartie(); }

    }
}
