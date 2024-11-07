package main.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;



public class Menu_Screen extends JFrame implements Runnable, KeyListener{
	


	// -------------------------- Declaracion de variables --------------------------
	
	private static final long serialVersionUID = 1L; 
	private Canvas canvas;
    private Thread thread;
    private boolean running;
    private boolean startGame = false;
    private boolean startOptions = false;

    private BufferStrategy bs;
    private Graphics g;
    
    private Font tittleFont = new Font("BREAK IT", Font.PLAIN, 140);
    private Font optionFont = new Font("BREAK IT", Font.PLAIN, 70);
    private String tittle = "Bomber Postal";
    private String start = "Enter para jugar";
    private String options = "Espacio para opciones";
    
    private Clip enterSound;
    private Clip spaceSound;

    
	// ------------------------------------------------------------------------------
    
    public Menu_Screen() {
        // Configurar el frame
        setTitle("Undercraft");
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

        // Cargar los sonidos de enter y espacio
        enterSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
        spaceSound = loadSound("assets/sounds/Sonidos/snd_select.wav");
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

	    // Dibuja fondo oscuro y sangriento
	    Color darkRed = new Color(50, 0, 0);  // Un rojo oscuro
	    Color bloodRed = new Color(120, 10, 10);  // Rojo sangre
	    for (int i = 0; i < Window.HEIGHT; i++) {
	        float ratio = (float) i / Window.HEIGHT;
	        Color gradient = blendColors(darkRed, bloodRed, ratio);
	        g.setColor(gradient);
	        g.fillRect(0, i, Window.WIDTH, 1);
	    }

	    // Dibuja salpicaduras sangrientas más prominentes
	    g.setColor(new Color(200, 0, 0, 200));  // Rojo intenso y algo transparente
	    for (int i = 0; i < 6; i++) {
	        int splashX = (int) (Math.random() * Window.WIDTH);
	        int splashY = (int) (Math.random() * Window.HEIGHT);
	        int splashSize = (int) (Math.random() * 100) + 50;  // Tamaños aleatorios
	        g.fillOval(splashX, splashY, splashSize, splashSize);
	    }

	    // Goteos de sangre
	    g.setColor(new Color(170, 0, 0, 255));  // Rojo más oscuro y sólido para los goteos
	    for (int i = 0; i < 5; i++) {
	        int dripX = (int) (Math.random() * Window.WIDTH);
	        int dripY = (int) (Math.random() * Window.HEIGHT);
	        int dripLength = (int) (Math.random() * 200) + 50;  // Longitud de goteo
	        g.fillRect(dripX, dripY, 5, dripLength);
	    }

	    // Dibuja el título en rojo brillante
	    g.setFont(tittleFont);
	    int tittleWidth = g.getFontMetrics().stringWidth(tittle);
	    g.setColor(new Color(255, 0, 0));  // Rojo brillante para destacar
	    g.drawString(tittle, ((Window.WIDTH / 2) - (tittleWidth / 2)) - 2, (Window.HEIGHT / 2) - 123);
	    
	    // Dibuja las opciones en blanco
	    g.setFont(optionFont);
	    g.setColor(Color.white);
	    int startWidth = g.getFontMetrics().stringWidth(start);
	    g.drawString(start, (Window.WIDTH/2)-(startWidth/2)+15, (Window.HEIGHT/2)+75);
	    
	    int optionsWidth = g.getFontMetrics().stringWidth(options);
	    g.drawString(options, (Window.WIDTH/2)-(optionsWidth/2)+15, (Window.HEIGHT/2)+150);
	    
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
	        long startTime = System.nanoTime();  // Registrar tiempo inicial
	        
	        draw();
	        update();
	        
	        if (startGame) {
	            running = false;
	            this.dispose();  // Cerrar el menú
	            new Window().start();  // Iniciar el juego
	        }
	        
	        if (startOptions) {
	            running = false;
	            this.dispose();
	            new Menu_Options().start();
	        }

	        // --------------------------------------------
	        // Añadir una pausa para hacer el menú más lento
	        try {
	            Thread.sleep(50);  // Pausar el hilo por 100 milisegundos (0.1 segundos)
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        // --------------------------------------------

	        long deltaTime = System.nanoTime() - startTime;
	        long sleepTime = (16_666_667 - deltaTime) / 1_000_000;  // Asegurar que el ciclo no corra demasiado rápido
	        if (sleepTime > 0) {
	            try {
	                Thread.sleep(sleepTime);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
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
	
	
	// Funciones --------------------------------------------------------------
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int key = e.getKeyCode();
	    if (key == KeyEvent.VK_ENTER) {
	        startGame = true;
	        if (enterSound != null) {
	            enterSound.start(); // Reproduce el sonido de "Enter"
	        }
	    }
	    else if (key == KeyEvent.VK_SPACE) {
	        startOptions = true;
	        if (spaceSound != null) {
	            spaceSound.start(); // Reproduce el sonido de "Espacio"
	        }
	    }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
