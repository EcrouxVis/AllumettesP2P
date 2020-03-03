package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Joueur;
import com.ecrouxvis.fr.Model.Partie;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Partie partie;
    private List<Joueur> joueurs = new ArrayList<Joueur>();
    private List<Spectateur> spectateurs = new ArrayList<Spectateur>();

    public GameController() {
        partie = new Partie(joueurs, spectateurs);
    }

    public boolean createServer() {
        // socket TCP
        return true;
    }

    public boolean addJoueur(int id, String ipAddr) {
        return joueurs.add(new Joueur(id, ipAddr));
    }

    public boolean removeJoueur(int position) {
        return addSpectateur( joueurs.remove(position) );
    }

    public boolean addSpectateur(Joueur joueur) {
        return spectateurs.add(joueur);
    }

    public int getNbAllumettes() {
        return partie.getNbAllumette();
    }

    public int getNbJoueurs() {
        return partie.getNbJoueur();
    }

    public String getJoueur(int position) {
        if( position > 0 && position < joueurs.size() ) {
            return joueurs.get(position).toString();
        } else {
            return "Position incorrecte";
        }
    }

    public boolean enleverAllumettes(int nb) {
        if( nb < 1 || nb > 3 ) {
            partie.enleverAllumettes(nb);
            return true;
        } else {
            return false;
        }
    }
}
