package src.main.java.org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rechtschreibtrainer {

    private int richtig= 0;
    private int falsch= 0;
    private static String eingabe;

    public int[] openFrame(String name, String url, String text) {
        JFrame frame= new JFrame("Benutzereingabe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea= new JTextArea(text+url);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);

        JScrollPane scrollPane= new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JTextField inputField= new JTextField();
        panel.add(inputField, BorderLayout.SOUTH);

        JButton okButton= new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eingabe= inputField.getText();

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
        return new int[]{richtig, falsch};
    }
}
