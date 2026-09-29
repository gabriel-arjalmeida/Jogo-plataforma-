package me.antoniodias.spaceInvaders;

import java.awt.*;

public class InimigoVermelho extends Inimigo {
    public InimigoVermelho(double x, double y, double velX, int largura, int altura) {
        super(x, y, velX, Color.RED, largura, altura);
        setPontos(20);
    }

    @Override
    public void desenhar(Graphics2D g) {
        var tam = getLargura() / 7;
        g.setColor(this.getCor());
        g.fillRect((int)(getX() + tam), (int)getY(), 5 * tam, tam);
        g.fillRect((int)getX(), (int)(getY() + tam), 7 * tam, tam);
        g.fillRect((int)(getX() + 2 * tam), (int)(getY() + 2 * tam), 3 * tam, tam);
        g.setColor(Color.BLACK);
        g.fillRect((int)(getX() + 2 * tam), (int)(getY() + tam), tam, tam);
        g.fillRect((int)(getX() + 4 * tam), (int)(getY() + tam), tam, tam);
    }
}
