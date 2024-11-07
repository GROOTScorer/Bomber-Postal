package main.entities.projectiles;

import main.entities.environment.Structure;
import main.ui.Window;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Bomb {
    private int x, y;
    private int size;
    private long creationTime;
    private final long explosionDelay = 3000; // Tiempo antes de la explosión
    private boolean exploded = false;
    private Image bombImage0, bombImage1, bombImage2, bombImage3, bombImage4; // Imágenes para la secuencia de la bomba
    private Image explosionImage; // Imagen de la explosión
    private Clip placeBombSound;
    private Clip explosionSound;
    private int explosionRadius = 100; // Rango de la explosión, puedes ajustarlo

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = (int)(Window.anchoResponsive * 0.04);
        this.creationTime = System.currentTimeMillis();

        // Cargar las imágenes de la bomba y la explosión
        try {
            bombImage0 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase1.png"));
            bombImage1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase2.png"));
            bombImage2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase3.png"));
            bombImage3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase4.png"));
            bombImage4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase5.png"));
            explosionImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase5.png"));

            // Cargar los sonidos
            placeBombSound = loadSound("assets/sounds/Sonidos/place_bomb.wav");
            explosionSound = loadSound("assets/sounds/Sonidos/explosion.wav");

            // Reproducir el sonido de colocar la bomba
            placeBombSound.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Clip loadSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(List<Structure> structures) {
        if (!exploded && (System.currentTimeMillis() - creationTime) >= explosionDelay) {
            exploded = true;

            // Reproducir el sonido de la explosión
            if (explosionSound != null) {
                explosionSound.start();
            }
        }
        
        destroyStructures(structures);
    }

    // Método para destruir estructuras dentro del rango de la explosión
    public void destroyStructures(List<Structure> structures) {
        if (exploded) {
            for (Structure structure : structures) {
                if (!structure.isDestroyed() && isInRange(structure) && structure.tipo == 1) {
                    structure.setDestroyedTrue();  // Marca la estructura como destruida
                    structure.hitByBomb();

                }
            }
        }
    }

    // Verifica si la estructura está dentro del rango de la explosión
    private boolean isInRange(Structure structure) {
        int centerX = x + size / 2;
        int centerY = y + size / 2;
        int structureCenterX = structure.getX() + structure.getWidth() / 2;
        int structureCenterY = structure.getY() + structure.getHeight() / 2;

        // Calcula la distancia entre el centro de la bomba y el centro de la estructura
        double distance = Math.sqrt(Math.pow(centerX - structureCenterX, 2) + Math.pow(centerY - structureCenterY, 2));
        
        // Devuelve true si la distancia está dentro del rango de la explosión
        return distance <= explosionRadius;
    }

    public void draw(Graphics g) {
        if (exploded) {
            // Dibujar la imagen de la explosión
            g.drawImage(explosionImage, x, y, size, size, null);
        } else {
            // Determinar qué imagen mostrar basándose en el tiempo transcurrido
            long elapsedTime = System.currentTimeMillis() - creationTime;
            int phase = (int)((elapsedTime / (explosionDelay / 5)) % 5); // 5 fases para la bomba
            
            // Dibujar la imagen correspondiente a la fase de la bomba
            switch (phase) {
                case 0:
                    g.drawImage(bombImage0, x, y, size, size, null);
                    break;
                case 1:
                    g.drawImage(bombImage1, x, y, size, size, null);
                    break;
                case 2:
                    g.drawImage(bombImage2, x, y, size, size, null);
                    break;
                case 3:
                    g.drawImage(bombImage3, x, y, size, size, null);
                    break;
                case 4:
                    g.drawImage(bombImage4, x, y, size, size, null);
                    break;
            }
        }
    }

    public boolean hasExploded() {
        return exploded;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
