package com.ecrouxvis.fr.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HUDEtat extends JPanel
{
    Image nbJoueur;
    String nbAllumette = "15";

    public void setNbAllumette(String nbAllumette) {
        this.nbAllumette = nbAllumette;
    }

    public HUDEtat()
    {
        try {
            nbJoueur = ImageIO.read(new File(""));
            this.setBackground(new Color(0,0,0,0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g.drawImage(nbJoueur, 10,10, 150, 108, this);
        g.drawString(nbAllumette, 40, 40);

    }
}
