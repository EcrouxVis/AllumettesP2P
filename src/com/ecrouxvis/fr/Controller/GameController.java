package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Joueur;
import com.ecrouxvis.fr.Model.Partie;

public class GameController {
    private Partie partie;
    boolean joueurJoue = false;
    boolean monEtat = true;         //je n'ai pas perdu, false si perdu

    String ip_voisin = "";


    public String getIp_voisin () {
        //vue fait ton taf

        return ip_voisin;
    }


    /**
     * Lance une partie
     */
    public void creerPartie() {
        partie = new Partie();
        partie.nouvelleManche();

        while (partie.getNbJoueur() > 0){
            //attendre jusqu'a lire info
            int[] tab = received();
            partie.setEtat(tab);

            if (monEtat){           //je ne joue que si je n'ai pas encore perdu
                //jouer
                while (!joueurJoue) {
                    //...
                }
                //lire info vue
                int nombreLue = 0;
                enleverAllumettes(nombreLue);
                if (partie.getNbAllumette() == 0){
                    this.monEtat = false;
                    partie.nouvelleManche();
                }
            }

            send(partie.getEtat());
        }

        // socket TCP
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
