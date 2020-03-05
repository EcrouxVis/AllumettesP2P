package com.ecrouxvis.fr.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FenetreJeux extends JPanel
{
    HUDEtat hudEtat = new HUDEtat();

    public FenetreJeux()
    {
        this.setBackground(new Color(0,210,255));
        this.add(hudEtat);
        jouer();
    }

    private void jouer()
    {

    }


}
