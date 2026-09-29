package me.antoniodias.solutions.spaceInvaders;

import java.awt.*;

public class ObjetoJogo {

    public static final int JOGADOR = 0;
    public static final int INIMIGO = 1;
    public static final int PROJETIL = 2;
    public static final int OBSTACULO = 3;

    protected double x, y;
    protected double velX, velY;
    protected int largura, altura;
    protected int tipo;

    public ObjetoJogo(double x, double y, double velX, double velY, int largura, int altura, int tipo) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.largura = largura;
        this.altura = altura;
        this.tipo = tipo;
    }

    public void mover() {

    }

    public void desenhar(Graphics2D g) {

    }
}
