package main.ui;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import main.entities.Power_Up;
import main.entities.characters.Player;
import main.entities.environment.Structure;
import main.levels.Level1;

public class Window extends JFrame implements Runnable, KeyListener {

	// -------------------------- Declaracion de variables --------------------------
	
	// Configuraciones generales -------------------
	
	private static final long serialVersionUID = 1L;
    public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	private static final double ASPECT_RATIO = 9.0 / 16.0;
    public static int alturaResponsive;
    public static int anchoResponsive;
	private Canvas canvas;
	private Thread thread;
	private boolean running;
	
	// Declaracion de graficos -----------------
	private BufferStrategy bs;
	private Graphics g;
	
	// Limitacion de FPS ----------------------
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	
	// Declaracion de entidades ---------------------------
	
	// Personajes
    private Player player;
    
    // Entorno
    List<Structure> structures = new ArrayList<>();
    
    // Proyectiles
    
    // PowerUps
    List<Power_Up> powerUps = new ArrayList<>();
    
	// Fin de Declaracion de entidades ---------------------------
       
    // Declaracion de eventos del teclado ----------
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean space = false;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean cPressed = false;
    private boolean iPressed = false;
    
    // Controlar la resolucion
    public int resolucion = 3;
    
    // Tabla lateral derecha
    public static int puntuacion = 0;
    
    // CronÃ³metro
    private long startTime;
    private long elapsedTime; 
    
    // Controlar niveles y frames
    public int nivelSeleccionado = 1;
    public static int frameSeleccionado = 1;
    
    Font font = new Font("smash", Font.BOLD, (int)(Window.anchoResponsive * 0.06));
    
    // Establecer limites del jugador con los bordes
    public static int limiteJugadorIzquierda, limiteJugadorDerecha, limiteJugadorArriba, limiteJugadorAbajo;
    
    // Manejo de niveles
    private Level1 level1;
    
    // Variables pos-optimizacion
    
    public static int entitiesSize = 0;
    public static int bloqueTam;
    public static int offsetX,offsetY;
    
    private Image barraLateralImage;
    
    // Variables para las cinematicas
    
    private List<Image> cinematicImages = new ArrayList<>();
    private int cinematicIndex = 0;
    private long cinematicStartTime = 0;
    private boolean isCinematicPlaying = true;

    private float fadeAlpha = 0.0f;    // Nivel de opacidad de la imagen de la cinemática
    private float fadeSpeed = 0.01f;   // Velocidad de cambio de opacidad
    private long fadeStartTime = 0;    // Marca de tiempo cuando comienza el fade
    private boolean isFadingIn = true; // Si el fade es hacia la aparición (fade in)
    private boolean isFadingOut = false; // Si el fade es hacia la desaparición (fade out)

	// ------------------------------------------------------------------------------ Fin de Declaracion de Variables
    
    // Constructor

