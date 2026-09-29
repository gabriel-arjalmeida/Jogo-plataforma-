package me.antoniodias.solutions.spaceInvaders;

import java.awt.*;

public class InimigoDois extends Inimigo {

    private double xInicial;
    private double chanceDisparo;

    public InimigoDois(double x, double y, double velX, int largura, int altura, double chanceDisparo) {
        super(x, y, velX, Color.RED, largura, altura, ObjetoJogo.INIMIGO);
        this.xInicial = x;
        this.chanceDisparo = chanceDisparo;
        this.pontuacao = 30;
    }

    public void mover() {
        x += velX;

        if (x < xInicial || x > xInicial + largura) {
            velX = -velX;
        }
    }

    public void desenhar(Graphics2D g) {
        var tam = largura / 7;
        g.setColor(this.getCor());
        g.fillRect((int)(x + tam), (int)y, 5 * tam, tam);
        g.fillRect((int)x, (int)(y + tam), 7 * tam, tam);
        g.fillRect((int)(x + 2 * tam), (int)(y + 2 * tam), 3 * tam, tam);
        g.setColor(Color.BLACK);
        g.fillRect((int)(x + 2 * tam), (int)(y + tam), tam, tam);
        g.fillRect((int)(x + 4 * tam), (int)(y + tam), tam, tam);
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
}
