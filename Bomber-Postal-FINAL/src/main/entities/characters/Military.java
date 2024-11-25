// Ruta del archivo: main/entities/characters/Military.java

package main.entities.characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.entities.projectiles.Bullet;

public class Military extends Enemy {

    private static final int DEFAULT_HEALTH = 6;
    private static final int DEFAULT_SPEED = 16;
    private static final long DEFAULT_SHOOT_DELAY = 300;
    private static final int DEFAULT_MAX_BULLETS = 30; 
    private static final int DEFAULT_REMAINING_BULLETS = 30;

    private BufferedImage downImage;
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private BufferedImage upImage;

    public Military(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HEALTH, DEFAULT_SPEED, DEFAULT_SHOOT_DELAY, DEFAULT_MAX_BULLETS, DEFAULT_REMAINING_BULLETS);
        loadImages();
    }

    private void loadImages() {
        try {
            downImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Militar/SpriteMilitarDown.png"));
            leftImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Militar/SpriteMilitarLeft.png"));
            rightImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Militar/SpriteMilitarRight.png"));
            upImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Militar/SpriteMilitarUp.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading Military images");
        }
    }

    @Override
    public void draw(Graphics g) {
        if (isAlive()) {
            // Dibujar el sprite del militar
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

            // Dibujar las balas disparadas por el militar
            for (Bullet bullet : getBullets()) { // Accede a las balas mediante el getter en Enemy
                bullet.draw(g); // Llama al método de dibujo de Bullet
            }
        }
    }
}
