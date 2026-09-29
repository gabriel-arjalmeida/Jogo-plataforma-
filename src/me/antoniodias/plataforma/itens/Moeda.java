package me.antoniodias.plataforma.itens;

import me.antoniodias.base.ObjetoJogo;
import me.antoniodias.plataforma.Personagem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Moeda extends ObjetoJogo implements Coletavel {

    private BufferedImage imagem;
    private double yInicial;

    public Moeda(double x, double y, double velY, int largura, int altura, BufferedImage imagem) {
        super(x, y, 5, velY, largura, altura);
        this.imagem = imagem;
        yInicial = y;
    }

    @Override
    public void coletar(Personagem personagem) {
        personagem.setPontos(personagem.getPontos() + 1);
    }

    @Override
    public void atualizar() {
        setY(getY() + getVelY());

        if (getY() < yInicial || getY() > yInicial + getAltura() / 2) {
            setVelY(-getVelY());
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(imagem, (int)getX(), (int)getY(), getLargura(), getAltura(), null);
    }
}
