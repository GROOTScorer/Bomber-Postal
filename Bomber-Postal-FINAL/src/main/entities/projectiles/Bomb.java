package main.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.entities.characters.Innocent;
import main.entities.characters.Military;
import main.entities.characters.Player;
import main.entities.characters.Police;
import main.entities.environment.Structure;
import main.ui.Window;

public class Bomb {
    private int x, y;
    private int size;
    private long creationTime;
    private final long explosionDelay = 3000; // Tiempo antes de la explosión
    private boolean exploded = false;
    private Image bombImage0, bombImage1, bombImage2, bombImage3, bombImage4; // Imágenes para la secuencia de la bomba
    private Image explosionImage; // Imagen de la explosión
    private Clip placeBombSound;
    private Clip explosionSound;
    private Clip timerSound; // Sonido del temporizador
    private int explosionRadius = 100; // Rango de la explosión
    private List<Particle> explosionParticles; // Partículas de la explosión

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = (int) (Window.anchoResponsive * 0.04);
        this.creationTime = System.currentTimeMillis();
        this.explosionParticles = new ArrayList<>();

        // Cargar las imágenes de la bomba y la explosión
        try {
            bombImage0 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase1.png"));
            bombImage1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase2.png"));
            bombImage2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase3.png"));
            bombImage3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase4.png"));
            bombImage4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase5.png"));
            explosionImage = ImageIO.read(new File("assets/images/SpritesBomberPostal/Objetos/Bomba/TNT-Fase5.png"));

            // Cargar los sonidos
            placeBombSound = loadSound("assets/sounds/Sonidos/place_bomb.wav");
            explosionSound = loadSound("assets/sounds/Sonidos/explosion.wav");
            timerSound = loadSound("assets/sounds/Sonidos/bomb_timer.wav");

            // Reproducir el sonido de colocar la bomba
            if (placeBombSound != null) {
                placeBombSound.start();
            }

            // Reproducir el sonido del temporizador en bucle
            if (timerSound != null) {
                timerSound.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Clip loadSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            AudioFormat baseFormat = audioInputStream.getFormat();

            // Crear un formato compatible (PCM_SIGNED, 44100 Hz, 16 bits, Stereo)
            AudioFormat compatibleFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                44100,
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2, // 2 bytes por muestra (16 bits)
                44100,
                false // Little-endian
            );

            // Convertir el flujo de audio al formato compatible
            AudioInputStream convertedAudioInputStream = AudioSystem.getAudioInputStream(compatibleFormat, audioInputStream);

            // Abrir el clip y cargar los datos de audio
            Clip clip = AudioSystem.getClip();
            clip.open(convertedAudioInputStream);

            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void update(List<Structure> structures, Player player, List<Innocent> inocentes, List<Police> policias, List<Military> militares) {
        explosionRadius = Window.bloqueTam * 2;

        if (!exploded && (System.currentTimeMillis() - creationTime) >= explosionDelay) {
            exploded = true;

            // Detener el sonido del temporizador
            if (timerSound != null && timerSound.isRunning()) {
                timerSound.stop();
                timerSound.close();
            }

            // Reproducir el sonido de la explosión
            if (explosionSound != null) {
                explosionSound.start();
            }

            // Generar partículas de explosión
            generateExplosionParticles();

            // Verificar si el jugador está en rango
            if (isPlayerInRange(player)) {
                player.decrementHealth();
            }

            // Verificar si las otras entidades están en rango
            affectEntitiesInRange(inocentes, policias, militares);
        }

        destroyStructures(structures);

        // Actualizar partículas de la explosión
        if (exploded) {
            explosionParticles.forEach(Particle::update);
            explosionParticles.removeIf(Particle::isExpired);

        }

        destroyStructures(structures);

        // Actualizar partículas de la explosión
        if (exploded) {
            explosionParticles.forEach(Particle::update);
            explosionParticles.removeIf(Particle::isExpired);
        }
    }
	
	private void affectEntitiesInRange(
	        List<Innocent> innocents, 
	        List<Police> policeOfficers, 
	        List<Military> soldiers
	    ) {
	        int centerX = x + size / 2;
	        int centerY = y + size / 2;
	
	        // Daño a los Innocent
	        for (Innocent innocent : innocents) {
	            if (isInRangeEnemy(centerX, centerY, innocent.getX(), innocent.getY(), innocent.getWidth(), innocent.getHeight())) {
	                innocent.setHealth(0);
	            }
	        }
	
	        // Daño a los Police
	        for (Police police : policeOfficers) {
	            if (isInRangeEnemy(centerX, centerY, police.getX(), police.getY(), police.getWidth(), police.getHeight())) {
	                police.setHealth(0);
	            }
	        }
	
	        // Daño a los Military
	        for (Military soldier : soldiers) {
	            if (isInRangeEnemy(centerX, centerY, soldier.getX(), soldier.getY(), soldier.getWidth(), soldier.getHeight())) {
	                soldier.setHealth(0);
	            }
	        }
	    }


    private void generateExplosionParticles() {
        int centerX = x + size / 2;
        int centerY = y + size / 2;

        // Crear partículas en un patrón circular
        for (int angle = 0; angle < 360; angle += 10) { // Más partículas con intervalo menor
            double radian = Math.toRadians(angle);

            // Calcular la dirección de cada partícula
            double dx = Math.cos(radian);
            double dy = Math.sin(radian);

            // Crear y agregar partículas
            // explosionParticles.add(new Particle(centerX, centerY, dx, dy, explosionRadius));
        }
    }

    public void destroyStructures(List<Structure> structures) {
        if (exploded) {
            for (Structure structure : structures) {
                if (!structure.isDestroyed() && isInRange(structure) && structure.tipo == 1) {
                    structure.setDestroyedTrue();
                    structure.hitByBomb();
                    Window.puntuacion += 10;
                }
            }
        }
    }

    private boolean isInRange(Structure structure) {
        int centerX = x + size / 2;
        int centerY = y + size / 2;
        int structureCenterX = structure.getX() + structure.getWidth() / 2;
        int structureCenterY = structure.getY() + structure.getHeight() / 2;

        double distance = Math.sqrt(Math.pow(centerX - structureCenterX, 2) + Math.pow(centerY - structureCenterY, 2));
        return distance <= explosionRadius;
    }
    
    private boolean isInRangeEnemy(int centerX, int centerY, int entityX, int entityY, int entityWidth, int entityHeight) {
        int entityCenterX = entityX + entityWidth / 2;
        int entityCenterY = entityY + entityHeight / 2;

        double distance = Math.sqrt(Math.pow(centerX - entityCenterX, 2) + Math.pow(centerY - entityCenterY, 2));
        return distance <= explosionRadius;
    }
    
    private boolean isPlayerInRange(Player player) {
        int centerX = x + size / 2;
        int centerY = y + size / 2;
        int playerCenterX = player.getX() + player.getWidth() / 2;
        int playerCenterY = player.getY() + player.getHeight() / 2;
        player.activateDamageEffect();
        double distance = Math.sqrt(Math.pow(centerX - playerCenterX, 2) + Math.pow(centerY - playerCenterY, 2));
        return distance <= explosionRadius;
        
    }


    public void draw(Graphics g) {
        if (exploded) {
            // Dibujar partículas de explosión
            for (Particle particle : explosionParticles) {
                particle.draw(g);
            }

            // Dibujar imagen fija de explosión (opcional)
            g.drawImage(explosionImage, x, y, size, size, null);
        } else {
            // Dibujar secuencia de la bomba
            long elapsedTime = System.currentTimeMillis() - creationTime;
            int phase = (int) ((elapsedTime / (explosionDelay / 5)) % 5);

            switch (phase) {
                case 0 -> g.drawImage(bombImage0, x, y, size, size, null);
                case 1 -> g.drawImage(bombImage1, x, y, size, size, null);
                case 2 -> g.drawImage(bombImage2, x, y, size, size, null);
                case 3 -> g.drawImage(bombImage3, x, y, size, size, null);
                case 4 -> g.drawImage(bombImage4, x, y, size, size, null);
            }
        }
    }

    public boolean hasExploded() {
        return exploded;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean isParticlesFinished() {
        return explosionParticles.isEmpty();
    }


    // Clase interna para las partículas de la explosión
 // Clase interna para las partículas de la explosión
    private static class Particle {
        private int x, y;
        private double dx, dy;
        private int size = 25; // Tamaño del cuadrado de la partícula
        private int life = 20; // Duración en frames
        private Color color;

        public Particle(int x, int y, double dx, double dy, int maxRadius) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;

            // Generar color aleatorio para la partícula
            this.color = new Color(255, (int) (Math.random() * 255), 0); // Rojizo con tonos aleatorios
        }

        public void update() {
            // Mover la partícula
            x += dx * 5;
            y += dy * 5;

            // Reducir su vida útil
            life--;

            // Reducir tamaño para dar la impresión de disiparse
            size = Math.max(2, size - 1);
        }

        public boolean isExpired() {
            return life <= 0;
        }

        public void draw(Graphics g) {
            // Fondo con leve transparencia (color rojo)
            g.setColor(new Color(255, 125, 0, 8)); // Rojo transparente (20 de alpha)
            g.fillRect(0, 0, Window.cuadriculaWidth, Window.alturaResponsive);

            // Dibujar partículas con transparencia variable
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, life * 12))); // Transparencia dependiente de la vida
            g.fillRect(x - size / 2, y - size / 2, size, size); // Cuadrado centrado
        }
        
        

    }

    
    public void stopBombSound() {
        if (timerSound != null && timerSound.isRunning()) {
            timerSound.stop();   // Detener el sonido
            timerSound.close();  // Liberar recursos del clip
        }
    }
}
