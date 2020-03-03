package com.ecrouxvis.fr.View;

import com.sun.jdi.PathSearchingVirtualMachine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fenetre extends JFrame
{

    public Fenetre()
    {
        try
        {
            this.setTitle("Allumettes THE VIDEO GAME");
            BufferedImage imgAllumette = ImageIO.read(new File("Images/Allumette.png"));
            this.setIconImage(imgAllumette);
            this.setSize(1280,720);
            this.setLocationRelativeTo(null); //permet d'afficher la fenêtre au centre
            JPanel pan = new JPanel();
            JButton bouton_partie = new JButton("Créer une partie");
            JButton bouton_rejoindre = new JButton("Rejoindre une partie");
            pan.setBackground(new Color(0,210,255)); //couleur du background
            this.setContentPane(pan);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);

            //Ajout du bouton à notre content pane
            pan.add(bouton_partie);
            this.setContentPane(pan);
            this.setVisible(true);

            //Ajout du bouton à notre content pane
            pan.add(bouton_rejoindre);
            this.setContentPane(pan);
            this.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Fenetre fenetre = new Fenetre();
    }
}
