package main.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class AudioController {
    private static Clip backgroundMusic;
    private static boolean soundEnabled = true; // Indica si el sonido está habilitado
    private static float volumeLevel = 1.0f;    // Volumen en rango de 0.0 a 1.0 (100%)

    // Reproduce música de fondo
    public static void playBackgroundMusic(String filePath) {
        if (!soundEnabled) return; // Si el sonido está deshabilitado, no reproducir

        try {
            if (backgroundMusic == null || !backgroundMusic.isRunning()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioInputStream);
                setVolume(volumeLevel); // Ajustar el volumen actual
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Detiene la música de fondo
    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
            backgroundMusic = null;
        }
    }

    // Ajusta el volumen (0.0 = silencio, 1.0 = 100%)
    public static void setVolume(float level) {
        if (level < 0.0f || level > 1.0f) {
            throw new IllegalArgumentException("El nivel de volumen debe estar entre 0.0 y 1.0");
        }
        volumeLevel = level; // Guardar nivel actual

        if (backgroundMusic != null) {
            try {
                FloatControl volumeControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
                float min = volumeControl.getMinimum(); // Valor mínimo del volumen
                float max = volumeControl.getMaximum(); // Valor máximo del volumen
                float gain = min + (max - min) * volumeLevel; // Ajuste proporcional
                volumeControl.setValue(gain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Activa o desactiva el sonido globalmente
    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;

        if (!enabled) {
            stopBackgroundMusic(); // Detener cualquier audio si se desactiva el sonido
        } else if (backgroundMusic != null) {
            backgroundMusic.start(); // Reanudar si estaba pausado y el sonido vuelve a activarse
        }
    }

    // Obtiene si el sonido está habilitado
    public static boolean isSoundEnabled() {
        return soundEnabled;
    }

    // Obtiene el nivel actual de volumen
    public static float getVolume() {
        return volumeLevel;
    }
}