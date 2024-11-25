// Ruta del archivo: main/entities/characters/Police.java

package main.entities.characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.entities.projectiles.Bullet;

public class Police extends Enemy {

    private static final int DEFAULT_HEALTH = 5;
    private static final int DEFAULT_SPEED = 24;
    private static final long DEFAULT_SHOOT_DELAY = 1500;
    private static final int DEFAULT_MAX_BULLETS = 10; 
    private static final int DEFAULT_REMAINING_BULLETS = 10;

    private BufferedImage downImage;
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private BufferedImage upImage;

    public Police(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HEALTH, DEFAULT_SPEED, DEFAULT_SHOOT_DELAY, DEFAULT_MAX_BULLETS, DEFAULT_REMAINING_BULLETS);
        loadImages();
    }

    private void loadImages() {
        try {
            downImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Policia/SpritePoliciaDown.png"));
            leftImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Policia/SpritePoliciaLeft.png"));
            rightImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Policia/SpritePoliciaRight.png"));
            upImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Policia/SpritePoliciaUp.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading Police images");
        }
    }

    @Override
    public void draw(Graphics g) {
        if (isAlive()) {
            // Dibujar el sprite del policía
            BufferedImage currentImage = null;
            switch (getCurrentDirection()) {
                case "UP":
                    currentImage = upImage;
                    break;
                case "DOWN":
                    currentImage = downImage;
                    break;
                case "LEFT":
                    currentImage = leftImage;
                    break;
                case "RIGHT":
                    currentImage = rightImage;
                    break;
            }

            if (currentImage != null) {
                g.drawImage(currentImage, x, y-(height/2), (int)(width*0.95), (int)((height+(height/2))*0.95), null);
            }

            // Dibujar las balas disparadas por el policía
            for (Bullet bullet : getBullets()) {
                bullet.draw(g); // Usar el método de dibujo de Bullet
            }
        }
    }
}
