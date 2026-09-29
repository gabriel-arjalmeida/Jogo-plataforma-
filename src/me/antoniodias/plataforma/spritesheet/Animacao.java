package me.antoniodias.plataforma.spritesheet;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Animacao {

    public static final int DIREITA = 0;
    public static final int ESQUERDA = 1;

    private MapeamentoSpritesheet mapeamento;
    private BufferedImage imagem;
    private int frameAtual;
    private long ultimaAtualizacao;
    private int direcao;
    private int intervalo;

    public Animacao(int intervalo, MapeamentoSpritesheet mapeamento, BufferedImage imagem) {
        this.mapeamento = mapeamento;
        this.imagem = imagem;
        this.intervalo = intervalo;
        this.frameAtual = 0;
        this.ultimaAtualizacao = 0;
        this.direcao = DIREITA;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public void atualizar() {
        long atual = System.currentTimeMillis();

        if (atual - ultimaAtualizacao > intervalo) {
            this.frameAtual = (this.frameAtual + 1) % mapeamento.frames().size();
            ultimaAtualizacao = atual;
        }
    }

    public void desenhar(Graphics2D g, int x, int y, int largura, int altura) {
        int x2 = mapeamento.frames().get(this.frameAtual).x();
        int y2 = mapeamento.frames().get(this.frameAtual).y();
        int largura2 = mapeamento.frameWidth();
        int altura2 = mapeamento.frameHeight();

        if (direcao == ESQUERDA) {
            AffineTransform original = g.getTransform();

            g.translate(x + largura, y);
            g.scale(-1, 1);
            g.drawImage(this.imagem,
                    0, 0, largura, altura,
                    x2, y2, x2 + largura2, y2 + altura2,
                    null);

            g.setTransform(original);
        } else {
            g.drawImage(this.imagem,
                    x, y, x + largura, y + altura,
                    x2, y2, x2 + largura2, y2 + altura2,
                    null);
        }
    }
}
