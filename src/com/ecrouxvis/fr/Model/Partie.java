package com.ecrouxvis.fr.Model;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int nbAllumette;                     //nombre d'allumette de la partie
    private int nbJoueur;                        //nombre  de joueur de la partie

    private List<Joueur> joueur = new ArrayList<Joueur>();      //liste des joueurs de la manche
    private List<Joueur> spect = new ArrayList<Joueur>();       //liste des spectateurs de la manche (les joueurs ayant perdus)


    public boolean addJoueur (Joueur e) { return joueur.add(e); }                   //permet d'ajouter un joueur á la manche

    public boolean removeJoueur (Joueur e) { return joueur.remove(e); }             //permet de retirer un joueur á la manche

    public boolean addSpect (Joueur e) { return spect.add(e); }                     //permet d'ajouter un spectateur á la manche

    public boolean removeSpect (Joueur e) { return spect.remove(e); }               //permet de retirer un spectateur á la manche

    public int getNbAllumette() { return this.nbAllumette; }                        //renvoie le nombre d'allumette de la manche

    public void removeAllumette(int nombre) { this.nbAllumette -= nombre; }         //permet d'enlever un certain nombre d'allumette

    public void calculNbAllumette() {this.nbAllumette = this.nbJoueur * 6 + 1;}     //initialise le bon nombre d'allumette pour la manche a jouer

    public int getNbJoueur() { return nbJoueur; }                                   //renvoie le nombre de joueur de la manche

    public void calculNbJoueur() { this.nbJoueur = this.joueur.size(); }            //permet de calculer le nombre de joueur de la manche

    public Joueur getJoueur (int position) { return joueur.get(position); }         //retourne le joueur de la liste a la position cherchee

    public Partie () { }

 /*   Partie (List<Joueur> joueurs, List<Joueur> spects) {

        this.joueur = joueurs;
        this.spect = spects;

    }  */

    public void nouvelleManche(){

        calculNbJoueur();                           //met a jour le nombre de joueur pour cette manche
        calculNbAllumette();                        //met a jour le nombre d'allumette pour cette manche
    }


    public void nouvelleManche(Joueur j){

        removeJoueur(j);                            //enleve le joueur de la liste des joueur pour la manche
        addSpect(j);                                //ajoute le joueur a la liste des spectateur

        nouvelleManche();
    }





}
