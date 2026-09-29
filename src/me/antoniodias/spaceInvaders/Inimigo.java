package me.antoniodias.spaceInvaders;

import java.awt.*;

public abstract class Inimigo extends ObjetoSpaceInvaders {

    protected double xInicial;
    private Color cor;
    private int pontos;
    private int estado = 0;
    private boolean vivo = true;

    public Inimigo(double x, double y, double velX, Color cor, int largura, int altura) {
        super(x, y, velX, 0, largura, altura, ObjetoSpaceInvaders.INIMIGO);
        this.cor = cor;
        this.xInicial = x;
    }

    public void mover() {
        setX(getX() + getVelX());
        
        if (getX() < xInicial || getX() > xInicial + getLargura()) {
            setVelX(-getVelX());
        }
    }

    public void atualizar() {

    }

    public abstract void desenhar(Graphics2D g);

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void incEstado() {
        estado = (estado + 1) % 4;
    }

    public int getEstado() {
        return estado;
    }

    public void desenharDestruido(Graphics2D g) {
        switch (estado) {
            case 0 -> desenharEstado1(g);
            case 1 -> desenharEstado2(g);
            case 2 -> desenharEstado3(g);
        }
    }

    private void desenharEstado1(Graphics2D g) {
        var tam = getLargura() / 6;
        int x = (int)this.getX() + (getLargura() / 2 - tam / 2);
        var y = (int)this.getY() + (getAltura() / 2 - tam / 2);

        g.setColor(getCor());
        g.fillRect(x, y, tam, tam);
    }

    private void desenharEstado2(Graphics2D g) {
        var tam = getLargura() / 6;
        int x = (int)this.getX() + (getLargura() / 2 - tam / 2);
        var y = (int)this.getY() + (getAltura() / 2 - tam / 2);

        g.setColor(getCor());
        g.fillRect(x - tam, y - tam, tam, tam);
        g.fillRect(x + tam, y - tam, tam, tam);
        g.fillRect(x - tam, y + tam, tam, tam);
        g.fillRect(x + tam, y + tam, tam, tam);
    }

    private void desenharEstado3(Graphics2D g) {
        var tam = getLargura() / 6;
        int x = (int)this.getX() + (getLargura() / 2 - tam / 2);
        var y = (int)this.getY() + (getAltura() / 2 - tam / 2);

        g.setColor(getCor());
        g.fillRect(x - 2 * tam, y - 2 * tam, tam, tam);
        g.fillRect(x + 2 * tam, y - 2 * tam, tam, tam);
        g.fillRect(x - 2 * tam, y + 2 * tam, tam, tam);
        g.fillRect(x + 2 * tam, y + 2 * tam, tam, tam);
    }
    public Projetil disparar() {
        var tam = getLargura() / 6;

        var projetil = new Projetil(
                getX() + getLargura() / 2 - tam / 2,
                getY() + getAltura(),
                3,
                Color.ORANGE,
                tam,
                tam);

        // Define que o autor do disparo é o inimigo
        projetil.setAtirador(Projetil.INIMIGO);

        return projetil;
    }

    public double getxInicial() {
        return xInicial;
    }

    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }
}
