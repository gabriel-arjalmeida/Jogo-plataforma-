package me.antoniodias.base;

import java.awt.*;
/*
    1. Todo objeto no jogo precisa se deslocar conforme o personagem se desloca.
       Sendo assim, esse comportamento ficará no método deslocar(deslocamento).
 */
public abstract class ObjetoJogo implements Hitbox, Atualizavel {

    private double x, y;
    private double velX, velY;
    private int largura, altura;

    public ObjetoJogo() {
    }

    public ObjetoJogo(double x, double y, double velX, double velY, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.largura = largura;
        this.altura = altura;
    }

    public void deslocar(int deslocamento) {
        setX(getX() + deslocamento);
    }

    public abstract void desenhar(Graphics2D g);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
