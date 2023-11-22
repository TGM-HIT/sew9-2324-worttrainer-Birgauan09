package src.main.java.org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse ist für die View des Programmes zuständig.
 * @author Dawid Birgauan 5cHIT
 * @version 2023-10-18
 */

public class Rechtschreibtrainer {

    private int richtig= 0;
    private int falsch= 0;
    private static String eingabe;

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

        //Textfeld welches nicht bearbeitbar ist
        JTextArea textArea= new JTextArea(text+url);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);

        JScrollPane scrollPane= new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

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
                    textArea.setText("Antwort falsch! Nochmal versuchen: "+url);
                }
                if(eingabe.toLowerCase().equals(name.toLowerCase())) {
                    textArea.setText("Antwort richtig! Weiter mit Enter!");
                    richtig++;
                } else {
                    textArea.setText("Antwort falsch! Nochmal versuchen: "+url);
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
