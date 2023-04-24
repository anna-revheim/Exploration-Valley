package no.uib.inf101.sem2.ExploartionValley.model;

import java.io.*;
import javax.sound.sampled.*;

public class AudioPlayer {
    private Clip clip;
    
    public void play(String fileName, double volume) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        clip.stop();
    }
}









/* 
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
    private Clip music;

    public BackgroundMusic() {
        // load music file
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sound/music/GC8bit.wav"));
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
*/