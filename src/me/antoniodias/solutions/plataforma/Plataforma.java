package me.antoniodias.solutions.plataforma;

import me.antoniodias.base.ObjetoJogo;

import java.awt.*;

public class Plataforma extends ObjetoJogo {

    public Plataforma(double x, double y, int largura, int altura) {
        super(x, y, 0, 0, largura, altura);
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)getX(), (int)getY(), getLargura(), getAltura());
    }

    @Override
    public void atualizar() {

    }
}
