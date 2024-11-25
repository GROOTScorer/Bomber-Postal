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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class Save_Progress extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;
    private String namePlayer = ""; // Variable para almacenar el nombre del jugador
    private boolean isFinished = false; // Controla si se terminó de escribir el nombre

    private int width, height;

    public Save_Progress(int width, int height) {
        this.width = width;
        this.height = height;

        setTitle("Guardar Progreso");
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

        // Texto de instrucción
        g.setColor(Color.WHITE);
        Font instructionFont = new Font("smash", Font.PLAIN, 30);
        g.setFont(instructionFont);
        String instruction = "Escribe tu nombre y presiona Enter:";
        FontMetrics fm = g.getFontMetrics(instructionFont);
        int instructionX = (width - fm.stringWidth(instruction)) / 2;
        int instructionY = height / 3;
        g.drawString(instruction, instructionX, instructionY);

        // Mostrar el nombre del jugador
        Font nameFont = new Font("smash", Font.BOLD, 40);
        g.setFont(nameFont);
        String nameDisplay = "Nombre: " + namePlayer;
        fm = g.getFontMetrics(nameFont);
        int nameX = (width - fm.stringWidth(nameDisplay)) / 2;
        int nameY = height / 2;
        g.drawString(nameDisplay, nameX, nameY);

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
    public void keyTyped(KeyEvent e) {
        if (!isFinished) {
            char keyChar = e.getKeyChar();
            if (Character.isLetterOrDigit(keyChar) || keyChar == ' ') {
                namePlayer += keyChar; // Agregar carácter al nombre
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !isFinished) {
        	saveNameToFile(); // Guardar el nombre, la puntuación y el tiempo en el archivo
        	Window.nivelSeleccionado = 1;
        	Window.frameSeleccionado = 1;
        	Window.objetosRestantes = 0;
        	Window.enemigosRestantes = 0;
        	Window.puntuacion = 0;
            isFinished = true; // Marcar como finalizado
            
            stop();
            dispose(); // Cerrar la ventana
            new CreditsScreen(this.width, this.height).start();
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !namePlayer.isEmpty()) {
            // Borrar el último carácter
            namePlayer = namePlayer.substring(0, namePlayer.length() - 1);
        }
    }

    private void saveNameToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("player_progress.txt", true))) {
            // Escribir el nombre del jugador, la puntuación y el tiempo
            writer.write("Nombre: " + namePlayer + ", Puntuación: " + Window.puntuacion + ", Tiempo: " + Window.totalSeconds);
            writer.newLine(); // Escribir una nueva línea
        } catch (IOException e) {
            System.err.println("Error al guardar el progreso: " + e.getMessage());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public String getNamePlayer() {
        return namePlayer; // Retorna el nombre del jugador
    }
}
