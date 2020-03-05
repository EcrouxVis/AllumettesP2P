package com.ecrouxvis.fr.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Logo extends JPanel
{
    public void paintComponent(Graphics g)
    {
        try
        {
            Image img = ImageIO.read(new File("Images/Allumette.png"));
            g.drawImage(img, this.getWidth()/2, 0, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
