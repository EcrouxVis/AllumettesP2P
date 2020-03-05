package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Joueur;
import com.ecrouxvis.fr.Model.Partie;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class GameController {
    private Partie partie;
    boolean joueurJoue = false;
    boolean monEtat = true;         //je n'ai pas perdu, false si perdu

    private String ipVoisin;

    Socket client;
    ServerSocket listener;


    public void setIpVoisin(String ipVoisin) {
        this.ipVoisin = ipVoisin;
    }

    public String getIpVoisin () {
        return ipVoisin;
    }


    public void connecterClient() {
        try {
            client = new Socket( getIpVoisin(), 50000 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance une partie
     */
    public void creerPartie() {
        partie = new Partie();
        partie.nouvelleManche();

        try {
            listener = new ServerSocket(50000);
            client = listener.accept();
            System.out.println(client);

            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();

            BufferedReader bfr = new BufferedReader(new InputStreamReader(in));

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));

            while (partie.getNbJoueur() > 0){

                //attendre jusqu'a lire info
                int nbJoueurs = bfr.read();
                partie.setNbJoueur(nbJoueurs);
                int nbAllumettes = bfr.read();
                partie.setNbAllumette(nbAllumettes);

                if (monEtat){           //je ne joue que si je n'ai pas encore perdu
                    // attendre l'input
                    while (!joueurJoue) {
                        //...
                    }

                    if (partie.getNbAllumette() == 0){
                        this.monEtat = false;
                        partie.nouvelleManche();
                    }
                }

                pw.print(getNbJoueurs());
                pw.print(getNbAllumettes());
            }

            pw.close();
            bfr.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un joueur à la partie
     * @param id l'identifiant du joueur
     * @param ipAddr : l'adresse IP du joueur
     * @return boolean en fonction du succès de l'ajout
     */
/*
    public boolean addJoueur(int id, String ipAddr) {
        return partie.addJoueur(new Joueur(id, ipAddr));
    }
*/
    /**
     * Retourne le nombre d'allumettes
     * @return le nombre d'allumettes actuellement en jeu
     */
    public int getNbAllumettes() {
        return partie.getNbAllumette();
    }

    /**
     * Retourne le nombre de joueurs
     * @return le nombre de joueurs actuellement en jeu
     */
    public int getNbJoueurs() {
        return partie.getNbJoueur();
    }

    /**
     * Enlève le nombre d'allumettes passé en paramètre
     * @param nb le nombre d'allumettes à enlever
     * @return le nombre d'allumettes en jeu ou -1 si erreur
     */
    public int enleverAllumettes(int nb) {
        if( nb > 0 && nb < 4 ) {
            joueurJoue = true;
            partie.removeAllumette(nb);
            return getNbAllumettes();
        }
        return -1; // Cas erreur
    }

    /**
     * Retourne une chaine de caractères avec le gagnant
     * @return une chaine de caractères avec les infos du gagnant
     */
/*
    public String getGagnant() { return "Le joueur " + partie.getJoueur(0).toString() + " a gagné la partie !"; }
*/

}
