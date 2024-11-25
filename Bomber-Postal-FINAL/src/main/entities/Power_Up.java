package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Power_Up {
    public int x, y, width, height;
    public boolean isAlive;
    public int tipo;

    // Variables para las imágenes
    private Image image1, image2, image3, image4;

    public Power_Up(int x, int y, int width, int height, int tipo) {
        this.x = x;
        this.y = y;
        this.width = width; 
        this.height = height; 
        this.isAlive = true;
        this.tipo = tipo;
        
        // Cargar imágenes
        image1 = Toolkit.getDefaultToolkit().getImage("assets/images/SpritesBomberPostal/Objetos/PowerUps/Velocidad.png");
        image2 = Toolkit.getDefaultToolkit().getImage("assets/images/SpritesBomberPostal/Objetos/PowerUps/AumentoCargador.png");
        image3 = Toolkit.getDefaultToolkit().getImage("assets/images/SpritesBomberPostal/Objetos/PowerUps/Botiquin.png");
        image4 = Toolkit.getDefaultToolkit().getImage("assets/images/SpritesBomberPostal/Objetos/PowerUps/RecorteDeTiempoBomba.png");
    }

    public void draw(Graphics g) {
        if (isAlive) {
            switch (tipo) {
                case 1:
                    g.drawImage(image1, x, y, width, height, null); // Dibujar imagen 1
                    break;
                case 2:
                    g.drawImage(image2, x, y, width, height, null); // Dibujar imagen 2
                    break;
                case 3:
                    g.drawImage(image3, x, y, width, height, null); // Dibujar imagen 3
                    break;
                case 4:
                    g.drawImage(image4, x, y, width, height, null); // Dibujar imagen 4
                    break;
                default:
                    // Si el tipo no está entre 1-4, puedes dibujar un rectángulo como fallback
                    g.setColor(Color.blue);
                    g.fillRect(x, y, width, height);
                    break;
            }
        }
    }
    
    public void update(int bloqueTam) {
    	this.width = bloqueTam;
    	this.height = bloqueTam;
    }

    // Getters para la detección de colisiones
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

    public boolean isAlive() {
        return isAlive;
    }

    public void deactivate() {
        isAlive = false;
    }
}
