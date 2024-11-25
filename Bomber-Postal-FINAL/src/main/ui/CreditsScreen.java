package main.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.io.File;

public class CreditsScreen extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;

    private int width, height;
    private int scrollY; // Controla la posición del texto
    private boolean skip; // Controla si el usuario adelanta el texto
    private Clip backgroundMusic; // Música de fondo

    public CreditsScreen(int width, int height) {
        this.width = width;
        this.height = height;

        setTitle("Créditos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(width, height);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);
        setLocationRelativeTo(null);

        add(canvas);
        canvas.addKeyListener(this);
        setVisible(true);

        scrollY = height; // Los créditos comienzan fuera de la pantalla
        skip = false;

        // Cargar y reproducir la música de fondo
        backgroundMusic = loadSound("assets/sounds/Sonidos/creditos.wav");
        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Reproducir en bucle
        }
    }

    // Método para cargar el sonido
    private Clip loadSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (Exception e) {
            System.err.println("Error al cargar la música: " + e.getMessage());
            return null;
        }
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener la música al cerrar
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }

    @Override
    public void run() {
        while (running) {
            draw();
            update();
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (skip) {
            scrollY -= 10; // Velocidad rápida
        } else {
            scrollY -= 1; // Velocidad normal
        }

        // Terminar la animación cuando todo el texto haya salido de la pantalla
        if (scrollY + 800 < 0) { // Ajustar según el tamaño del texto
        	
            if (backgroundMusic != null && backgroundMusic.isRunning()) {
                backgroundMusic.stop();
                backgroundMusic.close();
            }
            // Deja de mostrar los créditos, pero la música seguirá si no se ha detenido manualmente.
        	running = false;          
        	dispose();
            new Menu_Screen().start(); // Volver al menú de inicio
        }
    }

    private void draw() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // Fondo negro
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // Texto de créditos estilo Star Wars
        g.setColor(Color.RED);
        int fontSize = (int) (height * 0.03); // Tamaño de la fuente dinámico
        Font font = new Font("smash", Font.PLAIN, fontSize);
        g.setFont(font);
        String[] credits = {
        	    "Creditos",
        	    "",
        	    "Desarrollado por:",
        	    "Matias Ozores - Programador",
        	    "Luca Amodeo - Programador",
        	    "Gael De Luca - Compositor y programador",
        	    "Ignacio Tapia - Artista visual",
        	    "Joaquin Pocovi - Tester y escritor",
        	    "",
        	    "",
        	    "Gracias por jugar."
        	};


        FontMetrics fm = g.getFontMetrics(font);
        int centerX = width / 2; // Centrar el texto horizontalmente

        // Dibujar cada línea de los créditos
        for (int i = 0; i < credits.length; i++) {
            int textWidth = fm.stringWidth(credits[i]);
            int textX = centerX - textWidth / 2; // Centrar horizontalmente
            int textY = scrollY + i * (int) (height * 0.08); // Separación dinámica entre líneas
            g.drawString(credits[i], textX, textY);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            skip = true; // Habilitar velocidad rápida
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            skip = false; // Volver a velocidad normal
        }
    }
}
