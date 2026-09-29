package me.antoniodias.breakout;

import java.awt.*;

public class PowerUp {
    private double x, y;
    private String id;
    private int cor;
    private double velX, velY;
    private boolean revelado = false;
    private boolean usado = false;

    public PowerUp(double x, double y, String id, int cor, double velY) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.cor = cor;
        this.velX = velX;
        this.velY = velY;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
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

    public boolean isRevelado() {
        return revelado;
    }

    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public void mover() {
        if (isRevelado() && !isUsado()) {
            setX(getX() + getVelX());
            setY(getY() + getVelY());
        }
    }

    public void desenhar(Graphics2D g) {
        if (isRevelado() && !isUsado()) {
            g.setColor(new Color(getCor()));
            g.fillRect((int) getX(), (int) getY(), 20, 20);
            g.setColor(Color.BLACK);
            desenharStringCentralizada(g, getId(), (int) getX(), (int) getY(), 20, 20);
        }
    }

    private void desenharStringCentralizada(Graphics2D g, String text, int x, int y, int largura, int altura) {
        FontMetrics fm = g.getFontMetrics();

        int textoX = x + (largura - fm.stringWidth(text)) / 2;
        int textoY = y
                + (altura - fm.getHeight()) / 2
                + fm.getAscent();

        g.drawString(text, textoX, textoY);
    }
}
