package com.ecrouxvis.fr.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MenuPrincipal extends JPanel
{
    JButton boutonPartie = new JButton("Créer une partie");
    JButton boutonRejoindre = new JButton("Rejoindre une partie");
    Logo logo= new Logo();
    JPanel menu = new JPanel();

    public MenuPrincipal()
    {
        this.setBackground(new Color(0,210,255)); //couleur du background

        this.setLayout(new GridLayout(2,1));
        this.add(logo);

        menu.setBackground(new Color(0,0,0,0));
        menu.add(boutonPartie);
        menu.add(boutonRejoindre);
        this.add(menu);
    }
}


