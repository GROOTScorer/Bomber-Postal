package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.entities.characters.Player;

public class Item {

    // Declaración de variables
    private int x, y, width, height, tipo;
    private Player player;
    private boolean collected = false; // Bandera para saber si el item fue recogido
    private BufferedImage image; // Imagen para el item de tipo 2
    private BufferedImage portalImage; // Imagen para el item de tipo 1

    // Constructor
    public Item(int x, int y, int width, int height, Player player, int tipo) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
        this.tipo = tipo;

        // Cargar la imagen según el tipo
        if (tipo == 2) {
            try {
                image = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bala/Municion.png")); // Imagen para tipo 2
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de errores básico
            }
        } else if (tipo == 1) {
            try {
                portalImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Objetos/Dinero.png")); // Ruta de la imagen para tipo 1
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de errores básico
            }
        }
    }

    public void update() {
        if (!collected && player.getX() < x + width && player.getX() + player.getWidth() > x &&
            player.getY() < y + height && player.getY() + player.getHeight() > y) {
            collected = true;

            if (tipo == 1) {
                playSound("assets/sounds/Sonidos/SonidoEsencial.wav");
            } else if (tipo == 2) {
                // Generar un número aleatorio entre 1 y 5
                Random rand = new Random();
                int bulletsToAdd = rand.nextInt(5) + 1;
                player.remainingBullets += bulletsToAdd; // Sumar balas aleatorias
                if (player.remainingBullets > player.MAX_BULLETS) {
                    player.remainingBullets = player.MAX_BULLETS;
                }
                playSound("assets/sounds/Sonidos/SonidoMunicion.wav");
            }
        }
    }

    private void playSound(String filePath) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filePath)));

            // Ajustar el volumen
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Incrementar el volumen, por ejemplo, en +6.0 decibeles
            volumeControl.setValue(6.0f); 

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Manejo de errores básico
        } catch (IllegalArgumentException e) {
            System.err.println("Control de volumen no soportado para este clip.");
        }
    }

    public void draw(Graphics g) {
        if (!collected) {
            if (tipo == 1 && portalImage != null) {
                // Dibujar la imagen del portal para tipo 1
                g.drawImage(portalImage, x, y, width, height, null);
            } else if (tipo == 2 && image != null) {
                // Calcular el nuevo tamaño (mitad del tamaño original)
                int halfWidth = width / 2;
                int halfHeight = height / 2;

                // Calcular las nuevas coordenadas para centrar la imagen
                int centeredX = x + (width - halfWidth) / 2;
                int centeredY = y + (height - halfHeight) / 2;

                // Dibujar la imagen centrada y a la mitad de su tamaño
                g.drawImage(image, centeredX, centeredY, halfWidth, halfHeight, null);
            } else if (tipo == 2) {
                // Si no se carga la imagen, dibujar un rectángulo de respaldo
                g.setColor(Color.green);
                g.fillRect(x, y, width, height);
            }
        }
    }

    public int getTipo() {
        return this.tipo;
    }

    public boolean getCollected() {
        return this.collected;
    }
}
