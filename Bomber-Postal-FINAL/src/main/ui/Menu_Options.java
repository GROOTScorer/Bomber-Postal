// Archivo: src/main/ui/Menu_Options.java
package main.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import main.utils.AudioController;

public class Menu_Options extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;
    public static int flagSonido = 1; // 1 = Activado, 0 = Desactivado

    private Font levelFont = new Font("smash", Font.PLAIN, 50);
    private String opcionSonido = "Activar musica: SI";
    private String opcionResolucion = "Resolución: " + getResolutionString(Window.resolucion);

    private String[] opciones = {opcionSonido, opcionResolucion};
    private int currentSelection = 0;

    private String comoElegir = "Usa las flechas para moverte";

    private Clip navigationSound;
    private Clip selectSound;
    private Clip escapeSound;

    public Menu_Options(int width, int height) {
        setTitle("Menu opciones");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(this);
        setVisible(true);

        selectSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
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
    }

    private void updateResolution() {
        int newWidth, newHeight;

        switch (Window.resolucion) {
            case 1 -> { newWidth = 1920; newHeight = 1080; }
            case 2 -> { newWidth = 1600; newHeight = 900; }
            case 3 -> { newWidth = 1280; newHeight = 720; }
            case 4 -> { newWidth = 1024; newHeight = 576; }
            case 5 -> { newWidth = 854;  newHeight = 480; }
            default -> { newWidth = 800;  newHeight = 450; }
        }

        setSize(newWidth, newHeight);
        canvas.setPreferredSize(new Dimension(newWidth, newHeight));
        canvas.revalidate();
        setLocationRelativeTo(null);
        validate();
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
    Color softRed = new Color(89, 15, 15);
    Color lightRed = new Color(128, 35, 35);
    for (int i = 0; i < getHeight(); i++) {
        float ratio = (float) i / getHeight();
        Color gradient = blendColors(softRed, lightRed, ratio);
        g.setColor(gradient);
        g.fillRect(0, i, getWidth(), 1);
    }

    int baseFontSize = Math.min(getWidth(), getHeight()) / 20;
    Font responsiveLevelFont = new Font("smash", Font.PLAIN, baseFontSize);
    Font responsiveMinorFont = new Font("smash", Font.PLAIN, baseFontSize / 2);

    // Opciones
    g.setFont(responsiveLevelFont);
    FontMetrics fm = g.getFontMetrics(responsiveLevelFont);
    int yOffset = (getHeight() / 2) - 125;
    for (int i = 0; i < opciones.length; i++) {
        int textWidth = fm.stringWidth(opciones[i]);
        int xPosition = (getWidth() - textWidth) / 2;

        if (i == currentSelection) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.drawString(opciones[i], xPosition, yOffset + (i * baseFontSize * 2));
    }

    // Triángulos y texto inferior
    g.setFont(responsiveMinorFont);
    int arrowSize = baseFontSize / 2;
    int triangleY = (getHeight() / 2) + baseFontSize * 5;

    // Medir ancho del texto para centrar todo
    int textWidth = g.getFontMetrics().stringWidth(comoElegir);
    int totalWidth = textWidth + arrowSize * 4; // Texto + ancho de ambos triángulos
    int centerX = getWidth() / 2;

    // Calcular posiciones de los triángulos en función del texto
    int triangleXLeft = centerX - totalWidth / 2 - arrowSize;
    int triangleXRight = centerX + totalWidth / 2;

    // Triángulo izquierdo
    Polygon leftArrow = new Polygon();
    leftArrow.addPoint(triangleXLeft, triangleY);
    leftArrow.addPoint(triangleXLeft + arrowSize, triangleY - arrowSize);
    leftArrow.addPoint(triangleXLeft + arrowSize, triangleY + arrowSize);
    g.setColor(Color.white);
    g.fillPolygon(leftArrow);

    // Triángulo derecho
    Polygon rightArrow = new Polygon();
    rightArrow.addPoint(triangleXRight, triangleY);
    rightArrow.addPoint(triangleXRight - arrowSize, triangleY - arrowSize);
    rightArrow.addPoint(triangleXRight - arrowSize, triangleY + arrowSize);
    g.fillPolygon(rightArrow);

    // Texto centrado respecto a los triángulos
    g.drawString(comoElegir, centerX - (textWidth / 2), triangleY + arrowSize * 0 + (int) (arrowSize * 0.5));

    g.dispose();
    bs.show();
}

    private Color blendColors(Color color1, Color color2, float ratio) {
        int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return new Color(red, green, blue);
    }

    private void updateOptions() {
        opcionSonido = "Activar musica: " + (flagSonido == 1 ? "SI" : "NO");
        opcionResolucion = "Resolución: " + getResolutionString(Window.resolucion);

        opciones[0] = opcionSonido;
        opciones[1] = opcionResolucion;
    }

    private String getResolutionString(int resolucion) {
        return switch (resolucion) {
            case 1 -> "1920x1080";
            case 2 -> "1600x900";
            case 3 -> "1280x720";
            case 4 -> "1024x576";
            case 5 -> "854x480";
            default -> "800x450";
        };
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentSelection = (currentSelection - 1 + opciones.length) % opciones.length;
            playNavigationSound();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentSelection = (currentSelection + 1) % opciones.length;
            playNavigationSound();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (currentSelection == 0) {
                flagSonido = (flagSonido + 1) % 2; // Alterna entre 0 y 1
                AudioController.setSoundEnabled(flagSonido == 1); // Habilitar o deshabilitar sonido
            } else if (currentSelection == 1) {
                Window.resolucion += (e.getKeyCode() == KeyEvent.VK_RIGHT ? 1 : -1);
                Window.resolucion = Math.max(1, Math.min(Window.resolucion, 5));
                updateResolution();
            }
            updateOptions();
            playSelectSound();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            running = false;
            playEscapeSound();
            this.dispose();
            new Menu_Inicio(this.getWidth(), this.getHeight()).start();
        }
    }

    private void playNavigationSound() {
        if (navigationSound != null) {
            navigationSound.setFramePosition(0);
            navigationSound.start();
        }
    }

    private void playSelectSound() {
        if (selectSound != null) {
            selectSound.setFramePosition(0);
            selectSound.start();
        }
    }

    private void playEscapeSound() {
        if (escapeSound != null) {
            escapeSound.setFramePosition(0);
            escapeSound.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        while (running) {
            draw();
        }
    }
}
