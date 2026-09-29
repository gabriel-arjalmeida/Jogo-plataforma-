package me.antoniodias.solutions.spaceInvaders;

import java.awt.*;

public class Inimigo extends ObjetoJogo {

    private double xInicial;
    private Color cor;
    private double chanceDisparo;
    protected int pontuacao;

    public Inimigo(double x, double y, double velX, Color cor, int largura, int altura, double chanceDisparo) {
        super(x, y, velX, 0, largura, altura, ObjetoJogo.INIMIGO);
        this.xInicial = x;
        this.cor = cor;
        this.chanceDisparo = chanceDisparo;
        this.pontuacao = 5;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void mover() {
        x += velX;

        if (x < xInicial || x > xInicial + largura) {
            velX = -velX;
        }
    }

    public void desenhar(Graphics2D g) {
        var tam = largura / 3;
        g.setColor(cor);
        g.fillRect((int)x, (int)y, largura, tam);
        g.fillRect((int)(x + tam), (int)(y + tam), tam, tam);
    }

    public double getChanceDisparo() {
        return chanceDisparo;
    }

    public Projetil disparar() {
        var tam = largura / 6;

        var projetil = new Projetil(
                x + largura / 2 - tam / 2,
                y + altura,
                3,
                Color.ORANGE,
                tam,
                tam);

        // Define que o autor do disparo é o inimigo
        projetil.setAtirador(Projetil.INIMIGO);

        return projetil;
    }

    public double getxInicial() {
        return xInicial;
    }

    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
