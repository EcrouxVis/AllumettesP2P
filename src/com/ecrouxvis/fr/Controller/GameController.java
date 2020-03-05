package com.ecrouxvis.fr.Controller;

import com.ecrouxvis.fr.Model.Partie;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameController {
    private Partie partie;

    // Propre au joueur
    private boolean joueurJoue = false;
    private boolean monEtat = true;         //je n'ai pas perdu, false si perdu
    private boolean premierJoueur = true;

    Scanner sca = new Scanner(System.in);

    private String ipVoisin;

    // Sockets du joueur
    private Socket client;
    private Socket client2;
    private ServerSocket listener;

    // Buffer des clients
    private BufferedReader bfr;
    private PrintWriter pw;

    public GameController() throws SocketException {
    }


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
        System.out.println("connection client");
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
        System.out.println("creation partie");
        partie = new Partie();

        try {

            listener = new ServerSocket(50000);
            client = listener.accept();
            System.out.println(client);

            // Connexion au voisin
            //this.connecterClient();
            //System.out.println("connection voisin");

            // Initialisation des buffer du client (voisin précédent)
            // Buffer client
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            bfr = new BufferedReader(new InputStreamReader(in));
            pw = new PrintWriter(new OutputStreamWriter(out));

            System.out.println("buffers crees");

            // Création d'un jeton pour avoir le nombre de joueurs totaux
            int jeton = 1;
            //pw.print(Integer.toString(jeton));
            //pw.flush();
            //System.out.println("jeton envoye");


            byte[] data = Integer.toString(jeton).getBytes();

            try {
                InetSocketAddress sa = new InetSocketAddress(getIpVoisin(), 50000);
                DatagramSocket s = new DatagramSocket();
                DatagramPacket paquet = new DatagramPacket(data, data.length, sa);
                s.send(paquet);									/*	on envoie l'entier au serveur	*/
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }

        System.out.println("jeton envoye");



            // Attente du retour du jeton
            //jeton = Integer.parseInt(bfr.readLine());;
            //System.out.println("jeton recu");
            //System.out.println("Nombre de joueurs total : " + jeton);


            DatagramSocket s = new DatagramSocket(50000);
            byte[] datarecu = new byte[8];
            DatagramPacket paquet = new DatagramPacket(datarecu, datarecu.length);


            System.out.println("Attente de réception du paquet.");
            try {
                s.receive(paquet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String st = new String(paquet.getData(), 0, paquet.getLength());
            System.out.println("J'ai reçu [" + st + "]");


            jeton = Integer.parseInt(st);
            System.out.println("jeton recu");
            System.out.println("Nombre de joueurs total : " + jeton);






            // Initialise le nombre de joueurs au total
            partie.setNbJoueur(jeton);
            partie.calculNbAllumette();

            System.out.println(getNbAllumettes());

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
        System.out.println("rejoindre partie");
        partie = new Partie();

        try {
            // Connexion au voisin
            this.connecterClient();
            System.out.println("connection voisin");

            //listener = new ServerSocket(50000);
            //client = listener.accept();
            //System.out.println(client);

            // Initialisation des buffer du client (voisin précédent)
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            bfr = new BufferedReader(new InputStreamReader(in));
            pw = new PrintWriter(new OutputStreamWriter(out));

            System.out.println("buffers crees");

            /*
            int jeton = Integer.parseInt(bfr.readLine());
            System.out.println("jeton recu");
            jeton ++;
            pw.print(String.valueOf(jeton));
            pw.flush();
            System.out.println("jeton envoye");
            */
            // Attente du retour du jeton
            //jeton = Integer.parseInt(bfr.readLine());;
            //System.out.println("jeton recu");
            //System.out.println("Nombre de joueurs total : " + jeton);


            DatagramSocket s = new DatagramSocket(50000);
            byte[] datarecu = new byte[8];
            DatagramPacket paquet = new DatagramPacket(datarecu, datarecu.length);


            System.out.println("Attente de réception du paquet.");
            try {
                s.receive(paquet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String st = new String(paquet.getData(), 0, paquet.getLength());
            System.out.println("J'ai reçu [" + st + "]");


            int jeton = Integer.parseInt(st);
            System.out.println("jeton recu");
            System.out.println("Nombre de joueurs total : " + jeton);

            jeton++;

            byte[] data = Integer.toString(jeton).getBytes();

            try {
                InetSocketAddress sa = new InetSocketAddress(getIpVoisin(), 50000);
                DatagramSocket s2 = new DatagramSocket();
                DatagramPacket paquet2 = new DatagramPacket(data, data.length, sa);
                s2.send(paquet2);									/*	on envoie l'entier au serveur	*/
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }

            System.out.println("jeton envoye");






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
        System.out.println("jouer partie");
        try {
            // Partie en cours
            do{

                // Lecture des informations de partie

                if(!premierJoueur) {

                    System.out.println("reception infos");

                    // Nombre de joueurs
                    //int nbJoueurs = Integer.parseInt(bfr.readLine());
                    //System.out.println(nbJoueurs);
                    //partie.setNbJoueur(nbJoueurs);


                    DatagramSocket s = new DatagramSocket(50000);
                    byte[] datarecu = new byte[8];
                    DatagramPacket paquet = new DatagramPacket(datarecu, datarecu.length);


                    System.out.println("Attente de réception du paquet.");
                    try {
                        s.receive(paquet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String st = new String(paquet.getData(), 0, paquet.getLength());
                    System.out.println("J'ai reçu [" + st + "]");


                    int nbJoueurs = Integer.parseInt(st);
                    System.out.println(nbJoueurs);
                    partie.setNbJoueur(nbJoueurs);




                    // Nombre d'allumettes restantes
                    //int nbAllumettes = Integer.parseInt(bfr.readLine());
                    //System.out.print(nbAllumettes);
                    //partie.setNbAllumette(nbAllumettes);


                    DatagramSocket s2 = new DatagramSocket(50000);
                    byte[] datarecu2 = new byte[8];
                    DatagramPacket paquet2 = new DatagramPacket(datarecu, datarecu.length);


                    System.out.println("Attente de réception du paquet.");
                    try {
                        s.receive(paquet2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String st2 = new String(paquet2.getData(), 0, paquet2.getLength());
                    System.out.println("J'ai reçu [" + st2 + "]");


                    int nbAllumettes = Integer.parseInt(st);
                    System.out.println(nbAllumettes);
                    partie.setNbJoueur(nbAllumettes);





                }

                premierJoueur=false;

                // Je ne joue que si je n'ai pas encore perdu
                if (monEtat) {

                    enleverAllumettes(retirerAllumette());

                    //while (!joueurJoue) {}

                    // S'il n'y a plus d'allumettes
                    // Le joueur actuel a perdu
                    // Puis on commence une nouvelle manche
                    if (partie.getNbAllumette() == 0){
                        this.monEtat = false;
                        System.out.println("j'ai perdu");
                        partie.nouvelleManche();
                    }
                }
                System.out.println("envoi des infos");
                //pw.print(getNbJoueurs());
                //pw.print(String.valueOf(getNbJoueurs()));
                //pw.flush();

                byte[] data = Integer.toString(getNbJoueurs()).getBytes();

                try {
                    InetSocketAddress sa = new InetSocketAddress(getIpVoisin(), 50000);
                    DatagramSocket s2 = new DatagramSocket();
                    DatagramPacket paquet2 = new DatagramPacket(data, data.length, sa);
                    s2.send(paquet2);									/*	on envoie l'entier au serveur	*/
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }



                byte[] data2 = Integer.toString(getNbAllumettes()).getBytes();

                try {
                    InetSocketAddress sa = new InetSocketAddress(getIpVoisin(), 50000);
                    DatagramSocket s2 = new DatagramSocket();
                    DatagramPacket paquet2 = new DatagramPacket(data2, data2.length, sa);
                    s2.send(paquet2);									/*	on envoie l'entier au serveur	*/
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }


                //pw.print(String.valueOf(getNbAllumettes()));
                //pw.flush();
            } while (partie.getNbJoueur() > 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("fin de jouer partie");
    }

    private int retirerAllumette() {

        int nbAllumDem = 0;

        do {
            System.out.println("Combien d'allumettes voulez vous enlever : 1, 2, ou 3 : ");
            nbAllumDem = sca.nextInt();
        } while(nbAllumDem<1 || nbAllumDem>3);
        return nbAllumDem;
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
        System.out.println("enlever allumette");
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
