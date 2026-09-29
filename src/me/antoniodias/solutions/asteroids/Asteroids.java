package me.antoniodias.solutions.asteroids;

import me.antoniodias.base.Jogo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;

public class Asteroids extends Jogo {
    private double x, y, vx, vy;
    private double[] asteroidesX, asteroidesY, asteroidesVX, asteroidesVY, asteroidesTamanho;
    private ArrayList<Projetil> projeteis = new ArrayList<>();
    private double orientacao;
    private boolean esquerda, direita, cima;

    @Override
    public void inicializar(int largura, int altura) {
        super.inicializar(largura, altura);

        x = largura / 2;
        y = altura / 2;
        vx = 0;
        vy = 0;
        orientacao = Math.toRadians(0);

        Random random = new Random();

        asteroidesX = new double[] {
            random.nextDouble(largura),
            random.nextDouble(largura),
            random.nextDouble(largura),
            random.nextDouble(largura),
        };
        asteroidesY = new double[] {
            random.nextDouble(altura),
            random.nextDouble(altura),
            random.nextDouble(altura),
            random.nextDouble(altura),
        };
        asteroidesVX = new double[] {
            random.nextDouble(0.2, 0.9),
            random.nextDouble(0.2, 0.9),
            random.nextDouble(0.2, 0.9),
            random.nextDouble(0.2, 0.9)
        };
        asteroidesVY = new double[] {
            random.nextDouble(-0.9, 0.9),
            random.nextDouble(-0.9, 0.9),
            random.nextDouble(-0.9, 0.9),
            random.nextDouble(-0.9, 0.9)
        };
        asteroidesTamanho = new double[] {
            random.nextDouble(40, 160),
            random.nextDouble(40, 160),
            random.nextDouble(40, 160),
            random.nextDouble(40, 160),
        };
    }

    @Override
    public void atualizar() {
        if (esquerda) {
            orientacao -= Math.toRadians(5);
        }

        if (direita) {
            orientacao += Math.toRadians(5);
        }

        acelerarNave();
        atualizarProjeteis();
        atualizarNave();
        atualizarAsteroides();
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getLargura(), getAltura());

        g.setColor(Color.WHITE);

        desenharProjeteis(g);
        desenharAsteroides(g);
        desenharNave(g);
    }

    private void atualizarAsteroides() {
        for (var i = 0; i < asteroidesX.length; i++) {
            asteroidesX[i] += asteroidesVX[i];
            asteroidesY[i] += asteroidesVY[i];

            if (asteroidesX[i] < 0) {
                asteroidesX[i] = getLargura();
            } else if (asteroidesX[i] > getLargura()) {
                asteroidesX[i] = 0;
            }

            if (asteroidesY[i] < 0) {
                asteroidesY[i] = getAltura();
            } else if (asteroidesY[i] > getAltura()) {
                asteroidesY[i] = 0;
            }
        }
    }
    private void desenharAsteroides(Graphics2D g) {
        for (var i = 0; i < asteroidesX.length; i++) {
            g.drawOval(
                    (int)(asteroidesX[i] - asteroidesTamanho[i] / 2),
                    (int)(asteroidesY[i] - asteroidesTamanho[i] / 2),
                    (int)asteroidesTamanho[i],
                    (int)asteroidesTamanho[i]);
        }
    }

    private void atualizarNave() {
        final double AMORTECIMENTO = 0.98;
        vx *= AMORTECIMENTO;
        vy *= AMORTECIMENTO;

        x += vx;
        y += vy;

        if (x < 0) {
            x = getLargura();
        } else if (x > getLargura()) {
            x = 0;
        }

        if (y < 0) {
            y = getAltura();
        } else if (y > getAltura()) {
            y = 0;
        }
    }

    private void atualizarProjeteis() {
        for (Projetil projetil : projeteis) {
            projetil.atualizar();
        }
    }

    private void desenharProjeteis(Graphics2D g) {
        for (Projetil projetil : projeteis) {
            projetil.desenhar(g);
        }
    }

    private void disparar() {
        final int FORCA = 12;
        final double COMPENSACAO_ANGULO = Math.toRadians(90);

        projeteis.add(new Projetil(x, y,
                Math.cos(orientacao - COMPENSACAO_ANGULO) * FORCA,
                Math.sin(orientacao - COMPENSACAO_ANGULO) * FORCA, 3));
    }

    private void acelerarNave() {
        if (cima) {
            final double COMPENSACAO_ANGULO = Math.toRadians(90);

            vx += Math.cos(orientacao - COMPENSACAO_ANGULO) * 0.2;
            vy += Math.sin(orientacao - COMPENSACAO_ANGULO) * 0.2;
        }
    }

    private void desenharNave(Graphics2D g) {
        AffineTransform current = g.getTransform();

        g.translate(x, y);
        g.rotate(orientacao);
        Path2D path = new Path2D.Double();

        path.moveTo(0, -15);
        path.lineTo(10, 15);
        path.lineTo(0, 10);
        path.lineTo(-10, 15);

        path.closePath();
        g.draw(path);

        g.setTransform(current);
    }

    @Override
    public void teclaPressionada(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> esquerda = true;
            case KeyEvent.VK_RIGHT -> direita = true;
            case KeyEvent.VK_UP -> cima = true;
            case KeyEvent.VK_SPACE -> disparar();
        }
    }

    @Override
    public void teclaLiberada(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> esquerda = false;
            case KeyEvent.VK_RIGHT -> direita = false;
            case KeyEvent.VK_UP -> cima = false;
        }
    }
}
