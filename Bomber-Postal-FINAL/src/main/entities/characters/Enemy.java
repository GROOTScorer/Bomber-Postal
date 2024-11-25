package main.entities.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList; // Importar ArrayList para inicializar la lista de balas
import java.util.List;

import main.entities.Person;
import main.entities.environment.Structure;
import main.entities.projectiles.Bullet;
import main.ui.Window;

public class Enemy extends Person {


    private long lastShootTime = 0;  // Para controlar el tiempo de disparo
    private long SHOOT_DELAY = 100;  // Delay entre disparos autom�ticos (1 segundo)

    private String currentDirection = "";  // Puede ser "UP", "DOWN", "LEFT", "RIGHT"

    private List<Bullet> bullets;  // Lista de balas que el enemigo dispara
    private int remainingBullets;  // Cantidad de balas restantes

    private boolean isPlayerInRange = false;
    
    public Enemy(int x, int y, int width, int height, int health, int speed, long shoot_delay, int max_bullets, int remaining_bullets) {
        super(x, y, width, height, health, speed);
        this.SHOOT_DELAY = shoot_delay;
        this.remainingBullets = remaining_bullets;
        bullets = new ArrayList<>(); // Inicializamos la lista de balas
    }

    public void update(List<Structure> structures, int bloqueTam, Player player, List<Bullet> BulletPlayer) {
        // Solo actualizamos si el enemigo est� vivo
        if (isAlive()) {
            long currentTime = System.currentTimeMillis();

            // Llamamos a autoMove solo si ha pasado suficiente tiempo desde la �ltima acci�n

            autoMove(structures, bloqueTam);
        

            // Aqu� podr�as incluir m�s l�gica, como disparar, atacar, etc.
            autoShoot(currentTime, player);
            
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.update(structures, player);
           
                if(!bullet.isActive()) {
                	bullets.remove(i);
                }
            }
            
            for(Bullet bullet1 : BulletPlayer) {
            	if(bullet1.collidesWithEnemy(this)) {
            		bullet1.deactivate();
            		this.health--;
            	}
            }
            
            player.takeDamageBullet(bullets);

        }
    }

    public void draw(Graphics g) {
        if (isAlive()) {
            sizeUpdate();
		    g.setColor(new Color(255, 255, 0, 0)); // Amarillo transparente
            g.fillRect(x, y, width, height); // Dibuja un rect�ngulo rojo para el enemigo
            
            /*
            
            if(currentDirection == "UP") {
            	g.setColor(Color.yellow);
                g.fillRect(x, y-height*2, width, height*2);
            }
            
            else if(currentDirection == "DOWN") {
            	g.setColor(Color.yellow);
                g.fillRect(x, y+height*1, width, height*2);
            }
            
            else if(currentDirection == "LEFT") {
            	g.setColor(Color.yellow);
                g.fillRect(x-width*2, y, width*2, height);
            }
            
            else if(currentDirection == "RIGHT") {
            	g.setColor(Color.yellow);
                g.fillRect(x+width*1, y, width*2, height);
            }
            
            */
            
            // Dibujar las balas disparadas
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            
            
        }
    }
    
    public void takeDamageBullet(List<Bullet> bullet) {
        for(Bullet bullet1 : bullet) {
        	if(bullet1.collidesWithEnemy(this)) {
        		this.health--;
        	}
        }
    }

    public void autoMove(List<Structure> structures, int bloqueTam) {
    if (isPlayerInRange) {
        return;  // El enemigo se queda quieto mientras el jugador está en rango
    }

    // El resto del código de movimiento va aquí
    if (currentDirection.isEmpty()) {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        currentDirection = directions[(int) (Math.random() * directions.length)];
    }

    int dx = 0;
    int dy = 0;

    switch (currentDirection) {
        case "UP":
            dy = -(bloqueTam / speed);
            break;
        case "DOWN":
            dy = bloqueTam / speed;
            break;
        case "LEFT":
            dx = -(bloqueTam / speed);
            break;
        case "RIGHT":
            dx = bloqueTam / speed;
            break;
    }

    int newX = this.getX() + dx;
    int newY = this.getY() + dy;

    if (!isCollision(newX, newY, structures)) {
        this.setX(newX);
        this.setY(newY);
    } else {
        currentDirection = "";  // Resetear la dirección para elegir una nueva
    }
}

    protected boolean detectPlayerInVisionRange(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int playerWidth = player.getWidth();
        int playerHeight = player.getHeight();

        // Calculamos el �rea de visi�n en funci�n de la direcci�n en la que el enemigo est� mirando
        int visionX = 0, visionY = 0, visionWidth = 0, visionHeight = 0;

        switch (currentDirection) {
            case "UP":
                visionX = x;
                visionY = y - height * 2; // �rea encima del enemigo
                visionWidth = width;
                visionHeight = height * 2;
                break;
            case "DOWN":
                visionX = x;
                visionY = y + height; // �rea debajo del enemigo
                visionWidth = width;
                visionHeight = height * 2;
                break;
            case "LEFT":
                visionX = x - width * 2; // �rea a la izquierda del enemigo
                visionY = y;
                visionWidth = width * 2;
                visionHeight = height;
                break;
            case "RIGHT":
                visionX = x + width; // �rea a la derecha del enemigo
                visionY = y;
                visionWidth = width * 2;
                visionHeight = height;
                break;
        }

        // Verificamos si el jugador est� dentro del �rea de visi�n
        return playerX + playerWidth > visionX &&
               playerX < visionX + visionWidth &&
               playerY + playerHeight > visionY &&
               playerY < visionY + visionHeight;
    }    
    
    // Esta funci�n controla el disparo autom�tico cuando el jugador est� dentro del rango de visi�n
    protected void autoShoot(long currentTime, Player player) {
        if (detectPlayerInVisionRange(player)) {
            isPlayerInRange = true;  // El jugador está en rango, el enemigo se quedará quieto

            if (remainingBullets > 0 && currentTime - lastShootTime >= SHOOT_DELAY) {
                int shootDirection = -1;
                switch (currentDirection) {
                    case "UP":
                        shootDirection = 1;  // Arriba
                        break;
                    case "DOWN":
                        shootDirection = 3;  // Abajo
                        break;
                    case "LEFT":
                        shootDirection = 2;  // Izquierda
                        break;
                    case "RIGHT":
                        shootDirection = 0;  // Derecha
                        break;
                }

                if (shootDirection != -1) {
                    // Crear una bala en la posición actual del enemigo
                    Bullet bullet = new Bullet(x + width / 2 - 5, y + height / 2 - 5, shootDirection);
                    bullets.add(bullet); // Agregar la bala a la lista de balas del enemigo
                    remainingBullets--;  // Reducir las balas restantes
                }

                lastShootTime = currentTime;  // Actualizamos el tiempo del último disparo
            }
        } else {
            // Si el jugador ya no está en rango, el enemigo puede moverse de nuevo
            isPlayerInRange = false;
        }
    } 
    
    protected boolean isCollision(int x, int y, List<Structure> structures) {
    	if (Window.resolucion != 4) {
    	    for (Structure structure : structures) {
    	        if (x < structure.getX() + structure.getWidth() &&
    	            x + Window.entitiesSize > structure.getX() &&
    	            y < structure.getY() + structure.getHeight() &&
    	            y + Window.entitiesSize > structure.getY()) {
    	            return true;  // Hay colisión
    	        } else if (x > Window.limiteJugadorDerecha || x < Window.limiteJugadorIzquierda || 
    	                   y > Window.limiteJugadorAbajo || y < Window.limiteJugadorArriba) {
    	            return true;
    	        }
    	    }
    	} else {
    	    // Ajustar la hitbox para que sea más pequeña
    	    int hitboxReduction = (int) (Window.entitiesSize * 0.1); // Reduce un 10% de cada lado
    	    int adjustedSize = Window.entitiesSize - hitboxReduction;

    	    for (Structure structure : structures) {
    	        if ((x + hitboxReduction / 2) < structure.getX() + structure.getWidth() &&
    	            (x + adjustedSize) > structure.getX() &&
    	            (y + hitboxReduction / 2) < structure.getY() + structure.getHeight() &&
    	            (y + adjustedSize) > structure.getY()) {
    	            return true;  // Hay colisión con hitbox reducida
    	        } else if (x > Window.limiteJugadorDerecha + hitboxReduction || x < Window.limiteJugadorIzquierda - hitboxReduction || 
    	                   y > Window.limiteJugadorAbajo + hitboxReduction || y < Window.limiteJugadorArriba- hitboxReduction) {
    	            return true;
    	        }
    	    }
    	}

    	
        return false;  // No hay colisi�n
    }
    
 // Clase Enemy

    public String getCurrentDirection() {
        return currentDirection;
    }
    
    public List<Bullet> getBullets() {
        return bullets;
    }


}
