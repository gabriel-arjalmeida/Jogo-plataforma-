package me.antoniodias.solutions.spaceInvaders;

import java.awt.*;

public class Nave extends ObjetoJogo {

    private Color cor;

    public Nave(int x, int y, Color cor, int largura, int altura) {
        super(x, y, 0, 0, largura, altura, ObjetoJogo.JOGADOR);
        this.cor = cor;
    }

    public Projetil disparar() {
        var tam = largura / 6;

        var projetil = new Projetil(
                x + largura / 2 - tam / 2,
                y - tam,
                -3,
                Color.ORANGE,
                tam,
                tam);

        // Define que o autor do disparo é o jogador
        projetil.setAtirador(Projetil.JOGADOR);

        return projetil;
    }

    public void desenhar(Graphics2D g) {
        var tam = largura / 3;
        g.setColor(cor);
        g.fillRect((int)(x + tam), (int)y, tam, tam);
        g.fillRect((int)x, (int)(y + tam), largura, tam);
        g.fillRect((int)x, (int)(y + 2 * tam), tam, tam);
        g.fillRect((int)(x + 2 * tam), (int)(y + 2 * tam), tam, tam);
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
