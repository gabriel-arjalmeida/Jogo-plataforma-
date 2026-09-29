package me.antoniodias.solutions.spaceInvaders;

import java.awt.*;

public class Projetil extends ObjetoJogo {

    private Color cor;
    private int atirador = -1;

    public Projetil(double x, double y, double velY, Color cor, int largura, int altura) {
        super(x, y, 0, velY, largura, altura, ObjetoJogo.PROJETIL);
        this.cor = cor;
    }

    // documentar que esse método sobrescreve outro da superclasse
    @Override
    public void mover() {
        y += velY;
    }

    @Override
    public void desenhar(Graphics2D g) {
        var tam = largura / 3;
        g.setColor(cor);
        g.fillRect((int)x, (int)y, largura, altura);
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getAtirador() {
        return atirador;
    }

    public void setAtirador(int atirador) {
        this.atirador = atirador;
    }

    public boolean colide(ObjetoJogo obj) {
        if (this.atirador == obj.tipo) return false;

        return y + altura > obj.y &&
                y < obj.y + obj.altura &&
                x < obj.x + obj.largura &&
                x + largura > obj.x;
    }
}
