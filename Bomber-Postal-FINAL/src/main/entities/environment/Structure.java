package main.entities.environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.entities.Power_Up;
import main.ui.Window;
import main.utils.FloatingText;

public class Structure {
    public int x, y, tipo;
    public int width, height;
    private Color color; // Color opcional si no se proporciona imagen
    public Image image; // Imagen opcional
    public int bulletHits; // Contador de impactos de balas
    private boolean destroyed; // Para saber si la estructura está destruida
    private List<Power_Up> powerUps;
    private List<FloatingText> floatingTexts = new ArrayList<>();
    
    private Clip destroySound; // Sonido de destrucción
    
    // Constructor que acepta imagen
    public Structure(int x, int y, int width, int height, Image image, int tipo, List<Power_Up> powerUps) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.color = null; // No hay color en este caso
        this.tipo = tipo;
        this.bulletHits = 0; // Inicialmente no ha recibido impactos
        this.destroyed = false; // No está destruida inicialmente
        this.powerUps = powerUps;
        this.destroySound = loadSound("assets/sounds/Sonidos/rock-destroy-6409.wav");
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

    // Método que dibuja la estructura
public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g; // Convertir a Graphics2D para usar AlphaComposite

    if (!destroyed) { // Solo dibuja si no está destruida
        if (bulletHits == 1) {
            // Configurar opacidad al 50% (0.5f)
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        if (image != null) {
            // Dibujar imagen con la opacidad configurada
            g2d.drawImage(image, x, y, width, height, null);
        } else if (color != null) {
            // Dibujar el rectángulo con color si no hay imagen
            g2d.setColor(color);
            g2d.fillRect(x, y, width, height);
        } else {
            // Si no hay imagen ni color, dibujar un fondo verde
            g2d.setColor(Color.GREEN);
            g2d.fillRect(x, y, width, height);
        }

        // Restaurar la opacidad completa si se cambió
        if (bulletHits == 1) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    } else {
        // Mover fuera de pantalla si está destruida
        x = -10000;
        y = -10000;
    }

    if (destroyed) {
        for (Power_Up powerUp : powerUps) {
            powerUp.draw(g);
        }
    }

    floatingTexts.removeIf(text -> !text.isActive()); // Eliminar los textos inactivos
    for (FloatingText text : floatingTexts) {
        text.draw(g);
    }
}

    // Método para manejar los impactos de bala
    public void hitByBullet() {
        if (tipo == 1) {
            bulletHits++;
            if (bulletHits >= 2) {
                destroyed = true;
                Window.puntuacion += 10;

                // Crear el texto flotante "+10"
                floatingTexts.add(new FloatingText(x, y, "+10", Color.RED));
                // Reproducir sonido de destrucción
                if (destroySound != null) {
                    destroySound.setFramePosition(0); // Reinicia el sonido
                    destroySound.start();
                }
                spawnPowerUps();
            }
        }
    }
    
    public void hitByBomb() {
        if (tipo == 1) {
            if (destroySound != null) {
                destroySound.setFramePosition(0); // Reinicia el sonido
                destroySound.start();
            }
        	spawnPowerUps(); // M�todo para generar power-ups
            floatingTexts.add(new FloatingText(x, y, "+10", Color.RED));
        }
    }
    
    private void spawnPowerUps() {
        Random random = new Random();
        
        // Generar un n�mero aleatorio para determinar si aparecer� un power-up
        if (random.nextInt(100) < 35) { // 10% de probabilidad
            int powerUpType = random.nextInt(4) + 1; // Genera un n�mero entre 1 y 4
            powerUps.add(new Power_Up(this.x, this.y, this.width, this.height, powerUpType));
        }

    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    // Método para destruir la estructura
    public void setDestroyedTrue() {
        this.destroyed = true;
    }
    
    public void setDestroyedFalse() {
        this.destroyed = false;
    }
}
