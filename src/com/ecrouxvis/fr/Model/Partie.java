package com.ecrouxvis.fr.Model;

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

    public void setMaxAlumette(int maxAlumette) {
        this.maxAlumette = maxAlumette;
    }

<<<<<<< Updated upstream:src/com/ecrouxvis/fr/Model/Partie.java
    Partie(int nbJoueur, int nbAllumette, int maxAlumette){}
=======
    public int getNbJoueur() { return nbJoueur; }

    public void setNbJoueur(int nbJoueur) { this.nbJoueur = nbJoueur; }

    public int getDernier_joueur() { return dernier_joueur; }

    public void setDernier_joueur(int dernier_joueur) { this.dernier_joueur = dernier_joueur; }

    Partie(int nbJoueur, int nbAllumette, int maxAlumette){
        this.maxAlumette = maxAlumette;
        this.nbAllumette = nbAllumette;
        this.nbJoueur = nbJoueur;
    }

    public int check_etat(){
        //return null si la partie est encore en cours
        //return id du joueur perdant si la partie est finie
        if(nbAllumette==0){
            return dernier_joueur;
        }
        else
            return null;
    }
>>>>>>> Stashed changes:src/com/ecrouxvis/fr/Partie.java
}
