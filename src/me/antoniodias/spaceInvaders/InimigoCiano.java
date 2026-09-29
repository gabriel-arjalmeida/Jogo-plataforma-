package me.antoniodias.spaceInvaders;

import java.awt.*;

public class InimigoCiano extends Inimigo {

    private double amplitude;
    private double angulo;
    public InimigoCiano(double x, double y, double velX, int largura, int altura) {
        super(x, y, velX, Color.CYAN, largura, altura);
        setPontos(30);

        // era assim...
//        amplitude = (xInicial + largura) / 2;
        amplitude = largura;
        angulo = 0;
    }

    @Override
    public void mover() {
        setX(xInicial + amplitude * Math.sin(Math.toRadians(angulo)));
        angulo++;
    }

    @Override
    public void desenhar(Graphics2D g) {
        var tam = getLargura() / 5;
        g.setColor(this.getCor());
        g.fillRect((int)getX(), (int)getY(), tam, tam);
        g.fillRect((int)(getX() + 2 * tam), (int)getY(), tam, tam);
        g.fillRect((int)(getX() + 4 * tam), (int)getY(), tam, tam);
        g.fillRect((int)getX(), (int)(getY() + tam), 5 * tam, tam);
        g.fillRect((int)(getX() + tam), (int)(getY() + 2 * tam), 3 * tam, tam);
        g.fillRect((int)(getX() + 2 * tam), (int)(getY() + 3 * tam), tam, tam);
    }
}
