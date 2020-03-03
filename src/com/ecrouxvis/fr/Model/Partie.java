package com.ecrouxvis.fr.Model;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int nbAllumette;                     //nombre d'allumette de la partie
    private int nbJoueur;                        //nombre  de joueur de la partie

    private List<Joueur> joueur = new ArrayList<Joueur>();      //liste des joueurs de la manche
    private List<Joueur> spect = new ArrayList<Joueur>();       //liste des spectateurs de la manche (les joueurs ayant perdus)


    public boolean addJoueur (Joueur e) { return joueur.add(e); }                   //permet d'ajouter un joueur á la partie

    public int getNbAllumette() { return this.nbAllumette; }                        //renvoie le nombre d'allumette de la manche

    public void removeAllumette(int nombre) { this.nbAllumette -= nombre; }         //permet d'enlever un certain nombre d'allumette

    public void calculNbAllumette() {this.nbAllumette = this.nbJoueur * 6 + 1;}     //initialise le bon nombre d'allumette pour la manche a jouer

    public int getNbJoueur() { return nbJoueur; }                                   //renvoie le nombre de joueur de la manche

    public void calculNbJoueur() { this.nbJoueur = this.joueur.size(); }            //permet de calculer le nombre de joueur de la manche


    Partie(){

    }

    Partie (List<Joueur> joueurs, List<Joueur> spects) {

        this.joueur = joueurs;
        this.spect = spects;

        this.nbJoueur = joueur.size();

        this.nbAllumette = this.nbJoueur * 6 + 1;
    }

    public Joueur jouerManche (){                   //retourne le joueur vainqueur de la manche

        calculNbJoueur();                           //met a jour le nombre de joueur pour cette manche
        calculNbAllumette();                        //met a jour le nombre d'allumette pour cette manche

        if (this.nbJoueur == 2){


            //manche a 2 joueurs



            Joueur vainqueur = null;
            return  vainqueur;
        }


        //la manche a au moins 3 joueurs





        //perdant = joueur ayant perdu
        Joueur perdant = null;
        this.joueur.remove(perdant);                //le joueur qui a perdu est enleve de la liste des participants
        this.spect.add(perdant);                    //le joueur qui a perdu est ajoute a la liste de spectateur

        return null;                                //il n'y a pas encore de vainqueur
    }



}
