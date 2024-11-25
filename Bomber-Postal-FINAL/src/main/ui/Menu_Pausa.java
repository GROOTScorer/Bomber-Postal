package main.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

import main.entities.characters.Player;
import main.entities.projectiles.Bomb;

public class Menu_Pausa {
    private Window window;
    private boolean visible;
    private Font regularFont = new Font("smash", Font.PLAIN, 40);
    private String salir = "Salir al menu";
    private String reanudar = "Reanudar";
    private String[] opciones = { reanudar, salir };
    private int currentSelection = 0;

    public Menu_Pausa(Window window, Player player) {
        this.visible = false;
        this.window = window;
    }

    public void showMenu() {
        visible = true;
    }

    public void hideMenu() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(Graphics g, int width, int height) {
        if (!visible) return;

        // Obtén el valor de 'cuadriculaWidth' desde 'Window'
        int cuadriculaWidth = window.getCuadriculaWidth();

        // Fondo semi-transparente utilizando 'cuadriculaWidth'
        g.setColor(new Color(0, 0, 0, 150)); // Color negro con opacidad
        g.fillRect((width - cuadriculaWidth) / 2, 0, cuadriculaWidth, height);

        // Dibujar opciones centradas dentro de 'cuadriculaWidth'
        // Fondo semi-transparente
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(0, 0, width, height);

        // Ajustar tamaño de la fuente según la resolución
        int baseFontSize = Math.min(width, height) / 20;
        Font responsiveLevelFont = new Font("smash", Font.PLAIN, baseFontSize);

        g.setFont(responsiveLevelFont);
        FontMetrics fm = g.getFontMetrics(responsiveLevelFont);
        int yOffset = (height / 2) - baseFontSize * 3;

        // Dibujar opciones centradas
        for (int i = 0; i < opciones.length; i++) {
            int textWidth = fm.stringWidth(opciones[i]);
            int xPosition = (width - textWidth) / 2;

            if (i == currentSelection) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(opciones[i], xPosition, yOffset + (i * baseFontSize * 2));
        }
    }

    public void keyPressed(KeyEvent e, List<Bomb> bombs) {
        if (!visible) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentSelection = (currentSelection - 1 + opciones.length) % opciones.length;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentSelection = (currentSelection + 1) % opciones.length;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
                hideMenu();
            } else if (currentSelection == 1) {
		        for (Bomb bomb : bombs) { // Iterar sobre las bombas del jugador
		            bomb.stopBombSound();       // Llamar al método para detener el sonido del temporizador
		        }
		        
		        window.policias.clear();
		        window.militares.clear();
		        window.inocentes.clear();
            	window.enemigosRestantes = 0;
            	window.objetosRestantes = 0;
            	window.frameSeleccionado = 1;
            	window.nivelSeleccionado = 1;
                window.stopAudio(); // Detiene todos los sonidos y la música antes de cerrar
                window.stop();      // Detiene el hilo del juego
                hideMenu();
                if (window.isDisplayable()) {
                    window.dispose(); // Cierra la ventana
                }
                new Menu_Inicio(Window.WIDTH, Window.HEIGHT).start(); // Inicia el nuevo menú
            } 
        }
    }



}
