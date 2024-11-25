package main.ui;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.Timer;

import main.entities.Item;
import main.entities.Power_Up;
import main.entities.characters.Innocent;
import main.entities.characters.Military;
import main.entities.characters.Player;
import main.entities.characters.Police;
import main.entities.environment.Structure;
import main.entities.projectiles.Bomb;
import main.levels.Level1;
import main.levels.Level2;
import main.levels.Level3;
import main.levels.Level4;
import main.utils.AudioController;
import main.utils.FloatingText;

public class Window extends JFrame implements Runnable, KeyListener {

	// -------------------------- Declaracion de variables --------------------------
	
	// Configuraciones generales -------------------
	
	private static final long serialVersionUID = 1L;
    public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	private static final double ASPECT_RATIO = 9.0 / 16.0;
    public static int alturaResponsive;
    public static int anchoResponsive;
	private Canvas canvas;
	private Thread thread;
	public boolean running;
	
	// Declaracion de graficos -----------------
	private BufferStrategy bs;
	private Graphics g;
	
	// Limitacion de FPS ----------------------
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	
	// Declaracion de entidades ---------------------------
	
	// Auxiliares
    private List<FloatingText> floatingTexts = new ArrayList<>();
	
    // Controlar la musica
    private int musicaNivel = 1;
    
    
    private Clip backgroundMusic; // Clip para manejar la música de fondo

    
	// Control del guardado de nombre
    
    private boolean saveYourProgress = true;
    public static int totalSeconds;
	// Personajes
    private Player player;
    public static List<Innocent> inocentes = new ArrayList<>();
    public static List<Police> policias = new ArrayList<>();
    public static List<Military> militares = new ArrayList<>();
    // Entorno
    List<Structure> structures = new ArrayList<>();
    
    // Proyectiles
    
    // PowerUps
    List<Power_Up> powerUps = new ArrayList<>();
    
    // Items
    List<Item> items = new ArrayList<>();
    
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
    public static int resolucion = 2;
    
    // Tabla lateral derecha
    public static int puntuacion = 0;
    public static int objetosRestantes = 0;
    public static int enemigosRestantes = 0;
    
    // Cronómetro
    private long startTime;
    private long elapsedTime; 
    
    // Controlar niveles y frames
    public static int nivelSeleccionado = 1;
    public static int frameSeleccionado = 1;
    
    Font font = new Font("smash", Font.BOLD, (int)(Window.anchoResponsive * 0.06));
    
    // Establecer limites del jugador con los bordes
    public static int limiteJugadorIzquierda, limiteJugadorDerecha, limiteJugadorArriba, limiteJugadorAbajo;
    
    // Manejo de niveles
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private Level4 level4;


    
    // Variables pos-optimizacion
    
    public static int entitiesSize = 0;
    public static int bloqueTam;
    public static int offsetX,offsetY;
    
    private Image barraLateralImage;
    
    // Variables para las cinematicas
    
    private List<Image> cinematicImages = new ArrayList<>();
    private int cinematicIndex = 0;
    private boolean isCinematicPlaying = true;

    private float fadeAlpha = 0.0f;    // Nivel de opacidad de la imagen de la cinem�tica
    private float fadeSpeed = 0.005f;   // Velocidad de cambio de opacidad
    private boolean isFadingIn = true; // Si el fade es hacia la aparici�n (fade in)
    private boolean isFadingOut = false; // Si el fade es hacia la desaparici�n (fade out)

    public static int cuadriculaWidth;
    
    public static Menu_Pausa menuPausa;
    
    private Image playerStaticUp,playerStaticDown,playerStaticLeft,playerStaticRigth;
    private Image powerUpVelocidad,powerUpBotiquin, powerUpBomba, powerUpCargador;
    private Image barra0,barra1,barra2,barra3,barra4;
    
    
    private boolean gameOver = false;
    private int controladorGameOver = 0;
    private long tiempoInicioTransicion = 0;
    
    private boolean movimientoBloqueado = false;
    private long tiempoInicioBloqueo = 0;
    
    private boolean pasarNivel =  true;
	// ------------------------------------------------------------------------------ Fin de Declaracion de Variables
    
    // Constructor

