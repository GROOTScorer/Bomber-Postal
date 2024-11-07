package main.entities.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import main.entities.Power_Up;

public class Structure {
    public int x, y, tipo;
    public int width, height;
    private Color color; // Color opcional si no se proporciona imagen
    public Image image; // Imagen opcional
    public int bulletHits; // Contador de impactos de balas
    private boolean destroyed; // Para saber si la estructura está destruida
    private List<Power_Up> powerUps;

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
    }

    // Método que dibuja la estructura
    public void draw(Graphics g) {
        if (!destroyed) { // Solo dibuja si no está destruida
            if (image != null) {
                // Dibujar imagen si está presente
                g.drawImage(image, x, y, width, height, null);
            } else if (color != null) {
                // Dibujar el rectángulo con color si no hay imagen
                g.setColor(color);
                g.fillRect(x, y, width, height);
            } else {
                // Si no hay imagen ni color, dibujar un fondo verde
                g.setColor(Color.GREEN);
                g.fillRect(x, y, width, height);
            }
        }
        
        else {
        	x = -10000;
        	y = -10000;
        }
        
        if (destroyed) {
            for (Power_Up powerUp : powerUps) {
                powerUp.draw(g); // Dibuja solo los power-ups activos
            }
        }
    }

    // Método para manejar los impactos de bala
    public void hitByBullet() {
        if (tipo == 1) {
            bulletHits++;
            if (bulletHits >= 2) {
                destroyed = true; // La estructura desaparece
                spawnPowerUps(); // M�todo para generar power-ups
            }
        }
    }
    
    public void hitByBomb() {
        if (tipo == 1) {
        	spawnPowerUps(); // M�todo para generar power-ups
        }
    }
    
    private void spawnPowerUps() {
        Random random = new Random();
        
        // Generar un n�mero aleatorio para determinar si aparecer� un power-up
        if (random.nextInt(100) < 100) { // 10% de probabilidad
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
