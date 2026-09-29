package me.antoniodias.breakout;

import java.awt.*;

public class Tijolo {
    private double x, y;
    private double largura, altura;
    private Color cor;
    private int resistencia;
    private PowerUp powerUp;
    private boolean destruido = false;

    public Tijolo(double x, double y, double largura, double altura, int resistencia, Color cor) throws ValorInvalidoException {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
        setResistencia(resistencia);
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public void desenhar(Graphics2D g) {
        g.setColor(cor);
        g.fillRect((int) x, (int) y, (int) largura, (int) altura);
    }

    // métodos acessores
    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) throws ValorInvalidoException {
        if (resistencia > 0) {
            this.resistencia = resistencia;
        } else {
            throw new ValorInvalidoException("Valor inválido para resistencia: " + resistencia);

            // Para tratar uma exceção:
            // - Declarar na lista de exceções do método
            // - Cercar com try/catch
        }
    }

    public void diminuirResistencia() {
        if (resistencia > 0) {
            resistencia--;
        }
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

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
