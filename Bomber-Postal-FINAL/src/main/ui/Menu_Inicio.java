package main.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import main.utils.AudioController;

public class Menu_Inicio extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;

    private String[] opciones = {"Jugar", "Configuracion", "Controles", "Tabla de jugadores", "Salir"};
    private int currentSelection = 0;

    private Clip navigationSound;
    private Clip selectSound;

    private int width, height;

    public Menu_Inicio(int width, int height) {
        setTitle("Menu inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.width = width;
        this.height = height;

        // Configuración del canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(this);

        setSize(width, height);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        setVisible(true);

        // Cargar sonidos
        selectSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
        AudioController.playBackgroundMusic("assets/sounds/Sonidos/Musica_Menu.wav");
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
        AudioController.stopBackgroundMusic(); // Detener música al salir
    }

    @Override
    public void run() {
        while (running) {
            draw();
        }
    }

    private void draw() {
        if (!running) return;

        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // Fondo con degradado
        Color darkRed = new Color(50, 0, 0);
        Color bloodRed = new Color(120, 10, 10);
        for (int i = 0; i < height; i++) {
            float ratio = (float) i / height;
            Color gradient = blendColors(darkRed, bloodRed, ratio);
            g.setColor(gradient);
            g.fillRect(0, i, width, 1);
        }

        // Dibujar opciones del menú
        int fontSize = (int) (height * 0.06); // Tamaño de la fuente dinámico
        Font menuFont = new Font("smash", Font.PLAIN, fontSize);
        g.setFont(menuFont);

        FontMetrics fm = g.getFontMetrics(menuFont);

        int yOffset = height / 3; // Posición vertical inicial del menú (proporcional al alto)
        int lineSpacing = (int) (height * 0.08); // Separación dinámica entre líneas

        for (int i = 0; i < opciones.length; i++) {
            String text = opciones[i];
            int xPosition = (width - fm.stringWidth(text)) / 2; // Centrado horizontal
            int yPosition = yOffset + i * lineSpacing; // Espaciado vertical

            if (i == currentSelection) {
                g.setColor(Color.RED); // Resaltar opción seleccionada
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(text, xPosition, yPosition);
        }

        g.dispose();
        bs.show();
    }

    private Color blendColors(Color color1, Color color2, float ratio) {
        int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return new Color(red, green, blue);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentSelection = (currentSelection - 1 + opciones.length) % opciones.length;
            if (navigationSound != null) {
                navigationSound.setFramePosition(0);
                navigationSound.start();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentSelection = (currentSelection + 1) % opciones.length;
            if (navigationSound != null) {
                navigationSound.setFramePosition(0);
                navigationSound.start();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (selectSound != null) {
                selectSound.setFramePosition(0);
                selectSound.start();
            }

            switch (currentSelection) {
                case 0 -> {
                    AudioController.stopBackgroundMusic();
                    running = false;
                    this.dispose();
                    new Window().start();
                } // Jugar
                case 1 -> {
                    new Menu_Options(this.width, this.height).start();
                    running = false;
                    this.dispose();
                } // Configuración
                case 2 -> {
                    new Menu_Controles(this.width, this.height).start();
                    running = false;
                    this.dispose();
                } // Controles
                case 3 -> {
                    new Scoreboard(this.width, this.height).start();
                    running = false;
                    this.dispose();
                } // Tabla de clasificación
                case 4 -> {
                    AudioController.stopBackgroundMusic();
                    running = false;
                    this.dispose();
                    System.exit(0);
                } // Salir
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
