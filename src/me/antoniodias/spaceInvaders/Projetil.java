package me.antoniodias.spaceInvaders;

import java.awt.*;

public class Projetil extends ObjetoSpaceInvaders {

    private Color cor;
    private int atirador = -1;

    public Projetil(double x, double y, double velY, Color cor, int largura, int altura) {
        super(x, y, 0, velY, largura, altura, ObjetoSpaceInvaders.PROJETIL);
        this.cor = cor;
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(cor);
        g.fillRect((int)getX(), (int)getY(), getLargura(), getAltura());
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getAtirador() {
        return atirador;
    }

    public void setAtirador(int atirador) {
        this.atirador = atirador;
    }

    public boolean colide(ObjetoSpaceInvaders obj) {
        if (this.atirador == obj.tipo) return false;

        return getY() + getAltura() > obj.getY() &&
                getY() < obj.getY() + obj.getAltura() &&
                getX() < obj.getX() + obj.getLargura() &&
                getX() + getLargura() > obj.getX();
    }

    @Override
    public void atualizar() {
        setY(getY() + getVelY());
    }
}
