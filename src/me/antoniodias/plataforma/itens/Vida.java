package me.antoniodias.plataforma.itens;

import me.antoniodias.base.Atualizavel;
import me.antoniodias.base.ObjetoJogo;
import me.antoniodias.plataforma.Personagem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Vida extends ObjetoJogo implements Coletavel, Atualizavel {

    private BufferedImage imagem;
    private double yInicial;
    private double angulo;
    private double incremento;

    public Vida(double x, double y, double velY, int largura, int altura, BufferedImage imagem) {
        super(x, y, 5, velY, largura, altura);

        this.imagem = imagem;
        yInicial = y;
        angulo = 0;
        incremento = 1.25;
    }

    @Override
    public void coletar(Personagem personagem) {
        personagem.setPontos(personagem.getPontos() + 1);
    }

    @Override
    public void atualizar() {
        angulo += incremento;

        if (angulo < -15 || angulo > 15) {
            incremento = -incremento;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        AffineTransform original = g.getTransform();

        double centroX = getX() + getLargura() / 2.0;
        double centroY = getY() + getAltura() / 2.0;

        g.rotate(
                Math.toRadians(angulo),
                centroX,
                centroY
        );

        g.drawImage(imagem, (int)getX(), (int)getY(), getLargura(), getAltura(), null);

        g.setTransform(original);
    }
}
