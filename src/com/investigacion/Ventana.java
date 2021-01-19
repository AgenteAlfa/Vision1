package com.investigacion;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public Ventana(int a ,int b,int IMG){
        setSize(a,b);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("src/data/" + IMG);
        Graphics G = getGraphics();
        G.drawImage(icon.getImage(),0,0,null);
    }
}
