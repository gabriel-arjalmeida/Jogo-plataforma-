package me.antoniodias.spaceInvaders;

import me.antoniodias.base.ObjetoJogo;

import java.awt.*;

public abstract class ObjetoSpaceInvaders extends ObjetoJogo {

    public static final int JOGADOR = 0;
    public static final int INIMIGO = 1;
    public static final int PROJETIL = 2;
    public static final int OBSTACULO = 3;

    protected int tipo;

    public ObjetoSpaceInvaders(double x, double y, double velX, double velY, int largura, int altura, int tipo) {
        super(x, y, velX, velY, largura, altura);
        this.tipo = tipo;
    }
}
