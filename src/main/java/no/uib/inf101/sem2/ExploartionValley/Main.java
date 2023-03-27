package no.uib.inf101.sem2.ExploartionValley;

import no.uib.inf101.sem2.view.SampleView;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        SampleView view = new SampleView();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Exploation Valley");
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
