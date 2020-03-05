package com.ecrouxvis.fr.View;

import com.sun.jdi.PathSearchingVirtualMachine;

import javax.imageio.ImageIO;
import javax.management.InvalidApplicationException;
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
            Image imgAllumette = ImageIO.read(new File("Images/Allumette.png"));
            this.setIconImage(imgAllumette);
            this.setSize(1280,720);
            this.setLocationRelativeTo(null); //permet d'afficher la fenêtre au centre
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            this.setContentPane(menuPrincipal);
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
