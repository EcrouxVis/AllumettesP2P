package com.ecrouxvis.fr.Model;

public class Joueur {
    private int id;
    private String ipAddr;

    /**
     * Retourne l'id du joueur
     * @return l'id du joueur
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne l'adresse IP du joueur
     * @return l'adresse IP du joueur
     */
    public String getIpAdress() {
        return ipAddr;
    }

    /**
     * Constructeur du joueur
     * @param id l'id du joueur
     * @param ipAddr l'adresse IP du joueur
     */
    public Joueur(int id, String ipAddr) {
        this.id = id;
        this.ipAddr = ipAddr;
    }

    /**
     * Surchage de toString
     * @return Les informations du joueur
     */
    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", ipAddr='" + ipAddr + '\'' +
                '}';
    }
}