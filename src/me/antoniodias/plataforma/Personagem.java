package me.antoniodias.plataforma;

import me.antoniodias.base.ObjetoJogo;
import me.antoniodias.plataforma.spritesheet.Animacao;

import java.awt.*;

/*
    1. Personagem não tinha movimento autônomo, que seria implementado por mover(),
       apenas a ação da gravidade. Sendo assim, essa ação foi movida para atualizar()
       e mover() foi removido.
 */
public class Personagem extends ObjetoJogo {

    public static final int NAO_COLIDE = 0;
    public static final int CIMA = 1;
    public static final int BAIXO = 2;
    public static final int ESQUERDA = 3;
    public static final int DIREITA = 4;

    private int vida = 2;
    private int pontos = 0;
    private boolean pulando = true;
    private Animacao[] animacoes;
    private Animacao animacao;

    public Personagem(double x, double y, double velX, double velY, int largura, int altura, Animacao[] animacoes) {
        super(x, y, velX, velY, largura, altura);

        this.animacoes = animacoes;
        this.animacao = animacoes[1];
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    // 2. Reorganizados os métodos para mover-se
//    public void olharDireita() {
//        animacao.setDirecao(Animacao.DIREITA);
//    }
//
//    public void olharEsquerda() {
//        animacao.setDirecao(Animacao.ESQUERDA);
//    }

    public void moverEsquerda() {
        animacao.setDirecao(Animacao.ESQUERDA);
        if (pulando){
            animacao = animacoes[2];
        } else {
            animacao = animacoes[0];
        }
    }

    public void moverDireita() {
        animacao.setDirecao(Animacao.DIREITA);
        if (pulando){
            animacao = animacoes[2];
        } else {
            animacao = animacoes[0];
        }
    }

    // 3. Ajuste das animações
    public void parar() {
        if (pulando){
            animacao = animacoes[2];
        } else {
            animacao = animacoes[1];
        }
    }

    public void pular() {
        if (!pulando) {
            setVelY(-20);
            pulando = true;
        }
    }

    public void pisar(Plataforma p) {
        pulando = false;
        setVelY(0);
        setY(p.getY() - getAltura());
    }

    @Override
    public void desenhar(Graphics2D g) {
        animacao.desenhar(g, (int)getX(), (int)getY(), getLargura(), getAltura());
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


    @Override
    public void atualizar() {
        animacao.atualizar();

        final double G = 0.9;
        setVelY(getVelY() + G);
        setY(getY() + getVelY());
    }
}
