package me.antoniodias.plataforma;

import me.antoniodias.base.ObjetoJogo;

public class Camera {
    private double x;
    private double y;
    private int altura;
    private int largura;
    private ObjetoJogo alvo;
    private double suavizacao;

    public Camera(double x, double y, int altura, int largura, ObjetoJogo alvo, double suavizacao) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.largura = largura;
        this.alvo = alvo;
        this.suavizacao = suavizacao;
    }

    public void atualizar() {
        double alvoX = alvo.getX() - largura / 2;
        double alvoY = alvo.getY() - altura / 2;
        // A diferença é "suavemente" incrementada a x, de acordo
        // com a taxa de suavização.
        this.x += (alvoX - this.x) * this.suavizacao;
        this.y += (alvoY - this.y) * this.suavizacao;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
