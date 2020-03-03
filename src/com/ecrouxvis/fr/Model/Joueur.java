package com.ecrouxvis.fr.Model;

public class Joueur {
    private int id;
    private String ipAdress;
    public int nbAllumetteRestant;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    Joueur(int id, string ipAdresse) {
        this.id = id;
        this.ipAdress = ipAdresse;
    }

    public void jouer(int allumetteEnlever) {
        this.nbAllumetteRestant -= allumetteEnlever;
    }
}