package me.antoniodias.spaceInvaders;

import me.antoniodias.base.Jogo;
import me.antoniodias.solutions.spaceInvaders.Obstaculo;

import java.awt.Color;
import java.awt.Graphics2D;
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
                    case 0 -> inimigos.add(new InimigoCiano(xInimigo, yInimigo, 0.7, 30, 10));
                    case 1 -> inimigos.add(new InimigoVermelho(xInimigo, yInimigo, 0.7, 30, 10));
                    case 2 -> inimigos.add(new InimigoAmarelo(xInimigo, yInimigo, 0.7, 30, 10));
                    default -> inimigos.add(new InimigoBranco(xInimigo, yInimigo, 0.7, 30, 10));
                }
            }
        }

        obstaculos.add(new Obstaculo(50, 460, Color.WHITE, 20, 20));
        obstaculos.add(new Obstaculo(70, 460, Color.WHITE, 20, 20));
        obstaculos.add(new Obstaculo(90, 460, Color.WHITE, 20, 20));
    }

    @Override
    public void atualizar() {
        frameCount++;
        for (Inimigo inimigo : inimigos) {
            inimigo.mover();
        }

        for (int p = 0; p < projeteis.size(); p++) {
            Projetil projetil = projeteis.get(p);
            projetil.atualizar();

            if (projetil.colide(jogador)) {
                System.out.println("Game over");
            }

            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo inimigo = inimigos.get(i);

                if (projetil.colide(inimigo)) {
                    projeteis.remove(projetil);
                    inimigo.setVivo(false);
                }

                if (inimigo.isVivo() == false && inimigo.getEstado() == 3) {
                    inimigos.remove(inimigo);
                }
            }
        }

        if (frameCount % 60 == 0) {
            for (Inimigo inimigo : inimigos) {
                if (random.nextDouble() < 0.2) {
                    projeteis.add(inimigo.disparar());
                }
            }
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        desenharPlanoDeFundo(g);

        // desenha a nave
        jogador.desenhar(g);

        for (Inimigo inimigo : inimigos) {
            if (inimigo.isVivo()) {
                inimigo.desenhar(g);
            } else {
                inimigo.desenharDestruido(g);
                if (frameCount % 15 == 0) inimigo.incEstado();
            }
        }

        for (Projetil projetil : projeteis) {
            projetil.desenhar(g);
        }

        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.desenhar(g);
        }
    }

    private void desenharPlanoDeFundo(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getLargura(), getAltura());
    }

    @Override
    public void mouseMovido(MouseEvent e) {
        super.mouseMovido(e);
        var xMouse = e.getX();

        if (xMouse > 0 && xMouse < getLargura() - jogador.getLargura()) {
            jogador.setX(e.getX());
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
