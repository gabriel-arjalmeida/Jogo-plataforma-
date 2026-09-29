package me.antoniodias.plataforma;

import me.antoniodias.base.ObjetoJogo;
import me.antoniodias.plataforma.spritesheet.Animacao;

import java.awt.*;

public class Monstro extends ObjetoJogo {

    private String tipo;
    private Animacao animacao;
    private double xInicial, yInicial;
    private int amplitude = 60;
    private boolean olhandoDireita = false;



    public Monstro() {
        super(0, 0, 0, 0, 0, 0);
    }

    public Monstro(double x, double y, double velX, double velY, int largura, int altura, Animacao animacao) {
        super(x, y, velX, velY, largura, altura);

        xInicial = x;
        yInicial = y;
        this.animacao = animacao;
    }

    @Override
    public void desenhar(Graphics2D g) {
        animacao.setDirecao(olhandoDireita ? Animacao.DIREITA : Animacao.ESQUERDA);
        animacao.desenhar(g, (int)getX(), (int)getY(), getLargura(), getAltura());
    }

    @Override
    public void deslocar(int deslocamento) {
        super.deslocar(deslocamento);
        xInicial += deslocamento;
    }

    @Override
    public void atualizar() {
        animacao.atualizar();
        setX(getX() + getVelX());
        setY(getY() + getVelY());

        if (getX() < xInicial || getX() > xInicial + amplitude) {
            setVelX(-getVelX());
            olhandoDireita = !olhandoDireita;
        }

        if (getY() < yInicial || getY() > yInicial + amplitude) {
            setVelY(-getVelY());
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAnimacao(Animacao animacao) {
        this.animacao = animacao;
    }

    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    public void setyInicial(double yInicial) {
        this.yInicial = yInicial;
    }
}
