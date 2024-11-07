package main.entities.projectiles;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.entities.environment.Structure;
import main.ui.Window;

public class Bullet {
    private int x, y, width, height;
    private int speed;
    private int direction; // 0: derecha, 1: arriba, 2: izquierda, 3: abajo
    private boolean active;

    // Imágenes para cada dirección
    private Image imgRight, imgUp, imgLeft, imgDown;

    // Clip de sonido
    private Clip fireSound;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = (int) (Window.anchoResponsive * 0.013); 
        this.active = true;

        if(direction == 0 || direction == 2) {
            this.width = (int) (Window.anchoResponsive * 0.02);
            this.height = (int) (Window.anchoResponsive * 0.01);
        } else if(direction == 1 || direction == 3) {
            this.width = (int) (Window.anchoResponsive * 0.01);
            this.height = (int) (Window.anchoResponsive * 0.02);        	
        }

        // Cargar las imágenes para cada dirección
        try {
            imgRight = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bala/Bala-Derecha.png"));
            imgUp = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bala/Bala-Arriba.png"));
            imgLeft = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bala/Bala-Izquierda.png"));
            imgDown = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bala/Bala-Abajo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cargar y reproducir sonido al disparar
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sounds/Sonidos/M16.wav"));
            fireSound = AudioSystem.getClip();
            fireSound.open(audioInputStream);
            fireSound.start(); // Reproduce el sonido de disparo
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void update(List<Structure> structures) {
        if (active) {
            // Mover la bala
            switch (direction) {
                case 0: // Derecha
                    x += speed;
                    break;
                case 1: // Arriba
                    y -= speed;
                    break;
                case 2: // Izquierda
                    x -= speed;
                    break;
                case 3: // Abajo
                    y += speed;
                    break;
            }

            // Comprobar límites de la ventana
            if (x < Window.limiteJugadorIzquierda || x > Window.limiteJugadorDerecha || y < Window.limiteJugadorArriba || y > Window.limiteJugadorAbajo + Window.bloqueTam*0.5) {
                deactivate();
            }

            // Verificar colisiones con estructuras
            for (Structure structure : structures) {
                if (collidesWith(structure)) {
                    // Desactivar la bala
                    deactivate();

                    // Si la estructura es de tipo 1, aplicar daño
                    if (structure.tipo == 1) {
                        structure.hitByBullet(); // Aplicar daño a la estructura
                    }
                    break; // Salir del bucle una vez que colisiona
                }
            }
        }
    }

    public void draw(Graphics g) {
        if (active) {
            // Dibujar la imagen dependiendo de la dirección
            switch (direction) {
                case 0:
                    g.drawImage(imgRight, x, y, width, height, null);
                    break;
                case 1:
                    g.drawImage(imgUp, x, y, width, height, null);
                    break;
                case 2:
                    g.drawImage(imgLeft, x, y, width, height, null);
                    break;
                case 3:
                    g.drawImage(imgDown, x, y, width, height, null);
                    break;
            }
        }
    }

    public boolean collidesWith(Structure structure) {
        return x < structure.getX() + structure.getWidth() &&
               x + width > structure.getX() &&
               y < structure.getY() + structure.getHeight() &&
               y + height > structure.getY();
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
        // Aquí podrías agregar otro sonido para cuando la bala colisiona
    }
}
