package me.antoniodias.solutions.plataforma;

import me.antoniodias.base.ObjetoJogo;

import java.awt.*;

public class Personagem extends ObjetoJogo {

    public static final int NAO_COLIDE = 0;
    public static final int CIMA = 1;
    public static final int BAIXO = 2;
    public static final int ESQUERDA = 3;
    public static final int DIREITA = 4;

    private int vida;
    private int pontos;
    private Expiravel efeito;

    public Personagem(double x, double y, double velX, double velY, int largura, int altura) {
        super(x, y, velX, velY, largura, altura);
        vida = 2;
        pontos = 0;
    }

    public void setEfeito(Expiravel efeito) {
        this.efeito = efeito;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void atualizar() {
        if (efeito != null && efeito.expirou()) {
            efeito.expirar(this);
            efeito = null;
        }

        final double G = 0.9;
        setVelY(getVelY() + G);
        setY(getY() + getVelY());
    }

    public void pular() {
        setVelY(-20);
    }

    public void pisar(Plataforma p) {
        setVelY(0);
        setY(p.getY() - getAltura());
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)getX(), (int)getY(), getLargura(), getAltura());
    }

    public int colide(ObjetoJogo obj) {
        boolean colide = this.getY() + getAltura() > obj.getY() &&
                this.getY() < obj.getY() + obj.getAltura() &&
                this.getX() < obj.getX() + obj.getLargura() &&
                this.getX() + getLargura() > obj.getX();

        if (colide) {
            var overlapLeft = this.getX() + getLargura() - obj.getX();
            var overlapRight = obj.getX() + obj.getLargura() - this.getX();
            var overlapTop = this.getY() + getAltura() - obj.getY();
            var overlapBottom = obj.getY() + obj.getAltura() - this.getY();

            var minOverlap = Math.min(
                    Math.min(
                            overlapBottom,
                            overlapLeft),
                    Math.min(
                            overlapRight,
                            overlapTop
                    ));

            if (minOverlap == overlapTop && this.getVelY() > 0) {
                // colide por cima
                return CIMA;
            } else if (minOverlap == overlapBottom && this.getVelY() < 0) {
                // colide por baixo
                return BAIXO;
            } else if (minOverlap == overlapLeft && this.getVelX() > 0) {
                // colide pela esquerda
                return ESQUERDA;
            } else if (minOverlap == overlapRight && this.getVelX() < 0) {
                // colide pela direita
                return DIREITA;
            }
        }

        return NAO_COLIDE;
    }
}
