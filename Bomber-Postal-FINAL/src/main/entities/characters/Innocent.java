// Ruta del archivo: main/entities/characters/Innocent.java

package main.entities.characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Innocent extends Enemy {

    private static final int DEFAULT_HEALTH = 3;
    private static final int DEFAULT_SPEED = 32;
    private static final long DEFAULT_SHOOT_DELAY = 1000;
    private static final int DEFAULT_MAX_BULLETS = 0; 
    private static final int DEFAULT_REMAINING_BULLETS = 0;

    private BufferedImage downImage;
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private BufferedImage upImage;

    public Innocent(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HEALTH, DEFAULT_SPEED, DEFAULT_SHOOT_DELAY, DEFAULT_MAX_BULLETS, DEFAULT_REMAINING_BULLETS);
        loadImages();
    }

    private void loadImages() {
        try {
            downImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Inocente/SpriteInocenteDown.png"));
            leftImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Inocente/SpriteInocenteLeft.png"));
            rightImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Inocente/SpriteInocenteRight.png"));
            upImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Enemigos/Inocente/SpriteInocenteUp.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading Innocent images");
        }
    }

    @Override
    public void draw(Graphics g) {
        if (isAlive()) {
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
            } else {
                super.draw(g); // Llama al método de Enemy si no hay imagen
            }
        }
    }
}
