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
    private BufferedImage ImgAllumette;

    public void Fenetre()
    {
        try
        {
            this.setTitle("Jeux des allumetes");
            this.ImgAllumette = ImageIO.read(new File("Images/Allumette.png"));
            this.setIconImage(this.ImgAllumette);
            this.setSize(1280,720);
            JPanel pan = new JPanel();
            pan.setBackground(Color.getColor("00d2ff"));
            this.setContentPane(pan);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
