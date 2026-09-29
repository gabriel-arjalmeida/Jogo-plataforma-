package me.antoniodias.spaceInvaders;

import java.awt.*;

public class InimigoBranco extends Inimigo {
    public InimigoBranco(double x, double y, double velX, int largura, int altura) {
        super(x, y, velX, Color.WHITE, largura, altura);
        setPontos(10);
    }

    @Override
    public void desenhar(Graphics2D g) {
        var tam = getLargura() / 3;
        g.setColor(getCor());
        g.fillRect((int)getX(), (int)getY(), getLargura(), tam);
        g.fillRect((int)(getX() + tam), (int)(getY() + tam), tam, tam);
    }
}
