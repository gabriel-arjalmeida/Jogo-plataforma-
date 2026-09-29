package me.antoniodias.solutions.spaceInvaders;

import me.antoniodias.base.Jogo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceInvaders extends Jogo {
    // Jogador
    Nave jogador;
    // Inimigo
    List<Inimigo> inimigos = new ArrayList<>();
    List<Projetil> projeteis = new ArrayList<>();
    List<Obstaculo> obstaculos = new ArrayList<>();

    long frameCount;
    Random random = new Random();
    int pontos = 0;

    @Override
    public void inicializar(int largura, int altura) {
        super.inicializar(largura, altura);

        var larguraNave = 60;
        jogador = new Nave(
                (largura - larguraNave) / 2,
                520,
                Color.GREEN,
                30,
                30);

        for (int j = 0; j < 4; j++) {
            var yInimigo = j * 40 + 70;
            for (int i = 0; i < 5; i++) {
                var xInimigo = (i + 1) * 130 - 50;
                switch (j) {
                    case 0 -> inimigos.add(new InimigoDois(xInimigo, yInimigo, 0.7, 30, 10, random.nextDouble(0.5)));
                    case 1 -> inimigos.add(new InimigoTres(xInimigo, yInimigo, 0.7, 30, 10, random.nextDouble(0.5)));
                    case 2 -> inimigos.add(new InimigoQuatro(xInimigo, yInimigo, 0.7, 30, 10, random.nextDouble(0.5)));
                    default -> inimigos.add(new Inimigo(xInimigo, yInimigo, 0.7, Color.WHITE, 30, 10, random.nextDouble(0.5)));
                }

            }
        }

        int xInicial = 50;
        int yInicial = 460;
        int tamanho = 10;

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 1; j <= 2; j++) {
                    obstaculos.add(new Obstaculo(
                            xInicial + i * tamanho,
                            yInicial + j * tamanho,
                            Color.WHITE,
                            tamanho,
                            tamanho));
                }
            }
            xInicial += 150;
        }
    }

    @Override
    public void atualizar() {
        frameCount++;
        for (Inimigo inimigo : inimigos) {
            inimigo.mover();
        }

        for (int p = 0; p < projeteis.size(); p++) {
            Projetil projetil = projeteis.get(p);
            projetil.mover();

            if (projetil.colide(jogador)) {
                System.out.println("Game over");
            }

            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo inimigo = inimigos.get(i);

                if (projetil.colide(inimigo)) {
                    pontos += inimigo.getPontuacao();
                    projeteis.remove(projetil);
                    inimigos.remove(inimigo);
                }
            }

            for (int i = 0; i < obstaculos.size(); i++) {
                Obstaculo obstaculo = obstaculos.get(i);

                if (projetil.colide(obstaculo)) {
                    projeteis.remove(projetil);
                    obstaculos.remove(obstaculo);
                }
            }
        }

        if (frameCount % 60 == 0) {
            for (Inimigo inimigo : inimigos) {
                if (random.nextDouble() < inimigo.getChanceDisparo() &&
                    podeDisparar(inimigo)
                ) {
                    projeteis.add(inimigo.disparar());
                }
            }
        }
    }

    private boolean podeDisparar(Inimigo inimigo) {
        for (Inimigo outro : inimigos) {
            if (inimigo.x == outro.x && outro.y > inimigo.y) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void desenhar(Graphics2D g) {
        desenharPlanoDeFundo(g);
        desenharPontuacao(g);

        // desenha a nave
        jogador.desenhar(g);

        for (Inimigo inimigo : inimigos) {
            inimigo.desenhar(g);
        }

        for (Projetil projetil : projeteis) {
            projetil.desenhar(g);
        }

        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.desenhar(g);
        }
    }

    private void desenharPontuacao(Graphics2D g) {
        g.setColor(Color.WHITE);

        Font font = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(font);
        g.drawString("Pontos: " + pontos, 30, 30);
    }

    private void desenharPlanoDeFundo(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getLargura(), getAltura());
    }

    @Override
    public void mouseMovido(MouseEvent e) {
        super.mouseMovido(e);
        var xMouse = e.getX();

        if (xMouse > 0 && xMouse < getLargura() - jogador.largura) {
            jogador.x = e.getX();
        }
    }

    @Override
    public void teclaPressionada(KeyEvent e) {
        super.teclaPressionada(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            projeteis.add(jogador.disparar());
        }
    }

    @Override
    public void mousePressionado(MouseEvent e) {
        super.mousePressionado(e);

        if (e.getButton() == MouseEvent.BUTTON1) {
            projeteis.add(jogador.disparar());
        }
    }
}
