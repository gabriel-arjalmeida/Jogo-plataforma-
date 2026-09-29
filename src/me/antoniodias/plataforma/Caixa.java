package me.antoniodias.plataforma;

import me.antoniodias.base.ObjetoJogo;

import java.awt.*;

public class Caixa extends ObjetoJogo {

    public Caixa(double x, double y, int largura, int altura) {
        super(x, y, 0, 0, largura, altura);

    }

    @Override
    public void desenhar(Graphics2D g) {
        g.fillRect((int)getX(), (int)getY(), getLargura(), getAltura());
    }

    @Override
    public void atualizar() {

    }
}
