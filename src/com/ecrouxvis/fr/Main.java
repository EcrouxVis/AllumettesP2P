package com.ecrouxvis.fr;

import com.ecrouxvis.fr.Controller.GameController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //debut du programme
        System.out.println("Binevenue dans la partie : ");
        Scanner sc = new Scanner(System.in);

        GameController gameController = new GameController();

        gameController.setIpVoisin("172.20.10.7");

        gameController.connecterClient();

        //creation de la partie ...
        gameController.creerPartie();

        int nbAllumDem = 0;
        System.out.println("Il y a " + gameController.getNbAllumettes() + " allumettes");

        while(true){
            do {
                System.out.println("Combien d'allumettes voulez vous enlever : 1, 2, ou 3 : ");
                nbAllumDem = sc.nextInt();
            }while(nbAllumDem<1 || nbAllumDem>3);

            System.out.println("Il reste "+ gameController.enleverAllumettes(nbAllumDem) +" allumettes");
        }






    }
}
