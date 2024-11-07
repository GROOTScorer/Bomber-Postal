package main.entities.characters;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.entities.Person;
import main.entities.Power_Up;
import main.entities.environment.Structure;
import main.entities.projectiles.Bomb;
import main.entities.projectiles.Bullet;
import main.ui.Window;

public class Player extends Person {

    // ----------------------- Declaracion de variables

	// Bombas
    private List<Bomb> bombs = new ArrayList<>();
    private long lastBombTime = 0; 
    private long BOMB_DELAY = 5000; 
    
    
    // Balas
    private List<Bullet> bullets = new ArrayList<>();
    private long lastShootTime = 0; 
    private final long SHOOT_DELAY = 390; 
    private int lastDirection = 1;
    public int MAX_BULLETS = 10; // Cantidad máxima de balas
    public int remainingBullets = MAX_BULLETS; // Balas restantes
	
    // Power Ups


    
    // ----------------------- Fin de Declaracion de variables

    public Player(int x, int y, int width, int height, int health, int speed) {
        super(x, y, width, height, health, speed);
    }

    public void update(boolean left, boolean right, boolean up, boolean down, boolean a, boolean d, boolean w, boolean s, boolean space, boolean shoot, List<Structure> structures, List<Power_Up> powerUps, int bloqueTam) {
        if (isAlive()) {
        	// Llamamos al movimiento
            handleMove(left, right, up, down, a, d, w, s, structures, bloqueTam);
            
            // Llamamos a las bombas
            long currentTime = System.currentTimeMillis();
            handleBombs(space,currentTime,structures);
            
            handleShoot(shoot,currentTime, structures);
            
            checkPowerUpCollision(powerUps);
        }
    }
    
    // Metodo para dibujar al jugador y las bombas
    public void draw(Graphics g) {
        super.draw(g); // Dibuja al jugador
        for (Bomb bomb : bombs) {
            bomb.draw(g);
        }
        
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }


    // Controlar movimiento por el usuario
    private void handleMove(boolean left, boolean right, boolean up, boolean down, boolean a, boolean d, boolean w, boolean s, List<Structure> structures, int bloqueTam) {
        int newX = x, newY = y;

        if (left || a) {
            newX -= bloqueTam / 16;
            lastDirection = 2; // Izquierda
        }
        if (right || d) {
            newX += bloqueTam / 16;
            lastDirection = 0; // Derecha
        }
        if (up || w) {
            newY -= bloqueTam / 16;
            lastDirection = 1; // Arriba
        }
        if (down || s) {
            newY += bloqueTam / 16;
            lastDirection = 3; // Abajo
        }


        // Pasar la lista de estructuras a checkCollision
        if (!checkCollision(newX, y, structures)) x = newX;
        if (!checkCollision(x, newY, structures)) y = newY;

        // Limitar los bordes
        x = Math.max(Window.limiteJugadorIzquierda, Math.min(x, Window.limiteJugadorDerecha));
        y = Math.max(Window.limiteJugadorArriba, Math.min(y, Window.limiteJugadorAbajo));
    }

    private void handleBombs(boolean space, long currentTime, List<Structure> structures) {
        if (space && (currentTime - lastBombTime) >= BOMB_DELAY) {
            bombs.add(new Bomb(x + width / 2 - (int) (Window.anchoResponsive * 0.019), y + height / 2 - (int) (Window.anchoResponsive * 0.019)));
            lastBombTime = currentTime; 
        }

        // Actualizar bombas
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update(structures);
            if (bombs.get(i).hasExploded()) {
                bombs.remove(i);
                i--;
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
            bullet.update(structures);

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
    			if(this.speed < 5.2) {
        	        this.speed++;
    			}
    		break;
    		
    		case 2:
    			if(MAX_BULLETS < 25) {
    				MAX_BULLETS+=5;	
    			}
    		break;
    		
    		case 3:
    			this.health++;
    			break;
    		
    		case 4:
    			if(BOMB_DELAY > 1000) {
        			BOMB_DELAY-=1000;		
    			}
    		break;
    	}
    }

    
    private void checkPowerUpCollision(List<Power_Up> powerUps) {
        Iterator<Power_Up> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            Power_Up powerUp = iterator.next();
            if (powerUp.isAlive() && collidesWith(powerUp)) {
                // Lógica cuando el jugador recoge un power-up
                handlePowerUp(powerUp);
                powerUp.deactivate(); // Desactivamos el power-up una vez recogido
                iterator.remove(); // Eliminamos el power-up de la lista
            }
        }
    }

    private boolean collidesWith(Power_Up powerUp) {
        return x < powerUp.getX() + powerUp.getWidth() &&
               x + width > powerUp.getX() &&
               y < powerUp.getY() + powerUp.getHeight() &&
               y + height > powerUp.getY();
    }

}
