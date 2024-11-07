package main.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.awt.FontMetrics;




public class Menu_Options extends JFrame implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Canvas canvas;
    private Thread thread;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;
    public static int flagSonido = 0;
    public static int flagNivelSonido = 100;
    private Font levelFont = new Font("BREAK IT", Font.PLAIN, 80);
    private String opcionSonido = "Activar sonido?: NO";
    private String opcionNivelSonido = "Elegir nivel de sonido: " + getFlagNivelSonido();
    private String[] opciones = {opcionSonido, opcionNivelSonido};
    private int currentSelection = 0;
    
    private Clip navigationSound;
    private Clip selectSound;
    private Clip escapeSound;

    public Menu_Options() {
        setTitle("Opciones");
        setSize(Window.WIDTH, Window.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(this);        
        setVisible(true);

        // Cargar los sonidos
        navigationSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
        selectSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
        escapeSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
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
    
        }
    }

    private void draw() {
        if (!running) return; // Si no está corriendo, no dibujes

        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
	    Color softRed = new Color(89, 15, 15);  // Un rojo oscuro y suave
	    Color lightRed = new Color(128, 35, 35);  // Un rojo más claro para el degradado
	    for (int i = 0; i < Window.HEIGHT; i++) {
	        float ratio = (float) i / Window.HEIGHT;
	        Color gradient = blendColors(softRed, lightRed, ratio);
	        g.setColor(gradient);
	        g.fillRect(0, i, Window.WIDTH, 1);
	    }

	    // Dibuja algunas salpicaduras suaves
	    g.setColor(new Color(150, 50, 50, 180));  // Transparente para suavidad
	    for (int i = 0; i < 5; i++) {
	        int splashX = (int) (Math.random() * Window.WIDTH);
	        int splashY = (int) (Math.random() * Window.HEIGHT);
	        int splashSize = (int) (Math.random() * 80) + 40;  // Tamaños aleatorios pero más pequeños
	        g.fillOval(splashX, splashY, splashSize, splashSize);
	    }

        // Dibuja las opciones
        g.setFont(levelFont);
        FontMetrics fm = g.getFontMetrics(levelFont);
        int yOffset = (Window.HEIGHT / 2) - 125; // Ajusta la altura según tus necesidades
        for (int i = 0; i < opciones.length; i++) {
            int textWidth = fm.stringWidth(opciones[i]);
            int xPosition = (Window.WIDTH - textWidth) / 2; // Centra el texto horizontalmente

            if (i == currentSelection) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(opciones[i], xPosition, yOffset + (i * 120));
        }

        g.dispose();
        bs.show();
    }
    
    public static int getFlagSonido() {
    	return flagSonido;
    }
    
    public static int getFlagNivelSonido() {
    	return flagNivelSonido;
    }
        
    private void updateOptions() {
    	switch(getFlagSonido()) {
    		case 0:
    			opcionSonido = "Activar sonido?: NO";
    			break;
    		
    		case 1:
    			opcionSonido = "Activar sonido?: SI";
    			break;
    	}
    	
    	opcionNivelSonido = "Elegir nivel de sonido: " + getFlagNivelSonido();
    	
        opciones[0] = opcionSonido;
        opciones[1] = opcionNivelSonido;
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_UP) {
	        currentSelection = (currentSelection - 1 + opciones.length) % opciones.length;
	        if (navigationSound != null) {
	            navigationSound.setFramePosition(0); // Reiniciar sonido
	            navigationSound.start(); // Reproduce el sonido de navegación
	        }
	    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	        currentSelection = (currentSelection + 1) % opciones.length;
	        if (navigationSound != null) {
	            navigationSound.setFramePosition(0); // Reiniciar sonido
	            navigationSound.start(); // Reproduce el sonido de navegación
	        }
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        if (currentSelection == 0 && getFlagSonido() == 1) {
	            flagSonido--;
	        } else if (currentSelection == 1 && getFlagNivelSonido() > 0) {
	            flagNivelSonido--;
	        }
	        updateOptions();
	        if (selectSound != null) {
	            selectSound.setFramePosition(0); // Reiniciar sonido
	            selectSound.start(); // Reproduce el sonido de selección
	        }
	    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        if (currentSelection == 0 && getFlagSonido() < 1) {
	            flagSonido++;
	        } else if (currentSelection == 1 && getFlagNivelSonido() < 100) {
	            flagNivelSonido++;
	        }
	        updateOptions();
	        if (selectSound != null) {
	            selectSound.setFramePosition(0); // Reiniciar sonido
	            selectSound.start(); // Reproduce el sonido de selección
	        }
	    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	        running = false;
	        if (escapeSound != null) {
	            escapeSound.setFramePosition(0); // Reiniciar sonido
	            escapeSound.start(); // Reproduce el sonido de "escape"
	        }
	        this.dispose(); // Cerrar el menú
	        new Menu_Screen().start(); // Volver al menú principal
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
