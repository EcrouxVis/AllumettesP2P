package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Joueur;
import com.ecrouxvis.fr.Model.Partie;

public class GameController {
    private Partie partie;

    /**
     * Lance une partie
     * @return
     */
    public void creerPartie() {
        partie = new Partie();

        // socket TCP
    }

    /**
     * Ajoute un joueur à la partie
     * @param id
     * @param ipAddr
     * @return
     */
    public boolean addJoueur(int id, String ipAddr) {
        return partie.addJoueur(new Joueur(id, ipAddr));
    }

    /**
     * Retourne le nombre d'allumettes
     * @return
     */
    public int getNbAllumettes() {
        return partie.getNbAllumette();
    }

    /**
     * Retourne le nombre de joueurs
     * @return
     */
    public int getNbJoueurs() {
        return partie.getNbJoueur();
    }

    /**
     * Enlève le nombre d'allumettes passé en paramètre
     * @param nb
     * @return
     */
    public int enleverAllumettes(int nb) {
        if( nb > 0 && nb < 4 ) {
            // Plus d'allumettes --> partie terminée
            if( getNbAllumettes() == 0 ) {
                partie.nouvelleManche();
            } else {
                partie.removeAllumette(nb);
            }
            return getNbAllumettes();
        }
        return -1; // Cas erreur
    }

    /**
     * Retourne une chaine de caractères avec le gagnant
     * @return
     */
    public String getGagnant() {
        return "Le joueur " + partie.getJoueur(0).toString() + " a gagné la partie !"
    }
}
