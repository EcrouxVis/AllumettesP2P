package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Partie;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameController {
    private Partie partie;

    // Propre au joueur
    private boolean joueurJoue = false;
    private boolean monEtat = true;         //je n'ai pas perdu, false si perdu
    private boolean premierJoueur = true;

    private String ipVoisin;

    // Sockets du joueur
    private Socket client;
    private ServerSocket listener;

    // Buffer des clients
    private BufferedReader bfr;
    private PrintWriter pw;


    /**
     * Initialise l'ip du voisin
     * @param ipVoisin l'ip du voisin
     */
    public void setIpVoisin(String ipVoisin) {
        this.ipVoisin = ipVoisin;
    }

    /**
     * Retour l'ip du voisin
     * @return l'ip du voisin
     */
    public String getIpVoisin () {
        return ipVoisin;
    }


    /**
     * Connexion à un autre hôte
     */
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

        try {
            listener = new ServerSocket(50000);
            client = listener.accept();
            System.out.println(client);

            // Initialisation des buffer du client (voisin précédent)
            // Buffer client
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            bfr = new BufferedReader(new InputStreamReader(in));
            pw = new PrintWriter(new OutputStreamWriter(out));

            // Création d'un jeton pour avoir le nombre de joueurs totaux
            int jeton = 1;
            pw.print(jeton);

            // Attente du retour du jeton
            jeton = bfr.read();
            System.out.println("Nombre de joueurs total : " + jeton);

            // Initialise le nombre de joueurs au total
            partie.setNbJoueur(jeton);

            jouerPartie();

            // Fermeture des buffers
            pw.close();
            bfr.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rejoint une partie en cours
     */
    public void rejoindrePartie() {
        partie = new Partie();

        try {
            listener = new ServerSocket(50000);
            client = listener.accept();
            System.out.println(client);

            // Initialisation des buffer du client (voisin précédent)
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            bfr = new BufferedReader(new InputStreamReader(in));
            pw = new PrintWriter(new OutputStreamWriter(out));

            premierJoueur = false;
            jouerPartie();

            // Fermeture des buffers
            pw.close();
            bfr.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Jouer une partie en tant que client
     */
    public void jouerPartie() {
        try {
            // Partie en cours
            while (partie.getNbJoueur() > 1){

                // Lecture des informations de partie

                if(premierJoueur) {
                    // Nombre de joueurs
                    int nbJoueurs = bfr.read();
                    System.out.println(nbJoueurs);
                    partie.setNbJoueur(nbJoueurs);

                    // Nombre d'allumettes restantes
                    int nbAllumettes = bfr.read();
                    System.out.print(nbAllumettes);
                    partie.setNbAllumette(nbAllumettes);
                }

                // Je ne joue que si je n'ai pas encore perdu
                if (monEtat) {
                    // attendre l'input pour enlever des allumettes
                    while (!joueurJoue) {}

                    // S'il n'y a plus d'allumettes
                    // Le joueur actuel a perdu
                    // Puis on commence une nouvelle manche
                    if (partie.getNbAllumette() == 0){
                        this.monEtat = false;
                        partie.nouvelleManche();
                    }
                }

                pw.print(getNbJoueurs());
                pw.print(getNbAllumettes());
            }
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