    public Window() {
        // ----------------------------------- Configuraciones principales
        setTitle("Bomber Postal");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);
        add(canvas);
        canvas.addKeyListener(this);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                handleResize(); // Llamar al mï¿½todo para manejar el ajuste
            }
        });

        startTime = System.nanoTime();

        player = new Player(entitiesSize, entitiesSize, entitiesSize, entitiesSize, 3, 3);   

        level1 = new Level1();
        
        loadCinematicImages();
    }
    
    private void handleResize() {
        int newWidth = getWidth();
        int numFilas = 10; // Nï¿½mero de filas de la grilla
        int numColumnas = 15; // Nï¿½mero de columnas de la grilla
        int usableWidth = newWidth; // Ancho usable
        int cuadriculaWidth = (int) (usableWidth * 0.75); // Ancho de la grilla
        int bloqueTam = cuadriculaWidth / numColumnas; // Tamaï¿½o de cada bloque

        // Calcular la nueva altura basada en el nï¿½mero de filas
        int newHeight = bloqueTam * numFilas;
        // Establecer el nuevo tamaï¿½o
        setSize(newWidth, newHeight); // Mantener la relaciï¿½n de aspecto y ajustar la altura
        canvas.setPreferredSize(new Dimension(newWidth, newHeight)); // Actualizar canvas
        canvas.revalidate(); // Forzar la validaciï¿½n del layout
    }


	
	// Metodo que actualizara todo el juego (cada segundo literalmente)
	
	private void update() {
	    // ---------------------------------------- Actualizacion de variables relativas
	    
	    entitiesSize = (int) (anchoResponsive * 0.045);
	    alturaResponsive = getHeight();
	    anchoResponsive = getWidth();
	    
	    
	    if (updateResolution()) {
	        // Ajustar el tamaÃ±o de la ventana si la resoluciÃ³n ha cambiado
	        setSize(WIDTH, HEIGHT);
	        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	        canvas.revalidate();
	    }
	    
	 // Si estamos en fade in, aumentar la opacidad gradualmente
	    if (isFadingIn) {
	        
	        if (fadeAlpha < 1.0f) {
	            fadeAlpha += fadeSpeed; // Aumentar la opacidad
	        } else {
	            fadeAlpha = 1.0f; // Asegurarse de que no exceda 1
	            isFadingIn = false; // Dejar de hacer fade in cuando llega a 1
	        }
	    }

	    // Si estamos en fade out, reducir la opacidad gradualmente
	    if (isFadingOut) {
	        if (fadeAlpha > 0.0f) {
	            fadeAlpha -= fadeSpeed; // Reducir la opacidad
	        } else {
	            fadeAlpha = 0.0f; // Asegurarse de que no sea menor que 0
	            isFadingOut = false; // Dejar de hacer fade out cuando llega a 0
	        }
	    }

	    // Asegúrate de que fadeAlpha siempre esté entre 0.0 y 1.0
	    fadeAlpha = Math.max(0.0f, Math.min(fadeAlpha, 1.0f));

	    
	    if (isCinematicPlaying) {
	        long currentTime = System.nanoTime();

	        if (cinematicStartTime == 0) {
	            cinematicStartTime = currentTime; // Inicia el conteo de la cinemática
	        }
	        
	        // Muestra cada imagen durante 2 segundos (puedes ajustar el tiempo)
	        if ((currentTime - cinematicStartTime) >= 3_000_000_000L) { // 2 segundos en nanosegundos
	            cinematicStartTime = currentTime; // Reinicia el tiempo de inicio para la siguiente imagen
	            cinematicIndex++; // Pasa a la siguiente imagen
	            isFadingIn= true;
	            isFadingOut = false;
	            fadeAlpha = 1.0f;
	        }

	        if (cinematicIndex >= cinematicImages.size()) {
	            isCinematicPlaying = false; // Termina la cinemática cuando se acaban las imágenes
	            cinematicIndex = 0; // Resetea el índice para futuros ciclos de cinemática
	        }
	    }
	    
	    else {
	    	// ---------------------------------------- Fin de Actualizacion de variables relativas
		    
		    
			// ---------------------------------- Actualizacion de entidades
			player.update(leftPressed, rightPressed, upPressed, downPressed, aPressed, dPressed, wPressed, sPressed, space, cPressed, structures, powerUps, bloqueTam);

			// ---------------------------------- Fin de Actualizacion de entidades
			
			
			// ---------------------------------- Actualizacion de grilla y sus niveles 
			
			// Actualizacion de grilla
			int usableHeight = getHeight();
			int usableWidth = getWidth();
			int cuadriculaWidth = (int) (usableWidth * 0.75);
			int numFilas = 10;
			int numColumnas = 15;
			bloqueTam = cuadriculaWidth / numColumnas; // Tamaï¿½o de bloque basado en ancho
			int bloqueHeight = usableHeight / numFilas; // Tamaï¿½o de bloque basado en altura
			offsetX = (cuadriculaWidth - (bloqueTam * numColumnas)) / 2;
			offsetY = (usableHeight - (bloqueTam * numFilas)) / 2;
			bloqueTam = Math.min(bloqueTam, bloqueHeight); 
		  
			// ---------------------------------- Fin de Actualizacion de grilla y sus niveles 
	    }
	    
	    

	    

		// Actualizar el tiempo jugado
        elapsedTime = System.nanoTime() - startTime; // Calcular tiempo transcurrido
	}
	

	private boolean updateResolution() {
        int newWidth = WIDTH;
        int newHeight;

        switch (resolucion) {
        case 1:
            newWidth = 1920;  // 1080p estÃ¡ndar
            newHeight = 1080;
            break;
        case 2:
            newWidth = 1600;  // ResoluciÃ³n HD intermedia
            newHeight = 900;
            break;
        case 3:
            newWidth = 1280;  // 720p estÃ¡ndar
            newHeight = 720;
            break;
        case 4:
            newWidth = 1024;  // ResoluciÃ³n HD baja
            newHeight = 576;
            break;
        case 5:
            newWidth = 854;   // TamaÃ±o compacto 16:9
            newHeight = 480;
            break;
        default:
            newWidth = 800;   // TamaÃ±o compacto adicional
            newHeight = 450;

            break;
    }

        // Calcular la nueva altura basada en la relaciï¿½n de aspecto	
        newHeight = (int) (newWidth * ASPECT_RATIO);

        // Solo cambia si hay una diferencia en el ancho o la altura
        if (newWidth != WIDTH || newHeight != HEIGHT) {
            WIDTH = newWidth;
            HEIGHT = newHeight;
            return true; // Se ha cambiado la resoluciï¿½n
        }

        return false; // No se ha cambiado la resoluciï¿½n
	}
	
	private void loadCinematicImages() {
	    // Suponiendo que tienes imágenes con nombre secuencial como "cinematic_1.png", "cinematic_2.png", etc.
	    cinematicImages.add(Toolkit.getDefaultToolkit().getImage("assets/images/CI_1.jpg"));
	    cinematicImages.add(Toolkit.getDefaultToolkit().getImage("assets/images/CI_2.jpg"));
	    cinematicImages.add(Toolkit.getDefaultToolkit().getImage("assets/images/CI_3.jpg"));
	    // Agrega más imágenes según sea necesario
	}

	
	// --------------------------------------------------- Metodo que dibuja el juego
	
	private void draw() {
	    bs = canvas.getBufferStrategy();
	    if (bs == null) {
	        canvas.createBufferStrategy(3);
	        return;
	    }
	    
	    g = bs.getDrawGraphics();
	    
	    // -------------- FONDO DEFAULT -----------------------
	    g.setColor(Color.gray);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    // -------------- FIN FONDO DEFAULT -------------------
	    
	    if (isCinematicPlaying) {
	        // Verifica si la imagen de la cinemática está cargada
	        if (!cinematicImages.isEmpty()) {
	            // Crear una imagen de la cinemática con la opacidad controlada
	            Graphics2D g2d = (Graphics2D) g;
	            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));

	            // Dibujar la imagen de la cinemática con el fade
	            g.drawImage(cinematicImages.get(cinematicIndex), 0, 0, getWidth(), getHeight(), null);
	        }
	    }
	    
	    else {
	    	// ---------------------------------------------------- GRILLA
		    int usableHeight = getHeight() - getInsets().top - getInsets().bottom;
		    int usableWidth = getWidth() - getInsets().left - getInsets().right;
		    int cuadriculaWidth = (int) (usableWidth * 0.75);
		    int tablaWidth = (int) (usableWidth * 0.25);
		    g.setColor(Color.black);
		    g.fillRect(0, 0, cuadriculaWidth, usableHeight);
		    
		    int numFilas = 10;
		    int numColumnas = 15;
		    int bloqueWidth = cuadriculaWidth / numColumnas;
		    int bloqueHeight = usableHeight / numFilas;
		    bloqueTam = Math.min(bloqueWidth, bloqueHeight);
		    int offsetX = (cuadriculaWidth - (bloqueTam * numColumnas)) / 2;
		    int offsetY = (usableHeight - (bloqueTam * numFilas)) / 2;
		    
		    // Dibujo y actualizacion de niveles 
		    switch(nivelSeleccionado) {
		    	case 1:
		    		level1.draw(g, numColumnas, numFilas, bloqueTam, offsetX, offsetY, frameSeleccionado,structures);
			    	level1.update(structures, bloqueTam, offsetX, offsetY, iPressed, player, powerUps);
		    	break;
		    	
		    	case 2:
		    	break;
		    	
		    	case 3:
		    	break;
		    	
		    	case 4:
		    	break;
		    	
		    	case 5:
		    	break;
		    }
		    
		    // ---------------------------------------------------- FIN GRILLA
		    
		    // ------------------------------------------- Dibujo de entidades
		    
		    // Balas restantes
		    g.setColor(Color.RED);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString(player.remainingBullets + "/" + player.MAX_BULLETS, (int)(Window.anchoResponsive * 0.62), (int)(Window.alturaResponsive * 0.895));

		    // Vidas restantes
		    g.setColor(Color.RED);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("Vidas restantes: " + player.getHealth(), (int)(Window.anchoResponsive * 0.075), (int)(Window.alturaResponsive * 0.895));

		    
		    player.draw(g);
		    
		    
		    
		    for (Power_Up powerUps: powerUps) {
		        powerUps.draw(g);
		        powerUps.update(bloqueTam);
		    }
		    
			for(Structure structures : structures) {
				structures.draw(g);
			}
		    
		    // ------------------------------------------- Fin de Dibujo de entidades
		    
		   
		    // Cargar la imagen en el constructor o en algÃºn mÃ©todo de inicializaciÃ³n
		    barraLateralImage = Toolkit.getDefaultToolkit().getImage("assets/images/SpritesBomberPostal/Mapa/TablaNormal.png");

		    // ---------------------------------------------------- BARRA LATERAL

		    // Dibujar la imagen de la barra lateral en lugar del gradiente
		    if (barraLateralImage != null) {
		        g.drawImage(barraLateralImage, cuadriculaWidth, 0, tablaWidth, usableHeight, null);
		    } else {
		        // Fallback: Si la imagen no se encuentra, usar el gradiente como backup
		        GradientPaint gradient = new GradientPaint(
		            cuadriculaWidth, 0, new Color(45, 45, 48), 
		            cuadriculaWidth + tablaWidth, bloqueTam*numFilas, new Color(30, 30, 34));
		        Graphics2D g2d = (Graphics2D) g;
		        g2d.setPaint(gradient);
		        int newHeight = (int) (WIDTH* ASPECT_RATIO);
		        g2d.fillRect(cuadriculaWidth, 0, tablaWidth, newHeight);
		    }

		    // Texto estilizado
		    g.setColor(Color.RED);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("MARCADOR" , cuadriculaWidth + (int)(Window.anchoResponsive * 0.06), (int)(Window.alturaResponsive * 0.095));
		    
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("" + puntuacion , cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.16));

		    g.setFont(new Font("Smash", Font.BOLD, (int)(Window.anchoResponsive * 0.022))); // Fuente mÃ¡s moderna
		    g.setColor(Color.RED);
		    g.drawString("TIEMPO", cuadriculaWidth + (int)(Window.anchoResponsive * 0.08), (int)(Window.alturaResponsive * 0.235));
		    
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    int seconds = (int) (elapsedTime / 1_000_000_000); // Convertir de nanosegundos a segundos
		    g.drawString("" + seconds , cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.3));
		    
		    
		    g.setColor(Color.RED);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("ENEMIGOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.068), (int)(Window.alturaResponsive * 0.405));
		    
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("0", cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.48));


		    
		    g.setColor(Color.RED);
		    g.setFont(new Font("Smash", Font.BOLD, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("OBJETOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.07), (int)(Window.alturaResponsive * 0.55));

		    g.setColor(Color.WHITE);
		    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
		    g.drawString("0", cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.615));


		    // ---------------------------------------------------- FIN BARRA LATERAL
		    
	    }

	    
	    g.dispose();
	    bs.show();
	}
	
	// --------------------------------------------------- Funciones para empezar/detener el juego
	
	public void run() {
		long now = 0;
		long lastTime = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / TARGETTIME;
			lastTime = now;
			
			if (delta >= 1) {
				update();
				draw();
				delta--;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------- Fin de Funciones para empezar/detener el juego
	
	// --------------------------------------------------- Funciones de teclas
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			aPressed = true;
			break;
		case KeyEvent.VK_D:
			dPressed = true;
			break;
		case KeyEvent.VK_W:
			wPressed = true;
			break;
		case KeyEvent.VK_S:
			sPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			break;
		case KeyEvent.VK_UP:
			upPressed = true;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = true;
			break;
		case KeyEvent.VK_C:
			cPressed = true;
			break;
			
		case KeyEvent.VK_I:
			iPressed = true;
			break;
		case KeyEvent.VK_SPACE:
			space = true;
			break;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			aPressed = false;
			break;
		case KeyEvent.VK_D:
			dPressed = false;
			break;
		case KeyEvent.VK_W:
			wPressed = false;
			break;
		case KeyEvent.VK_S:
			sPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = false;
			break;
		case KeyEvent.VK_C:
			cPressed = false;
			break;
		case KeyEvent.VK_SPACE:
			space = false;
			break;
			
		case KeyEvent.VK_I:
			iPressed = false;
			break;	
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// no se necesita
	}
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	// --------------------------------------------------- Fin de Funciones de teclas
}