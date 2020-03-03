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

    String Partie (List joueur, List spect) {

        this.nbJoueur = joueur.size();

        

        if (this.nbJoueur == 2){
            String id_vainqueur = "";

            //partie a 2 joueurs

            return  id_vainqueur;
        }

        String id_perdant = "";
        //partie a au moins 3 joueurs
        // id_perdant = id du joueur ayant perdu

        joueur.remove(id_perdant);
        spect.add(id_perdant);

        return Partie(joueur,spect);
    }
}
