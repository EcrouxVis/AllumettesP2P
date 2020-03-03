package com.ecrouxvis.fr.Model;

public class Partie {
    private int nbAllumette;
    private int tour = 0;
    private int maxAlumette;
    private int nbJoueur;

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

    Partie(int nbJoueur, int nbAllumette, int maxAlumette){}
}