    public Window() {
        // ----------------------------------- Configuraciones principales
        setTitle("Bomber Postal");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        setResizable(false);
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
                handleResize(); // Llamar al m�todo para manejar el ajuste
            }
        });

        startTime = System.nanoTime();
        try {
        
            playerStaticUp = ImageIO.read(new File("assets/images/SpritesBomberPostal/Personaje/PJStaticUp.png"));
            playerStaticDown = ImageIO.read(new File("assets/images/SpritesBomberPostal/Personaje/PJStaticDown.png"));
            playerStaticLeft = ImageIO.read(new File("assets/images/SpritesBomberPostal/Personaje/PJStaticLeft.png"));
            playerStaticRigth = ImageIO.read(new File("assets/images/SpritesBomberPostal/Personaje/PJStaticRigth.png"));
            powerUpVelocidad = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/PowerUps/Velocidad.png"));
            powerUpBotiquin = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/PowerUps/Botiquin.png"));
            powerUpBomba = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/PowerUps/RecorteDeTiempoBomba.png"));
            powerUpCargador = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/PowerUps/AumentoCargador.png"));
            
            barra0 =ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/BarraVacia.png"));
            barra1 =ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Barra1.png"));
            barra2 =ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Barra2.png"));
            barra3 =ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Barra3.png"));
            barra4 =ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Barra4.png"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        player = new Player(entitiesSize, entitiesSize, entitiesSize, entitiesSize, 3, 16,playerStaticUp,playerStaticDown,playerStaticLeft,playerStaticRigth, this);   
     
     
        level1 = new Level1();
        level2 = new Level2();
        level3 = new Level3();
        level4 = new Level4();



        menuPausa = new Menu_Pausa(this, player);
        
        loadCinematicImages();
        

        
        setLocationRelativeTo(null);
    }
    
    private void handleResize() {
        int newWidth = getWidth();
        int numFilas = 10; // N�mero de filas de la grilla
        int numColumnas = 15; // N�mero de columnas de la grilla
        int usableWidth = newWidth; // Ancho usable
        int cuadriculaWidth = (int) (usableWidth * 0.75); // Ancho de la grilla
        int bloqueTam = cuadriculaWidth / numColumnas; // Tama�o de cada bloque

        // Calcular la nueva altura basada en el n�mero de filas
        int newHeight = bloqueTam * numFilas;
        // Establecer el nuevo tama�o
        setSize(newWidth, newHeight); // Mantener la relaci�n de aspecto y ajustar la altura
        canvas.setPreferredSize(new Dimension(newWidth, newHeight)); // Actualizar canvas
        canvas.revalidate(); // Forzar la validaci�n del layout
        setLocationRelativeTo(null);
    }


	
	// Metodo que actualizara todo el juego (cada segundo literalmente)
	
	private void update() {
	    // ---------------------------------------- Actualizacion de variables relativas
	    bloqueTam = entitiesSize;
	
	    entitiesSize = (int) (anchoResponsive * 0.043);
		

	    alturaResponsive = getHeight();
	    anchoResponsive = getWidth();
	    
	    if (updateResolution()) {
	        // Ajustar el tamaño de la ventana si la resolución ha cambiado
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

	    // Aseg�rate de que fadeAlpha siempre est� entre 0.0 y 1.0
	    fadeAlpha = Math.max(0.0f, Math.min(fadeAlpha, 1.0f));

	    
        if (isCinematicPlaying) {
        	 playBackgroundMusic("assets/sounds/Sonidos/cinematicaSong.wav");
            // Si se ha presionado Enter, pasar a la siguiente imagen
            if (space) {
                // Reinicia el conteo de la cinem�tica
                space = false;
                // Cambia a la siguiente imagen
                cinematicIndex++; 
                isFadingIn = true;
                isFadingOut = false;
                fadeAlpha = 1.0f;
                
                // Resetea la cinem�tica si llegamos al final de las im�genes
                if (cinematicIndex >= cinematicImages.size()) {
                    isCinematicPlaying = false; // Termina la cinem�tica
                    cinematicIndex = 0; // Resetea el �ndice para futuros ciclos
                }
            }
        }
	    
	    else {
	        stopBackgroundMusic();
	    	// ---------------------------------------- Fin de Actualizacion de variables relativas
		    
		    
			// ---------------------------------- Actualizacion de entidades
	        if (!menuPausa.isVisible() ) {
	         	if(!pasarNivel) {
	         		player.update(leftPressed, rightPressed, upPressed, downPressed, aPressed, dPressed, wPressed, sPressed, space, cPressed, structures, powerUps, bloqueTam);
		        }
	         }
	        	
	        
	        
	   
	    	
			// ---------------------------------- Fin de Actualizacion de entidades
			
			
			// ---------------------------------- Actualizacion de grilla y sus niveles 
			
			// Actualizacion de grilla
			int usableHeight = getHeight();
			int usableWidth = getWidth();
			int cuadriculaWidth = (int) (usableWidth * 0.75);
			int numFilas = 10;
			int numColumnas = 15;
			bloqueTam = cuadriculaWidth / numColumnas; // Tama�o de bloque basado en ancho
			int bloqueHeight = usableHeight / numFilas; // Tama�o de bloque basado en altura
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
            newWidth = 1920;  // 1080p estándar
            newHeight = 1080;
            break;
        case 2:
            newWidth = 1600;  // Resolución HD intermedia
            newHeight = 900;
            break;
        case 3:
            newWidth = 1280;  // 720p estándar
            newHeight = 720;
            break;
        case 4:
            newWidth = 1024;  // Resolución HD baja
            newHeight = 576;
            break;
        case 5:
            newWidth = 854;   // Tamaño compacto 16:9
            newHeight = 480;
            break;
        default:
            newWidth = 800;   // Tamaño compacto adicional
            newHeight = 450;

            break;
    }

        // Calcular la nueva altura basada en la relaci�n de aspecto	
        newHeight = (int) (newWidth * ASPECT_RATIO);

        // Solo cambia si hay una diferencia en el ancho o la altura
        if (newWidth != WIDTH || newHeight != HEIGHT) {
            WIDTH = newWidth;
            HEIGHT = newHeight;
            return true; // Se ha cambiado la resoluci�n
        }

        return false; // No se ha cambiado la resoluci�n
	}
	
	private void loadCinematicImages() {
	    // Suponiendo que tienes im�genes con nombre secuencial como "cinematic_1.png", "cinematic_2.png", etc.
	    cinematicImages.add(Toolkit.getDefaultToolkit().getImage("assets/images/CI_1.jpg"));
	    cinematicImages.add(Toolkit.getDefaultToolkit().getImage("assets/images/CI_2.jpg"));
	    cinematicImages.add(Toolkit.getDefaultToolkit().getImage("assets/images/CI_3.jpg"));
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
	        // Verifica si la imagen de la cinem�tica est� cargada
	        if (!cinematicImages.isEmpty()) {
	            // Crear una imagen de la cinem�tica con la opacidad controlada
	            Graphics2D g2d = (Graphics2D) g;
	            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));

	            // Dibujar la imagen de la cinem�tica con el fade
	            g.drawImage(cinematicImages.get(cinematicIndex), 0, 0, getWidth(), getHeight(), null);
	        
	            g2d.setFont(new Font("Smash", Font.BOLD, (int)(Window.anchoResponsive*0.015)));
	            g2d.setColor(Color.WHITE);  // Cambia el color si deseas otro

	            // Texto a mostrar y su posición
	            String text = "Espacio para continuar...";
	            int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(text) / 2;  // Centra el texto horizontalmente
	            int y = getHeight() - 50;  // Posición vertical cerca de la parte inferior
	            g2d.drawString(text, x, y);
	        }
	    }
	    
	    else {
	    	// ---------------------------------------------------- GRILLA
		    int usableHeight = getHeight() - getInsets().top - getInsets().bottom;
		    int usableWidth = getWidth() - getInsets().left - getInsets().right;
		    cuadriculaWidth = (int) (usableWidth * 0.75);
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
		    if (frameSeleccionado == 4) {
		        nivelSeleccionado++;
		        frameSeleccionado = 1;
		        pasarNivel = true;
		        inocentes.clear();            // Vaciar lista de inocentes
		        policias.clear();             // Vaciar lista de policías
		        militares.clear();            // Vaciar lista de militares
		        structures.clear();           // Vaciar lista de estructuras
		        powerUps.clear();             // Vaciar lista de power-ups
		        items.clear();                // Vaciar lista de items
		        AudioController.stopBackgroundMusic();
		        musicaNivel = 1;
		    }

  
		    switch(nivelSeleccionado) {
		    case 1:
		        if (pasarNivel) {
		            manejarTransicion(g); // Ejecuta la transición

		            // Al finalizar la transición, inicia el bloqueo
		            if (!movimientoBloqueado) {
		                movimientoBloqueado = true; // Activa el bloqueo
		                tiempoInicioBloqueo = System.currentTimeMillis(); // Marca el inicio del bloqueo
		            }
		        } else {
		            // Bloqueo de movimiento por 0.5 segundos después de la transición
		            if (movimientoBloqueado) {
		                long tiempoTranscurridoBloqueo = System.currentTimeMillis() - tiempoInicioBloqueo;

		                if (tiempoTranscurridoBloqueo < 3000) {
		                    // Durante el bloqueo, fija la posición del jugador
		                    player.setX((int) (Window.anchoResponsive * 0.043) * 1 + offsetX);
		                    player.setY((int) (Window.anchoResponsive * 0.043) * 1 + offsetY);
		                } else {
		                    // Fin del bloqueo
		                    movimientoBloqueado = false;
		                }
		            } 
	                // Ejecuta la lógica del nivel normalmente
	                if (musicaNivel == 1) {
	                    AudioController.playBackgroundMusic("assets/sounds/Sonidos/Musica_Fondo_Nivel.wav");
	                    musicaNivel = 0;
	                }
	                level1.draw(g, numColumnas, numFilas, bloqueTam, offsetX, offsetY, frameSeleccionado, structures);
	                if (!menuPausa.isVisible()) {
	                    level1.update(structures, bloqueTam, offsetX, offsetY, iPressed, player, powerUps, inocentes, policias, militares, items, floatingTexts, player.bombs);
	                }
		            
		        }
		        break;
		    	
		    case 2:
		        if (pasarNivel) {
		            manejarTransicion(g); // Ejecuta la transición

		            // Al finalizar la transición, inicia el bloqueo
		            if (!movimientoBloqueado) {
		                movimientoBloqueado = true; // Activa el bloqueo
		                tiempoInicioBloqueo = System.currentTimeMillis(); // Marca el inicio del bloqueo
		            }
		        } else {
		            // Bloqueo de movimiento por 0.5 segundos después de la transición
		            if (movimientoBloqueado) {
		                long tiempoTranscurridoBloqueo = System.currentTimeMillis() - tiempoInicioBloqueo;

		                if (tiempoTranscurridoBloqueo < 3000) {
		                    // Durante el bloqueo, fija la posición del jugador
		                    player.setX((int) (Window.anchoResponsive * 0.043) * 1 + offsetX);
		                    player.setY((int) (Window.anchoResponsive * 0.043) * 1 + offsetY);
		                } else {
		                    // Fin del bloqueo
		                    movimientoBloqueado = false;
		                }
		            } 
	                // Ejecuta la lógica del nivel normalmente
	                if (musicaNivel == 1) {
	                    AudioController.playBackgroundMusic("assets/sounds/Sonidos/Nivel2Musica.wav");
	                    musicaNivel = 0;
	                }
	                level2.draw(g, numColumnas, numFilas, bloqueTam, offsetX, offsetY, frameSeleccionado, structures);
	                if (!menuPausa.isVisible()) {
	                    level2.update(structures, bloqueTam, offsetX, offsetY, iPressed, player, powerUps, inocentes, policias, militares, items, floatingTexts, player.bombs);
	                }
		            
		        }
		        break;
		    	
		    	case 3:
		    		if(pasarNivel) {
		    			manejarTransicion(g);
		    		}
		    		else {
		        		if(musicaNivel == 1) {
			    	        AudioController.playBackgroundMusic("assets/sounds/Sonidos/Nivel3Musica.wav");
			    	        musicaNivel = 0;
			    		}
			    		level3.draw(g, numColumnas, numFilas, bloqueTam, offsetX, offsetY, frameSeleccionado,structures);
					    if (!menuPausa.isVisible()) {
					    	level3.update(structures, bloqueTam, offsetX, offsetY, iPressed, player, powerUps, inocentes, policias, militares, items, floatingTexts, player.bombs);
					    }
		    		}
		
				    
				  
		    	break;
		    	
		    	case 4:		    
			        if (pasarNivel) {
			            manejarTransicion(g); // Ejecuta la transición

			            // Al finalizar la transición, inicia el bloqueo
			            if (!movimientoBloqueado) {
			                movimientoBloqueado = true; // Activa el bloqueo
			                tiempoInicioBloqueo = System.currentTimeMillis(); // Marca el inicio del bloqueo
			            }
			        } else {
			            // Bloqueo de movimiento por 0.5 segundos después de la transición
			            if (movimientoBloqueado) {
			                long tiempoTranscurridoBloqueo = System.currentTimeMillis() - tiempoInicioBloqueo;

			                if (tiempoTranscurridoBloqueo < 3000) {
			                    // Durante el bloqueo, fija la posición del jugador
			                    player.setX((int) (Window.anchoResponsive * 0.043) * 1 + offsetX);
			                    player.setY((int) (Window.anchoResponsive * 0.043) * 1 + offsetY);
			                } else {
			                    // Fin del bloqueo
			                    movimientoBloqueado = false;
			                }
			            } 
		                // Ejecuta la lógica del nivel normalmente
		                if (musicaNivel == 1) {
		                    AudioController.playBackgroundMusic("assets/sounds/Sonidos/Nivel4Musica.wav");
		                    musicaNivel = 0;
		                }
		                level4.draw(g, numColumnas, numFilas, bloqueTam, offsetX, offsetY, frameSeleccionado, structures);
		                if (!menuPausa.isVisible()) {
		                    level4.update(structures, bloqueTam, offsetX, offsetY, iPressed, player, powerUps, inocentes, policias, militares, items, floatingTexts, player.bombs);
		                }
			            
			        }
		    	break;
		    	
		    	case 5:
		    		if(saveYourProgress) {
		    			stopBackgroundMusic();
		    			saveYourProgress = false;
		    	        Save_Progress saveProgress = new Save_Progress(this.getWidth(),this.getHeight());
		    	        saveProgress.start();
			    		running = false;
			    		this.dispose();
			    		this.stop();
		    		
			    	break;
		    		}
		    		

		    }
		    
		    // ---------------------------------------------------- FIN GRILLA
		    
		    // ------------------------------------------- Dibujo de entidades
		    
		 // Fuente común para ambas etiquetas
		    Font font = new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022));
		    g.setFont(font);

		    // Texto para las etiquetas
		    String bulletsText = player.remainingBullets + "/" + player.MAX_BULLETS;
		    String livesText = "Vidas restantes: " + player.getHealth();

		    // Condicional para ajustar la posición Y dependiendo de la resolución
		    int bulletsY, livesY;

		    if (resolucion == 1) {
		        // Ajuste de la posición Y para resolución 'x'
		        bulletsY = (int)(Window.alturaResponsive * 0.91); // Por ejemplo, mover más arriba
		        livesY = (int)(Window.alturaResponsive * 0.91);   // Ajustar de manera similar
		    } 
		    
		    else if(resolucion == 2){
		        // Posición Y por defecto
		        bulletsY = (int)(Window.alturaResponsive * 0.906); // Valor por defecto
		        livesY = (int)(Window.alturaResponsive * 0.906);   // Valor por defecto
		    }
		    
		    else if(resolucion == 3){
		        // Posición Y por defecto
		        bulletsY = (int)(Window.alturaResponsive * 0.894); // Valor por defecto
		        livesY = (int)(Window.alturaResponsive * 0.894);   // Valor por defecto
		    }

		    else if(resolucion == 4){
		        // Posición Y por defecto
		        bulletsY = (int)(Window.alturaResponsive * 0.88); // Valor por defecto
		        livesY = (int)(Window.alturaResponsive * 0.88);   // Valor por defecto
		    }
		    
		    else {
		        bulletsY = (int)(Window.alturaResponsive * 0.87); // Valor por defecto
		        livesY = (int)(Window.alturaResponsive * 0.87);   // Valor por defecto
		    }

		    // Posición y tamaño de las etiquetas
		    int bulletsX = (int)(Window.anchoResponsive * 0.62);
		    int livesX = (int)(Window.anchoResponsive * 0.075);

		    // Dibujar fondo para balas restantes
		    FontMetrics fm21 = g.getFontMetrics(font);
		    int bulletsWidth = fm21.stringWidth(bulletsText) + 10; // Ancho del fondo (+ padding)
		    int bulletsHeight = fm21.getHeight() + 4; // Alto del fondo (+ padding)
		    g.setColor(new Color(0, 0, 0, 150)); // Fondo negro translúcido
		    g.fillRect(bulletsX - 5, bulletsY - fm21.getAscent() - 2, bulletsWidth, bulletsHeight);

		    // Dibujar texto para balas restantes
		    g.setColor(Color.RED);
		    if(!pasarNivel) {
			    g.drawString(bulletsText, bulletsX, bulletsY);
		    }


		    // Dibujar fondo para vidas restantes
		    int livesWidth = fm21.stringWidth(livesText) + 10; // Ancho del fondo (+ padding)
		    int livesHeight = fm21.getHeight() + 4; // Alto del fondo (+ padding)
		    g.setColor(new Color(0, 0, 0, 150)); // Fondo negro translúcido
		    g.fillRect(livesX - 5, livesY - fm21.getAscent() - 2, livesWidth, livesHeight);

		    // Dibujar texto para vidas restantes
		    g.setColor(Color.RED);
		    if(!pasarNivel) {
			    g.drawString(livesText, livesX, livesY);
		    }

	     	
	        floatingTexts.removeIf(text -> !text.isActive()); // Eliminar los textos inactivos
	        for (FloatingText text : floatingTexts) {
	            text.draw(g);
	        }
	        
	        
		    if(!pasarNivel) {
			    for (Power_Up powerUps: powerUps) {
			        powerUps.draw(g);
			        powerUps.update(bloqueTam);
			    }
			    
				for(Structure structures : structures) {
					structures.draw(g);
				}
				
		     	for(Innocent inocentes: inocentes) {
		     		inocentes.draw(g);
		     	}
		     	
		     	for(Police policias: policias) {
		     		policias.draw(g);
		     	}

		     	for(Military militares: militares) {
		     		militares.draw(g);
		     	}
		     	
		     	for(Item items: items) {
		     		items.draw(g);
		  
		     	}
		  
			    player.draw(g);
		    }



		    // ------------------------------------------- Fin de Dibujo de entidades
		    
		   
		    // Cargar la imagen en el constructor o en algún método de inicialización
		    barraLateralImage = Toolkit.getDefaultToolkit().getImage("assets/images/SpritesBomberPostal/Mapa/TablaNormal.png");

		    // ---------------------------------------------------- BARRA LATERAL


		    if(!pasarNivel) {
		    	

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
	
			    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022))); // Fuente más moderna
			    g.setColor(Color.RED);
			    
			    
			    if(resolucion == 5) {
			    	g.drawString("TIEMPO", cuadriculaWidth + (int)(Window.anchoResponsive * 0.08), (int)(Window.alturaResponsive * 0.225));
			    }
			    else {
			    	g.drawString("TIEMPO", cuadriculaWidth + (int)(Window.anchoResponsive * 0.08), (int)(Window.alturaResponsive * 0.235));
				    
			    }
			    
			    g.setColor(Color.WHITE);
			    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
	
			    // Convertir el tiempo a segundos y luego obtener minutos y segundos
			    totalSeconds = (int) (elapsedTime / 1_000_000_000); // Convertir de nanosegundos a segundos
			    int minutes = totalSeconds / 60;
			    int seconds = totalSeconds % 60;
	
			    // Formatear el tiempo a "00:00"
			    String timeFormatted = String.format("%02d:%02d", minutes, seconds);
	
			    if(resolucion == 5) {
			    	g.drawString(timeFormatted, cuadriculaWidth + (int)(Window.anchoResponsive * 0.1), (int)(Window.alturaResponsive * 0.29));
			    }
			    
			    else {
			    	g.drawString(timeFormatted, cuadriculaWidth + (int)(Window.anchoResponsive * 0.1), (int)(Window.alturaResponsive * 0.3));
			    }
			    
			    g.setColor(Color.RED);
			    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
			    
			    if(resolucion == 4) {
			    	 g.drawString("ENEMIGOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.068), (int)(Window.alturaResponsive * 0.4));
			    }
			    
			    else if(resolucion == 5) {
			    	g.drawString("ENEMIGOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.068), (int)(Window.alturaResponsive * 0.39));
			    }
			    
			    else {
			    	g.drawString("ENEMIGOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.068), (int)(Window.alturaResponsive * 0.41));
			    
			    }
			   
			    g.setColor(Color.WHITE);
			    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
			    
			    if(resolucion == 4) {
			    	g.drawString("" + enemigosRestantes, cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.47));
			    }
			    
			    else if(resolucion == 5) {
			    	g.drawString("" + enemigosRestantes, cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.46));
			    }
			    
			    else {
			    	g.drawString("" + enemigosRestantes, cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.48));
	
			    }
			    
			    
			    g.setColor(Color.RED);
			    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
			   
			    if(resolucion == 4) {
			    	g.drawString("OBJETOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.07), (int)(Window.alturaResponsive * 0.54));
			    }
			    
			    else if(resolucion == 5) {
			    	g.drawString("OBJETOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.07), (int)(Window.alturaResponsive * 0.53));
			    }
			    
			    else {
			    	g.drawString("OBJETOS", cuadriculaWidth + (int)(Window.anchoResponsive * 0.07), (int)(Window.alturaResponsive * 0.55));
	
			    }
			    
			    
			    
			    g.setColor(Color.WHITE);
			    g.setFont(new Font("Smash", Font.PLAIN, (int)(Window.anchoResponsive * 0.022)));
			    
			    if(resolucion == 4) {
			    	 g.drawString("" + objetosRestantes, cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.6));
			    }
			    
			    else if(resolucion == 5) {
			    	g.drawString("" + objetosRestantes, cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.585));
			    }
			    
			    else {
			    	 g.drawString("" + objetosRestantes, cuadriculaWidth + (int)(Window.anchoResponsive * 0.115), (int)(Window.alturaResponsive * 0.615));
	
			    }
			    
			   
			    int imagenX = cuadriculaWidth + (int)(Window.anchoResponsive * 0.035); // Ajusta la posici�n X
			    int imagenY = (int)(Window.alturaResponsive * 0.675); // Ajusta la posici�n Y
	
			    
			    // Para la velocidad
			    g.drawImage(powerUpVelocidad, imagenX, imagenY, bloqueTam - (bloqueTam/4), bloqueTam - (bloqueTam/4), null);
			    if(player.velocidadPower == 0) {
				    g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.velocidadPower == 1) {
				    g.drawImage(barra1, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.velocidadPower == 2) {
				    g.drawImage(barra2, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.velocidadPower == 3) {
				    g.drawImage(barra3, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.velocidadPower == 4) {
				    g.drawImage(barra4, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else {
			    	g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    // Para el tamaño del cargdor
			    g.drawImage(powerUpBotiquin, imagenX, imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam - (bloqueTam/4), bloqueTam - (bloqueTam/4), null);
			    if(player.cargadorPower == 0) {
			    	 g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);   
			    }
			   
			    else if(player.cargadorPower == 1) {
			    	 g.drawImage(barra1, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);   
			    }
			    
			    else if(player.cargadorPower == 2) {
			    	 g.drawImage(barra2, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);   
			    }
			    
			    else if(player.cargadorPower == 3) {
			    	 g.drawImage(barra3, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);   
			    }
			    
			    else if(player.cargadorPower == 4) {
			    	 g.drawImage(barra4, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);   
			    }
			    
			    else {
			    	g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);   
			    }
			    
			    // Para el tiempo de la bomba
			    g.drawImage(powerUpBomba, imagenX + (int)(Window.anchoResponsive * 0.0955), imagenY, bloqueTam - (bloqueTam/4), bloqueTam - (bloqueTam/4), null);
			    if(player.bombaPower == 0) {
			    	  g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.bombaPower == 1) {
			    	g.drawImage(barra1, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.bombaPower == 2) {
			    	g.drawImage(barra2, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.bombaPower == 3) {
			    	g.drawImage(barra3, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.bombaPower == 4) {
			    	g.drawImage(barra4, imagenX + (int)(Window.anchoResponsive * 0.1355), imagenY, bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    
			    // Para la vida
			    g.drawImage(powerUpCargador, imagenX + (int)(Window.anchoResponsive * 0.0955), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam - (bloqueTam/4), bloqueTam - (bloqueTam/4), null);
			    
			    if(player.getHealth() == 3) {
			    	 g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.getHealth() == 4) {
			    	g.drawImage(barra1, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.getHealth() == 5) {
			    	g.drawImage(barra2, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.getHealth() == 6) {
			    	g.drawImage(barra3, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			    
			    else if(player.getHealth() == 7) {
			    	g.drawImage(barra4, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
			   
			    else {
			    	g.drawImage(barra0, imagenX + (int)(Window.anchoResponsive * 0.0425), imagenY + (int)(Window.alturaResponsive * 0.118), bloqueTam, bloqueTam - (bloqueTam/4), null);
			    }
		    }
		    // ---------------------------------------------------- FIN BARRA LATERAL
	        menuPausa.draw(g, cuadriculaWidth, HEIGHT);
	        
	        if (player.isDamaged) {
	            long currentTime = System.currentTimeMillis();
	            if (currentTime - player.damageEffectStartTime < 200) {
	                g.setColor(new Color(255, 0, 0, 100));  
	                g.fillRect(0, 0, cuadriculaWidth, getHeight());
	            } else {
	                player.isDamaged = false;  
	            }
	        }
	        

	        
	        if (player.getHealth() == 0) {
		        
	        	if(controladorGameOver == 0) {
		        	gameOver = true;
		        	controladorGameOver++;
	        	}

	            // Dibuja el fondo oscuro
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

	            // Dibuja el texto "Game Over" en el centro
	            g.setColor(Color.RED);
	            g.setFont(new Font("smash", Font.BOLD, (int)(anchoResponsive*0.07)));
	            String message = "ESTAS MUERTO";
	            FontMetrics fm = g.getFontMetrics();
	            int x = (getWidth() - fm.stringWidth(message)) / 2;
	            int y = (getHeight() / 2) + (fm.getAscent() / 2);
	            g.drawString(message, x, y);

	        } 
	        
	        if(gameOver) {
	        	
		        for (Bomb bomb : player.bombs) { // Iterar sobre las bombas del jugador
		            bomb.stopBombSound();       // Llamar al método para detener el sonido del temporizador
		        }
	        	// Resetear variables
	        	nivelSeleccionado = 1;
	        	frameSeleccionado = 1;
	        	objetosRestantes = 0;
	        	enemigosRestantes = 0;
	        	puntuacion = 0;
	        	
	        	militares.clear();
	        	inocentes.clear();
	        	policias.clear();
	        	
	            Timer timer = new Timer(3000, e -> {
	                // Cierra la ventana actual
	            	AudioController.stopBackgroundMusic();
	                this.dispose();
	                running = false;
	        		new Menu_Inicio(this.getWidth(), this.getHeight()).start();
	                
	                
	            });
	            timer.setRepeats(false);  // Solo ejecuta una vez
	            timer.start();	        	
	        }
	    }

	    
	    g.dispose();
	    bs.show();
	}
	
	public int getCuadriculaWidth() {
		return cuadriculaWidth;
	}
	
	// --------------------------------------------------- Funciones para empezar/detener el juego
	
	public void run() {
	    long now;
	    long lastTime = System.nanoTime();

	    while (running) {
	        now = System.nanoTime();
	        delta += (now - lastTime) / TARGETTIME;
	        lastTime = now;

	        if (delta >= 1) {
	            if (!gameOver) {
	                update();
	                draw(); // Solo se ejecuta si 'running' es true
	            }
	            delta--;
	        }
	    }
	}

	public synchronized void start() {
	    running = true; // Mueve esta línea antes de iniciar el hilo
	    thread = new Thread(this);
	    thread.start();
	}

	public synchronized void stop() {
	    running = false; // Detiene el ciclo antes de llamar a 'join()'
	    try {
	        if (thread != null && thread.isAlive()) {
	            thread.join(); // Une el hilo de manera segura
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}



	public void stopAudio() {
	    // Detiene la música de fondo y efectos de sonido
	    AudioController.stopBackgroundMusic();
	    
	}


	
	// --------------------------------------------------- Fin de Funciones para empezar/detener el juego
	
	// --------------------------------------------------- Funciones de teclas
	@Override
	public void keyPressed(KeyEvent e) {
        if (menuPausa.isVisible()) {
            menuPausa.keyPressed(e, player.bombs);
            return;
        }
		
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
			
		case KeyEvent.VK_P:
	        menuPausa.showMenu();
	        break;
	        
		case KeyEvent.VK_ESCAPE:
	        menuPausa.showMenu();
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
	
	private void playBackgroundMusic(String filePath) {
	    try {
	        if (backgroundMusic == null || !backgroundMusic.isRunning()) {
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
	            backgroundMusic = AudioSystem.getClip();
	            backgroundMusic.open(audioInputStream);
	            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Reproducir en bucle
	            backgroundMusic.start();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void stopBackgroundMusic() {
	    if (backgroundMusic != null && backgroundMusic.isRunning()) {
	        backgroundMusic.stop();   // Detener la reproducción
	        backgroundMusic.close(); // Liberar recursos
	        backgroundMusic = null;
	    }
	}
	public void manejarTransicion(Graphics g) {
	    // Registrar el inicio del tiempo si es la primera vez que se llama
	    if (tiempoInicioTransicion == 0) {
	        tiempoInicioTransicion = System.currentTimeMillis();
	    }

	    // Dimensiones de la pantalla
	    int width = getWidth();
	    int height = getHeight();

	    // Dibujar la pantalla de transición
	    g.setColor(Color.BLACK);
	    g.fillRect(0, 0, width, height);

	    // Texto del nivel
	    g.setColor(Color.WHITE);

	    // Calcular tamaño de fuente dinámico basado en el ancho de la pantalla
	    int fontSize = (int) (height * 0.05); // 5% de la altura de la pantalla
	    g.setFont(new Font("Smash", Font.BOLD, fontSize));
	    
	    // Centrar el texto dinámicamente
	    String textoNivel = "Nivel " + nivelSeleccionado;
	    int textoNivelWidth = g.getFontMetrics().stringWidth(textoNivel);
	    int textoNivelHeight = g.getFontMetrics().getHeight();
	    g.drawString(textoNivel, (width - textoNivelWidth) / 2, height / 2 - textoNivelHeight);

	    // Cálculo del tiempo restante
	    long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicioTransicion;


	    // Si pasaron los 2 segundos, finalizar la transición
	    if (tiempoTranscurrido >= 2000) {
	        pasarNivel = false;
	        tiempoInicioTransicion = 0; // Reiniciar el tiempo para la próxima transición
	    }
	}



	// --------------------------------------------------- Fin de Funciones de teclas
}