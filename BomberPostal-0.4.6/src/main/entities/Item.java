package main.entities;

import java.awt.Color;
import java.awt.Graphics;

import main.entities.characters.Player;

public class Item {

    // Declaraci�n de variables
    private int x, y, width, height, tipo;
    private Player player;
    private boolean collected = false; // Bandera para saber si el item fue recogido

    // Constructor
    public Item(int x, int y, int width, int height, Player player, int tipo) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
        this.tipo = tipo;
    }

    public void update() {
        if (!collected && player.getX() < x + width && player.getX() + player.getWidth() > x &&
            player.getY() < y + height && player.getY() + player.getHeight() > y) {
            collected = true;
             
            // Dependiendo del tipo el item va hacer una cosa u otra, por ejemplo si es de tipo de item de avance como un cheque o lo que sea se va agarrar y no te hara ningun efecto pero podras avanzar de nivel .
            // Por otro lado si es de tipo acumulacion como por ejemplos balas, en este caso aumentaria la cantidad total de balas que tenes en un rango de 1 a 5.
            
            if(tipo == 1) {
            	// item de progreso de frames           	
            }
            
            else if(tipo == 2) {
            	// item de acumulacion
            }
            
        }
    }

    public void draw(Graphics g) {
        if (!collected) { 
            g.setColor(Color.pink);
            g.fillRect(x, y, width, height);
        }
    }
}
