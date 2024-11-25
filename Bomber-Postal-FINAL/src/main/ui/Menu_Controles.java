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

public class Menu_Controles extends JFrame implements Runnable, KeyListener{
	


	// -------------------------- Declaracion de variables --------------------------
	
	private static final long serialVersionUID = 1L; 
	private Canvas canvas;
    private Thread thread;
    private boolean running;
    private boolean startGame = false;

    private BufferStrategy bs;
    private Graphics g;
    private Font messageFont = new Font("Smash", Font.PLAIN, 30);
    private String controls1 = "3. Presiona la C para 'DISPARAR'";
    private String controls2 = "2. Presiona el ESPACIO para 'PLANTAR BOMBAS'";
    private String controls3 = "1. Muevete con 'W,A,S,D' o con las flechas";    
    private String salir = "Enter o Escape para salir";
    private int width,height;
    // presiona enter
    private int blinkCounter = 0; // Contador para manejar el parpadeo
    private final int BLINK_INTERVAL = 800; // Intervalo de parpadeo (ajusta según sea necesario)
    
	// ------------------------------------------------------------------------------
    
    public Menu_Controles(int width, int height) {
        // seteamos los valores que necesitamos si o si para el frame
        setTitle("Bomber Postal");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.width = width;
        this.height = height;
        // instanciamos canvas y sus dimensiones (con las mismas dimensiones de window)
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(this);
        
        setVisible(true);
        
    }

    public void update() {
        blinkCounter++;
        if (blinkCounter >= BLINK_INTERVAL) {
            blinkCounter = 0;
        }
    }

	
    public void draw() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        
        // Calcular el tamaño de la fuente en función de las dimensiones de la ventana
        int baseFontSize = Math.min(width, height) / 25; // Ajuste de escala para la fuente principal
        Font responsiveMessageFont = new Font("Smash", Font.PLAIN, baseFontSize);

        // Dibujar los controles con la fuente escalada
        g.setFont(responsiveMessageFont);
        g.setColor(Color.red);

        int yOffset = (height / 2) - baseFontSize * 0; // Ajuste de posición vertical

        // Dibujar el texto de los controles
        int messageWidth = g.getFontMetrics().stringWidth(controls1);
        g.drawString(controls1, (width / 2) - (messageWidth / 2), yOffset);

        int messageWidth2 = g.getFontMetrics().stringWidth(controls2);
        g.drawString(controls2, (width / 2) - (messageWidth2 / 2), yOffset - baseFontSize * 3);

        int messageWidth3 = g.getFontMetrics().stringWidth(controls3);
        g.drawString(controls3, (width / 2) - (messageWidth3 / 2), yOffset - baseFontSize * 6);

        // Dibujar el texto de "salir"
        int salirFontSize = baseFontSize / 2; // Ajuste para el tamaño de la fuente menor
        Font responsiveSalirFont = new Font("Smash", Font.PLAIN, salirFontSize);
        g.setFont(responsiveSalirFont);
        g.setColor(Color.white);

        int salirWidth = g.getFontMetrics().stringWidth(salir);
        g.drawString(salir, (width / 2) - (salirWidth / 2), yOffset + baseFontSize * 6);

        g.dispose();
        bs.show();
    }


	
	
	@Override
	public void run() {
        while (running) {
            draw();
            update();
            if (startGame) {
                running = false;
                this.dispose(); // Cerrar el menú
                new Window().start(); // Iniciar el juego
            }
            
            /*
            if(startOptions) {
            	running = false;
            	this.dispose();
            	new Menu_Options().start();
            }
            
            */
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
        if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_ESCAPE) {
        	new Menu_Inicio(this.getWidth(),this.getHeight()).start();
            running = false; // Detiene el ciclo del juego
            dispose(); // Cierra la ventana
            
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
