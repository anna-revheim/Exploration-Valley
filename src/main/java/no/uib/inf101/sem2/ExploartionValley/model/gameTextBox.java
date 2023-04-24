package no.uib.inf101.sem2.ExploartionValley.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameTextBox extends JFrame {
    private JTextArea textArea;
    
    /*
    * A custom dialog box that displays game text in a black background with a translucent effect.
    */
    public GameTextBox() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 200);
        setLocationRelativeTo(null);
        setOpacity(0.8f);
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(textArea, BorderLayout.CENTER);
        getContentPane().add(panel);
        setVisible(true);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "close");
        getRootPane().getActionMap().put("close", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void appendText(String text) {
        textArea.append(text);
    }
}