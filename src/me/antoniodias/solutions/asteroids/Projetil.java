package me.antoniodias.solutions.asteroids;

import java.awt.*;

public class Projetil {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double tamanho;

    public Projetil(double x, double y, double vx, double vy, double tamanho) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.tamanho = tamanho;
    }

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

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public void atualizar() {
        x += vx;
        y += vy;
    }

    public void desenhar(Graphics2D g) {
        g.drawOval((int)(x - tamanho / 2), (int)(y - tamanho / 2),
                (int)tamanho, (int)tamanho);
    }
}
