package main.entities.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.entities.Person;
import main.entities.Power_Up;
import main.entities.environment.Structure;
import main.entities.projectiles.Bomb;
import main.entities.projectiles.Bullet;
import main.ui.Window;
import main.utils.FloatingText;

public class Player extends Person {

    // ----------------------- Declaracion de variables

	// Bombas
    public List<Bomb> bombs = new ArrayList<>();
    private List<FloatingText> floatingTexts = new ArrayList<>();

    private long lastBombTime = 0; 
    private long BOMB_DELAY = 5000; 

    private Clip stepSound; // Sonido de paso
    private Clip[] damageSounds;
    private final String[] damageSoundPaths = { // Rutas de los sonidos
    	    "assets/sounds/Sonidos/Daniado1.wav",
    	    "assets/sounds/Sonidos/Daniado2.wav",
    	    "assets/sounds/Sonidos/Daniado3.wav",
    	    "assets/sounds/Sonidos/Daniado4.wav"
    	};
    private long lastStepTime = 0; // Controla la frecuencia del sonido al caminar
    private static long STEP_DELAY = 300; // Retardo mínimo entre sonidos de pasos (ms)

    
    // Balas
    public List<Bullet> bullets = new ArrayList<>();
    private long lastShootTime = 0; 
    private final long SHOOT_DELAY = 390; 
    private int lastDirection = 1;
    public int MAX_BULLETS = 30; // Cantidad máxima de balas
    public int remainingBullets = MAX_BULLETS; // Balas restantes
	public int velocidadPower = 0, bombaPower = 0, cargadorPower = 0, vidaPower = 0;
    // Imagenes del jugador
    private Image spriteUp, spriteDown, spriteLeft, spriteRight;
    
    public boolean isDamaged = false;
    public long damageEffectStartTime = 0;


    private Clip powerUpSound; // Sonido de recogida de Power-Up

    
    // ----------------------- Fin de Declaracion de variables

