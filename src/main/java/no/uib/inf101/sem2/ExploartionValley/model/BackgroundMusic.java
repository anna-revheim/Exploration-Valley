package no.uib.inf101.sem2.ExploartionValley.model;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
    private Clip music;

    public BackgroundMusic() {
        // load music file
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("GC8bit.wav"));
            music = AudioSystem.getClip();
            music.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // start music
        music.loop(Clip.LOOP_CONTINUOUSLY);
        music.start();
    }
}