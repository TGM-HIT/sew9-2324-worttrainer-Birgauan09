package src.main.java.org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Diese Klasse ist für die View des Programmes zuständig.
 * @author Dawid Birgauan 5cHIT
 * @version 2023-11-22
 */

public class Rechtschreibtrainer {

    private int richtig= 0;
    private int falsch= 0;
    private static String eingabe;
    private Map<String, String> woerter;
    private Zwischen zwischen= new JSONUsage();

    /**
     * Konstruktor der Klasse Rechtschreibtrainer
     */
    public Rechtschreibtrainer() {
        woerter= zwischen.usage();
        for(Map.Entry<String, String> entry : woerter.entrySet()) {
            String name= entry.getKey();
            String url= entry.getValue();
            openFrame(name, url, "Bitte erraten Sie das Bild!:");
        }
    }

    /**
     * Diese Methode ist für die View des Programmes zuständig.
     * @param name - Name des Bildes
     * @param url - URL des Bildes
     * @param text - Text der angezeigt wird
     * @return - Gibt ein Array mit den Werten richtig und falsch zurück
     */
    public int[] openFrame(String name, String url, String text) {
        JFrame frame= new JFrame("Benutzereingabe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());

        /**Textfeld welches nicht bearbeitbar ist, um den Link zu kopieren
        JTextArea textArea= new JTextArea(text+url);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);

        JScrollPane scrollPane= new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);*/

        try {
            URL newurl= new URL(url);
            BufferedImage image= ImageIO.read(newurl);

            ImageIcon icon = new ImageIcon(image);
            JLabel label= new JLabel(icon);
            panel.add(label, BorderLayout.CENTER);
        } catch(IOException e) {
            e.printStackTrace();
        }

        JTextField inputField= new JTextField();
        panel.add(inputField, BorderLayout.SOUTH);

        //Button mit ActionListener
        JButton okButton= new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eingabe= inputField.getText(); //Eingabe des Benutzers

                //Checks über die Eingabe
                if(eingabe.equals("")) {
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie etwas ein!");
                    falsch++;
                }
                if(eingabe.toLowerCase().equals(name.toLowerCase())) {
                    JOptionPane.showMessageDialog(null, "Antwort richtig!");
                    richtig++;
                } else {
                    JOptionPane.showMessageDialog(null, "Antwort falsch!");
                    falsch++;
                }
                frame.dispose();
            }
        });
        panel.add(okButton, BorderLayout.EAST);
        frame.add(panel);
        frame.setVisible(true);
        return new int[]{richtig, falsch};  //Gibt ein Array mit den Werten richtig und falsch zurück
    }
}
