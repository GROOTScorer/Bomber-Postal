package main.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.ui.Window;

public class FloatingText {
    private int x, y;
    private String text;
    private Color color;
    private int timer;

    public FloatingText(int x, int y, String text, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.timer = 60; // Duración en frames (ajusta según el juego)
    }

    // Método para dibujar el texto
    public void draw(Graphics g) {
        if (timer > 0) {
            g.setColor(color);
            g.setFont(new Font("Minecraft", Font.BOLD, (int)(Window.anchoResponsive*0.015)));
            g.drawString(text, x, y);
            y -= 1; // Hace que el texto flote hacia arriba
            timer--;
        }
    }

    // Indica si el texto aún debe mostrarse
    public boolean isActive() {
        return timer > 0;
    }
}