    public Player(int x, int y, int width, int height, int health, int speed, Image spriteUp, Image spriteDown, Image spriteLeft, Image spriteRight, Window window) {
        super(x, y, width, height, health, speed);
        
        this.spriteUp = spriteUp;
        this.spriteDown = spriteDown;
        this.spriteLeft = spriteLeft;
        this.spriteRight = spriteRight;
        
        powerUpSound = loadSound("assets/sounds/Sonidos/PowerUpSound.wav");
        loadDamageSounds();
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

    public void update(boolean left, boolean right, boolean up, boolean down, boolean a, boolean d, boolean w, boolean s, boolean space, boolean shoot, List<Structure> structures, List<Power_Up> powerUps, int bloqueTam) {
        if (isAlive()) {
        	// Condicion para rapidez del sonido de caminata
        	if(velocidadPower == 0) {
        		STEP_DELAY = 300;
        	}
        	
        	else if(velocidadPower == 1) {
        		STEP_DELAY = 250;
        	}
        	
           	else if(velocidadPower == 2) {
        		STEP_DELAY = 200;
        	}
        	
           	else if(velocidadPower == 3) {
        		STEP_DELAY = 150;
        	}
        	
           	else if(velocidadPower == 4) {
        		STEP_DELAY = 100;
        	}
        	
            switch(Window.nivelSeleccionado) {
            	case 1:
            		switch(Window.frameSeleccionado) {
            			case 1:
            	        	stepSound = loadSound("assets/sounds/Sonidos/paso_alfombra.wav");
            			break;
            			
            			case 2:
            	        	stepSound = loadSound("assets/sounds/Sonidos/paso_marmol.wav");
            			break;
            			
            			case 3:
            			break;
            		}
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

        	
        	// Llamamos al movimiento
            handleMove(left, right, up, down, a, d, w, s, structures, bloqueTam);
            
            // Llamamos a las bombas
            long currentTime = System.currentTimeMillis();
            handleBombs(space,currentTime,structures);
            
            handleShoot(shoot,currentTime, structures);
            
            checkPowerUpCollision(powerUps);
        }
    }
    
    public void takeDamageBullet(List<Bullet> bullet) {
        for(int x = 0; x < bullet.size(); x++) {
        	if(bullet.get(x).collidesWithPlayer(this)) {
        		decrementHealth();
        		bullet.remove(x);
        	}
        }
    }
    
    // Metodo para dibujar al jugador y las bombas
    public void draw(Graphics g) {
    	 Image currentSprite = spriteDown;
    	
        super.draw(g); // Dibuja al jugador

        
        switch (lastDirection) {
        case 0: // Derecha
            currentSprite = spriteRight;
            break;
        case 1: // Arriba
            currentSprite = spriteUp;
            break;
        case 2: // Izquierda
            currentSprite = spriteLeft;
            break;
        case 3: // Abajo
            currentSprite = spriteDown;
            break;
        }
        
        g.drawImage(currentSprite, x, y-(height/2), width, height+(height/2), null); // Dibuja el sprite actual

        for (Bomb bomb : bombs) {
            bomb.draw(g);
        }
        
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        
        for (FloatingText text : floatingTexts) {
            text.draw(g);
        }
        
    }


    // Controlar movimiento por el usuario
    private void handleMove(boolean left, boolean right, boolean up, boolean down, boolean a, boolean d, boolean w, boolean s, List<Structure> structures, int bloqueTam) {
        int newX = x, newY = y;

        boolean moved = false; // Indica si el jugador se movió

        if (left || a) {
            newX -= bloqueTam / speed;
            lastDirection = 2; // Izquierda
            moved = true;
        }
        if (right || d) {
            newX += bloqueTam / speed;
            lastDirection = 0; // Derecha
            moved = true;
        }
        if (up || w) {
            newY -= bloqueTam / speed;
            lastDirection = 1; // Arriba
            moved = true;
        }
        if (down || s) {
            newY += bloqueTam / speed;
            lastDirection = 3; // Abajo
            moved = true;
        }

        // Pasar la lista de estructuras a checkCollision
        if (!checkCollision(newX, y, structures)) x = newX;
        if (!checkCollision(x, newY, structures)) y = newY;

        // Limitar los bordes
        x = Math.max(Window.limiteJugadorIzquierda, Math.min(x, Window.limiteJugadorDerecha));
        y = Math.max(Window.limiteJugadorArriba, Math.min(y, Window.limiteJugadorAbajo));

        // Reproducir sonido si el jugador se movió
        if (moved) {
            playStepSound();
        }
    }

    private void playPowerUpSound() {
        if (powerUpSound != null) {
            powerUpSound.setFramePosition(0); // Reinicia el clip para reproducir desde el principio
            powerUpSound.start();
        }
    }
    
    private void playStepSound() {
        long currentTime = System.currentTimeMillis();

        // Reproducir el sonido solo si ha pasado el retardo mínimo
        if (currentTime - lastStepTime >= STEP_DELAY) {
            if (stepSound != null) {
                stepSound.setFramePosition(0); // Reinicia el clip para reproducir desde el principio
                stepSound.start();
            }
            lastStepTime = currentTime; // Actualiza el tiempo del último sonido
        }
    }
    
    private void handleBombs(boolean space, long currentTime, List<Structure> structures) {
        if (space && (currentTime - lastBombTime) >= BOMB_DELAY) {
            bombs.add(new Bomb(x + width / 2 - (int) (Window.anchoResponsive * 0.019), 
                               y + height / 2 - (int) (Window.anchoResponsive * 0.019)));
            lastBombTime = currentTime; 
        }

        // Actualizar bombas
        if (!Window.menuPausa.isVisible()) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                bomb.update(structures, this,Window.inocentes, Window.policias, Window.militares); // Pasar el jugador actual

                // Solo eliminar la bomba cuando ya no tenga partículas activas
                if (bomb.hasExploded() && bomb.isParticlesFinished()) {
                    bombs.remove(i);
                    i--; // Ajustar el índice después de eliminar
                }
            }
        }
    }

