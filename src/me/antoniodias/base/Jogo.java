package me.antoniodias.base;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class Jogo {

    private int largura;
    private int altura;

    public void inicializar(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    protected int getLargura() {
        return largura;
    }

    protected int getAltura() {
        return altura;
    }

    public abstract void atualizar();

    public abstract void desenhar(Graphics2D g);

    public void teclaPressionada(KeyEvent e) {}

    public void teclaLiberada(KeyEvent e) {}

    public void mousePressionado(MouseEvent e) {}

    public void mouseLiberado(MouseEvent e) {}

    public void mouseMovido(MouseEvent e) {}

    public void rodaMouseGirada(MouseWheelEvent e) {}

    public void mouseArrastado(MouseEvent e) {}
}