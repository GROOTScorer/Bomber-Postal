package main.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;

public class Scoreboard extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;
    private List<PlayerScore> scores;

    private int width, height;
    private boolean isSortedByScore = true; // Controla el criterio de ordenación

    public Scoreboard(int width, int height) {
        this.width = width;
        this.height = height;

        setTitle("Tabla de Clasificación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(this);
        setVisible(true);

        scores = new ArrayList<>();
        loadScoresFromFile();
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

        // Título
        g.setColor(Color.WHITE);
        int titleFontSize = (int) (height * 0.08); // Tamaño de fuente proporcional a la altura
        Font titleFont = new Font("Smash", Font.BOLD, titleFontSize);
        g.setFont(titleFont);
        String title = "Ranking de Jugadores";
        FontMetrics fm = g.getFontMetrics(titleFont);
        int titleX = (width - fm.stringWidth(title)) / 2;
        int titleY = (int) (height * 0.1); // 10% del alto de la pantalla
        g.drawString(title, titleX, titleY);

        // Mostrar el criterio de ordenación
        String orderText = isSortedByScore ? "Ordenado por Puntuación" : "Ordenado por Tiempo";
        int orderFontSize = (int) (height * 0.05);
        Font orderFont = new Font("smash", Font.PLAIN, orderFontSize);
        g.setFont(orderFont);
        fm = g.getFontMetrics(orderFont);
        int orderX = (width - fm.stringWidth(orderText)) / 2;
        int orderY = titleY + (int) (height * 0.1); // Justo debajo del título
        g.drawString(orderText, orderX, orderY);

        // Mostrar la tabla de resultados
        int scoreFontSize = (int) (height * 0.05); // Tamaño de fuente para los puntajes
        Font scoreFont = new Font("smash", Font.PLAIN, scoreFontSize);
        g.setFont(scoreFont);
        fm = g.getFontMetrics(scoreFont);

        int startY = orderY + (int) (height * 0.1); // Comienza debajo del texto de ordenación
        int lineHeight = (int) (height * 0.07); // Espacio entre líneas

        for (int i = 0; i < scores.size(); i++) {
            PlayerScore score = scores.get(i);
            String line;
            if (isSortedByScore) {
                line = (i + 1) + ". " + score.getName() + " - " + score.getScore();
            } else {
                // Muestra el tiempo formateado en "mm:ss"
                line = (i + 1) + ". " + score.getName() + " - " + score.getFormattedTime();
            }
            int xPosition = (width - fm.stringWidth(line)) / 2; // Centrar horizontalmente
            int yPosition = startY + i * lineHeight; // Espaciado entre líneas
            g.drawString(line, xPosition, yPosition);
        }

        // Mostrar la instrucción al final
        String footerText = "Presione espacio para cambiar";
        String esc = "ESC para salir";
        int footerFontSize = (int) (height * 0.04);

        // Fuente para "Presione espacio para cambiar" (en cursiva)
        Font footerFont = new Font("smash", Font.ITALIC, footerFontSize);
        g.setFont(footerFont);
        fm = g.getFontMetrics(footerFont);
        int footerX = (width - fm.stringWidth(footerText)) / 2;
        int footerY = height - (int) (height * 0.15); // Un poco por encima del borde inferior
        g.drawString(footerText, footerX, footerY);

        // Fuente para "ESC para salir" (en plain)
        Font footerFont2 = new Font("smash", Font.PLAIN, footerFontSize);
        g.setFont(footerFont2);
        fm = g.getFontMetrics(footerFont2);
        int footerXEsc = 10; // Margen izquierdo para posicionar "ESC para salir" en la esquina inferior izquierda
        g.drawString(esc, footerXEsc, footerY);

        g.dispose();
        bs.show();
    }

    private Color blendColors(Color color1, Color color2, float ratio) {
        int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return new Color(red, green, blue);
    }

    private void loadScoresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("player_progress.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(", Puntuación: ") && line.contains(" Tiempo: ")) {
                    String[] parts = line.split(", Puntuación: ");
                    if (parts.length == 2) {
                        String name = parts[0].replace("Nombre: ", "").trim();
                        String[] scoreAndTime = parts[1].split(" Tiempo: ");
                        if (scoreAndTime.length == 2) {
                            String scoreString = scoreAndTime[0].replace(",", "").trim();
                            String time = scoreAndTime[1].trim();
                            try {
                                int score = Integer.parseInt(scoreString);
                                scores.add(new PlayerScore(name, String.valueOf(score), time));
                            } catch (NumberFormatException e) {
                                System.err.println("Error de formato en la puntuación: " + scoreString);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los datos del archivo: " + e.getMessage());
        }

        scores.sort(Comparator.comparingInt(s -> Integer.parseInt(((PlayerScore) s).getScore())).reversed());
    }

    private static class PlayerScore {
        private String name;
        private String score;
        private String time; // Almacena el tiempo en segundos como cadena

        public PlayerScore(String name, String score, String time) {
            this.name = name;
            this.score = score;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }

        public String getTime() {
            return time;
        }

        // Método para formatear el tiempo de segundos a minutos y segundos
        public String getFormattedTime() {
            try {
                int totalSeconds = Integer.parseInt(time);
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                return String.format("%d:%02d", minutes, seconds); // Formato "mm:ss"
            } catch (NumberFormatException e) {
                return "N/A"; // En caso de error, devolver "N/A"
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            running = false;
            this.dispose();
            new Menu_Inicio(this.width, this.height).start();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSortedByScore = !isSortedByScore;
            if (isSortedByScore) {
                scores.sort(Comparator.comparingInt(s -> Integer.parseInt(((PlayerScore) s).getScore())).reversed());
            } else {
                // Convierte `time` a un entero para la comparación
                scores.sort(Comparator.comparingInt(s -> {
                    try {
                        return Integer.parseInt(s.getTime());
                    } catch (NumberFormatException ex) {
                        System.err.println("Error al convertir el tiempo: " + s.getTime());
                        return Integer.MAX_VALUE; // Manejo básico en caso de error
                    }
                }));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
