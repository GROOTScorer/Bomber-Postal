package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import main.entities.environment.Structure;
import main.ui.Window;

public class Person {
	
	// ----------------------- Declaracion de variables
	
	protected int x, y ,width, height, health, speed;
	List<Structure> structures;

	// ----------------------- Fin de Declaracion de variables
	
	// Constructor
	public Person(int x, int y, int width, int height, int health, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		this.speed = speed;
	}
		
	// Dibuja a la persona
	public void draw(Graphics g) {
		if(isAlive()) {
			sizeUpdate();
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, width, height);
		}
	}
	
	// Metodo para verificar si sigue vivo la persona
	protected boolean isAlive() {
		if(health > 0) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	// Metodo para reducir la vida de la persona
	public void takeDamage() {
		health--;
	}
	
	// Metodo para control cambio de resolucion
	protected void sizeUpdate() {
		width = Window.entitiesSize;
		height = Window.entitiesSize;
	}
	
	// Metodo para verificar colision
	protected boolean checkCollision(int newX, int newY, List<Structure> structures) {
	    for (Structure structure : structures) {
	        if (collisionDetected(structure, newX, newY)) return true;
	    }
	    return false;
	}

    // Metodo para detectar colision
    private boolean collisionDetected(Structure structure, int newX, int newY) {
        return newX < structure.getX() + structure.getWidth() &&
               newX + width > structure.getX() &&
               newY < structure.getY() + structure.getHeight() &&
               newY + height > structure.getY();
    }
	
	// Getters
	
	public int getHealth() {
		return health;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	// Setters
	
	// Setters

	public void setX(int x) {
	    this.x = x;
	}

	public void setY(int y) {
	    this.y = y;
	}

}
