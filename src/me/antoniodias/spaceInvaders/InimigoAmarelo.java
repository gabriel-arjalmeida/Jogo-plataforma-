package me.antoniodias.spaceInvaders;

import java.awt.*;
import java.util.Random;

public class InimigoAmarelo extends Inimigo {

    private Random random = new Random();

    public InimigoAmarelo(double x, double y, double velX, int largura, int altura) {
        super(x, y, velX, Color.YELLOW, largura, altura);
        setPontos(40);
    }

//    @Override
//    public void mover() {
//        x += velX;
//
//        if (x < xInicial || x > xInicial + largura) {
//            velX = -velX;
//        }
//
//        if (random.nextDouble() < 0.5) {
//            velX = -0.7;
//        } else {
//            velX = 0.7;
//        }
//    }

    @Override
    public void desenhar(Graphics2D g) {
        var tam = getLargura() / 7;
        g.setColor(this.getCor());
        g.fillRect((int)(getX() + 2 * tam), (int)getY(), 3 * tam, tam);
        g.fillRect((int)(getX() + tam), (int)(getY() + tam), 5 * tam, tam);
        g.fillRect((int)getX(), (int)(getY() + 2 * tam), 7 * tam, tam);
        g.fillRect((int)(getX() + tam), (int)(getY() + 3 * tam), 5 * tam, tam);
        g.fillRect((int)getX(), (int)(getY() + 4 * tam), tam, tam);
        g.fillRect((int)(getX() + 6 * tam), (int)(getY() + 4 * tam), tam, tam);
        g.setColor(Color.BLACK);
        g.fillRect((int)(getX() + 2 * tam), (int)(getY() + 2 * tam), tam, tam);
        g.fillRect((int)(getX() + 4 * tam), (int)(getY() + 2 * tam), tam, tam);
    }
}
