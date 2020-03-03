package com.ecrouxvis.fr.Model;

import java.util.List;

public class Partie {
    private int nbAllumette;
    private int tour = 0;
    private int maxAlumette;
    private int nbJoueur;
    private int dernier_joueur;

    public int getNbAllumette() {
        return nbAllumette;
    }

    public void setNbAllumette(int nbAllumette) {
        this.nbAllumette = nbAllumette;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public int getMaxAlumette() {
        return maxAlumette;
    }

    public void setMaxAlumette(int maxAlumette) { this.maxAlumette = maxAlumette; }

    public int getNbJoueur() { return nbJoueur; }

    public void setNbJoueur(int nbJoueur) { this.nbJoueur = nbJoueur; }

    public int getDernier_joueur() { return dernier_joueur; }

    public void setDernier_joueur(int dernier_joueur) { this.dernier_joueur = dernier_joueur; }

    public int check_etat(){
        //return null si la partie est encore en cours
        //return id du joueur perdant si la partie est finie
        if(nbAllumette==0){
            return dernier_joueur;
        }
        else
            return 1; //return id
    }

    Joueur Partie (List<Joueur> joueur, List<Joueur> spect) {   //return le joueur vainqueur

        this.nbJoueur = joueur.size();

        this.nbAllumette = this.nbJoueur * 6 + 1;

        if (this.nbJoueur == 2){
            Joueur vainqueur = null;

            //partie a 2 joueurs

            return  vainqueur;
        }

        Joueur perdant = null;
        //partie a au moins 3 joueurs
        // id_perdant = id du joueur ayant perdu

        joueur.remove(perdant);              //le joueur qui a perdu est enleve de la liste des participants
        spect.add(perdant);                  //le joueur qui a perdu est ajoute a la liste de spectateur

        return Partie(joueur,spect);            //on lance une autre partie sans le joueur qui a perdu
    }
}
