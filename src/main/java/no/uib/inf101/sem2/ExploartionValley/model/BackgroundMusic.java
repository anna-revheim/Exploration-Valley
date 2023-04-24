package no.uib.inf101.sem2.ExploartionValley.model;

import java.io.InputStream;
import javax.sound.sampled.*;

public class BackgroundMusic {
    private Clip clip;

    public BackgroundMusic(String filename) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Error playing audio: " + e.getMessage());
        }
    }

    public void stop() {
        clip.stop();
    }
}