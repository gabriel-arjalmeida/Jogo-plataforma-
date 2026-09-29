package me.antoniodias.spaceInvaders;

import java.awt.*;

public class Nave extends ObjetoSpaceInvaders {

    private Color cor;

    public Nave(int x, int y, Color cor, int largura, int altura) {
        super(x, y, 0, 0, largura, altura, ObjetoSpaceInvaders.JOGADOR);
        this.cor = cor;
    }

    public Projetil disparar() {
        var tam = getLargura() / 6;

        var projetil = new Projetil(
                getX() + getLargura() / 2 - tam / 2,
                getY() - tam,
                -3,
                Color.ORANGE,
                tam,
                tam);

        // Define que o autor do disparo é o jogador
        projetil.setAtirador(Projetil.JOGADOR);

        return projetil;
    }


    public void desenhar(Graphics2D g) {
        var tam = getLargura() / 3;
        g.setColor(cor);
        g.fillRect((int)(getX() + tam), (int)getY(), tam, tam);
        g.fillRect((int)getX(), (int)(getY() + tam), getLargura(), tam);
        g.fillRect((int)getX(), (int)(getY() + 2 * tam), tam, tam);
        g.fillRect((int)(getX() + 2 * tam), (int)(getY() + 2 * tam), tam, tam);
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    @Override
    public void atualizar() {

    }
}
