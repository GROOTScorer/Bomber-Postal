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

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Menu_Screen extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    private boolean startGame = false;

    private BufferStrategy bs;
    private Graphics g;

    private String tittle = "Bomber Postal";
    private String start = "Enter para jugar";

    private Clip enterSound;

    public Menu_Screen() {
        // Configurar el frame
        setTitle("Bomber Postal");
        setSize(Window.WIDTH, Window.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Instanciar el canvas y sus dimensiones
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(this);

        setVisible(true);

        // Cargar los sonidos de enter
        enterSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
    }

    // Método para cargar un sonido
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

    public void update() {
    }

    public void draw() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // ---------------------------------------------- Empieza el dibujo

        // Dibuja fondo con degradado
        Color darkRed = new Color(50, 0, 0); // Un rojo oscuro
        Color bloodRed = new Color(120, 10, 10); // Rojo sangre
        for (int i = 0; i < Window.HEIGHT; i++) {
            float ratio = (float) i / Window.HEIGHT;
            Color gradient = blendColors(darkRed, bloodRed, ratio);
            g.setColor(gradient);
            g.fillRect(0, i, Window.WIDTH, 1);
        }

        // Dibuja salpicaduras sangrientas más prominentes
        g.setColor(new Color(200, 0, 0, 200)); // Rojo intenso y algo transparente
        for (int i = 0; i < 6; i++) {
            int splashX = (int) (Math.random() * Window.WIDTH);
            int splashY = (int) (Math.random() * Window.HEIGHT);
            int splashSize = (int) (Math.random() * 100) + 50; // Tamaños aleatorios
            g.fillOval(splashX, splashY, splashSize, splashSize);
        }

        // Dibuja el título
        int tittleFontSize = (int) (Window.HEIGHT * 0.15); // Fuente proporcional al alto
        Font tittleFont = new Font("BREAK IT", Font.PLAIN, tittleFontSize);
        g.setFont(tittleFont);
        g.setColor(new Color(255, 0, 0)); // Rojo brillante
        FontMetrics tittleMetrics = g.getFontMetrics(tittleFont);
        int tittleWidth = tittleMetrics.stringWidth(tittle);
        g.drawString(tittle, (Window.WIDTH - tittleWidth) / 2, (int) (Window.HEIGHT * 0.3));

        // Dibuja las opciones
        int optionFontSize = (int) (Window.HEIGHT * 0.05); // Fuente proporcional al alto
        Font optionFont = new Font("smash", Font.PLAIN, optionFontSize);
        g.setFont(optionFont);
        g.setColor(Color.WHITE);
        FontMetrics optionMetrics = g.getFontMetrics(optionFont);
        int startWidth = optionMetrics.stringWidth(start);
        g.drawString(start, (Window.WIDTH - startWidth) / 2, (int) (Window.HEIGHT * 0.6));

        // ---------------------------------------------- Termina el dibujo

        g.dispose();
        bs.show();
    }

    // Método para mezclar dos colores (degradado suave)
    private Color blendColors(Color color1, Color color2, float ratio) {
        int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return new Color(red, green, blue);
    }

    @Override
    public void run() {
        while (running) {
            draw();
            update();

            if (startGame) {
                running = false;
                this.dispose(); // Cerrar el menú
                new Menu_Inicio(this.getWidth(), this.getHeight()).start(); // Iniciar el juego
            }

            // Añadir pausa para controlar la velocidad del menú
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            startGame = true;
            if (enterSound != null) {
                enterSound.setFramePosition(0);
                enterSound.start(); // Reproduce el sonido de "Enter"
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