    private void handleShoot(boolean shoot, long currentTime, List<Structure> structures) {
        if (shoot && remainingBullets > 0 && (currentTime - lastShootTime) >= SHOOT_DELAY) {
            if (lastDirection != -1) {
                bullets.add(new Bullet(x + width / 2 - 5, y + height / 2 - 5, lastDirection));
                lastShootTime = currentTime;
                remainingBullets--; // Reducir la cantidad de balas
            }
        }

        // Actualizar balas y verificar colisiones
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update(structures, this);

            // Verificar colisiones con las estructuras
            for (Structure structure : structures) {
                if (bullet.collidesWith(structure)) {
                    bullet.deactivate(); // Desactivar la bala si colisiona
                    break; // Salir del bucle si la bala colisiona con cualquier estructura
                }
            }

            // Eliminar balas inactivas
            if (!bullet.isActive()) {
                bullets.remove(i);
                i--; // Decrementar i para mantener la posición correcta
            }
        }
    }

    private void handlePowerUp(Power_Up powerUp) {

    	switch(powerUp.tipo) {
    		case 1:
    			if(velocidadPower < 4) {
        	        this.speed-=2;
        	        velocidadPower++;
    			}
    		break;
    		
    		case 2:
    			if(cargadorPower < 4) {
    				MAX_BULLETS+=5;	
    				cargadorPower++;
    			}
    		break;
    		
    		case 3:
    			if(this.health < 7) {
    				this.health++;
    			}
    			break;
    		
    		case 4:
    			if(bombaPower < 4) {
        			BOMB_DELAY-=1000;	
        			bombaPower++;
    			}
    		break;
    	}
    }

    
    private void checkPowerUpCollision(List<Power_Up> powerUps) {
        Iterator<Power_Up> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            Power_Up powerUp = iterator.next();
            if (powerUp.isAlive() && collidesWith(powerUp)) {
                // Lógica cuando el jugador recoge un Power-Up
                handlePowerUp(powerUp);
                playPowerUpSound();

                // Agregar texto flotante "+75"
                Window.puntuacion+=75;
                floatingTexts.add(new FloatingText(powerUp.getX(), powerUp.getY(), "+75", Color.RED));

                powerUp.deactivate(); // Desactivar el Power-Up
                iterator.remove();    // Eliminar el Power-Up de la lista
            }
        }
    }


    private boolean collidesWith(Power_Up powerUp) {
        return x < powerUp.getX() + powerUp.getWidth() &&
               x + width > powerUp.getX() &&
               y < powerUp.getY() + powerUp.getHeight() &&
               y + height > powerUp.getY();
    }

    // Getters
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
    public void decrementHealth() {
        if (health > 0) {
            this.health--;
            playRandomDamageSound(); // Reproducir un sonido aleatorio
            activateDamageEffect();  // Activar efecto de daño
        }
    }

    
    public void activateDamageEffect() {
        isDamaged = true;
        damageEffectStartTime = System.currentTimeMillis();  // Guarda el tiempo actual para el temporizador
    }
    
    private void loadDamageSounds() {
        damageSounds = new Clip[damageSoundPaths.length];
        for (int i = 0; i < damageSoundPaths.length; i++) {
            damageSounds[i] = loadSound(damageSoundPaths[i]);
        }
    }
    
    private void playRandomDamageSound() {
        if (damageSounds != null && damageSounds.length > 0) {
            int randomIndex = (int) (Math.random() * damageSounds.length); // Índice aleatorio
            Clip randomClip = damageSounds[randomIndex];
            if (randomClip != null) {
                randomClip.setFramePosition(0); // Reinicia el clip
                randomClip.start();
            }
        }
    }
    
    
}
