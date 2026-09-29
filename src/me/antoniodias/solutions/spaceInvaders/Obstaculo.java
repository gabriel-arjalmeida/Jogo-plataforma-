package me.antoniodias.solutions.spaceInvaders;

import java.awt.*;

public class Obstaculo extends ObjetoJogo {

    private Color cor;

    public Obstaculo(double x, double y, Color cor, int largura, int altura) {
        super(x, y, 0, 0, largura, altura, ObjetoJogo.OBSTACULO);
        this.cor = cor;
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(cor);
        g.fillRect((int)x, (int)y, largura, altura);
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

}
