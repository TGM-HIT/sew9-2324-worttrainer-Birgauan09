package src.main.java.org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Diese Klasse ist der Einstiegspunkt des Programms.
 * @author Dawid Birgauan 5cHIT
 * @version 2023-10-18
 */

public class Main {

    public static void main(String[] args) {

        String imageUrl= "https://png.pngtree.com/png-vector/20210924/ourmid/pngtree-pure-water-splash-png-image_3944713.jpg";
        try {
            URL url= new URL(imageUrl);
            BufferedImage image= ImageIO.read(url);

            ImageIcon icon = new ImageIcon(image);
            JFrame frame= new JFrame();
            JLabel label= new JLabel(icon);
            frame.add(label);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch(IOException e) {
            e.printStackTrace();
        }
        //new Rechtschreibtrainer();
    }
}
