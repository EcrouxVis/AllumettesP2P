package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Joueur;
import com.ecrouxvis.fr.Model.Partie;

public class GameController {
    private Partie partie;

    /**
     * Lance une partie
     */
    public void creerPartie() {
        partie = new Partie();
        partie.nouvelleManche();

        // socket TCP
    }

    /**
     * Ajoute un joueur à la partie
     * @param id l'identifiant du joueur
     * @param ipAddr : l'adresse IP du joueur
     * @return boolean en fonction du succès de l'ajout
     */
    public boolean addJoueur(int id, String ipAddr) {
        return partie.addJoueur(new Joueur(id, ipAddr));
    }

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
    public int enleverAllumettes(int nb, int tour) {
        if( nb > 0 && nb < 4 ) {
            // Plus d'allumettes --> partie terminée
            if( getNbAllumettes() == 0 ) {
                partie.nouvelleManche( partie.getJoueur(tour) );
            } else {
                partie.removeAllumette(nb);
            }
            return getNbAllumettes();
        }
        return -1; // Cas erreur
    }

    /**
     * Retourne une chaine de caractères avec le gagnant
     * @return une chaine de caractères avec les infos du gagnant
     */
    public String getGagnant() {
        return "Le joueur " + partie.getJoueur(0).toString() + " a gagné la partie !"
    }
}
