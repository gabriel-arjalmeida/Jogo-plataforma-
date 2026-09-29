package me.antoniodias.breakout;

import me.antoniodias.base.Jogo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MeuJogo extends Jogo {

    private Bola bola;
    private Tijolo[] tijolos = new Tijolo[50];
    private Barra barra;
    private boolean esquerdaPressionada, direitaPressionada;
    private Estado estado;
    private int pontos = 0;

    @Override
    public void inicializar(int largura, int altura) {
        super.inicializar(largura, altura);

        Random random = new Random();

        estado = Estado.JOGANDO;

        bola = new Bola(largura / 2, altura / 2,
                7, random.nextDouble(2, 4),
                random.nextDouble(2, 4));

        var index = 0;
        final var QTD_COLUNAS = 10;
        final var QTD_LINHAS = 5;
        final var LARGURA = 70;
        final var ALTURA = 30;
        final var ESPACO = 3;
        var larguraTotal = QTD_COLUNAS * (LARGURA + ESPACO) - ESPACO;
        var inicio = (getLargura() - larguraTotal) / 2;

        for (var y = 50; y < 50 + QTD_LINHAS * (ALTURA + ESPACO); y += ALTURA + ESPACO) {
            for (var x = inicio; x < inicio + QTD_COLUNAS * (LARGURA + ESPACO); x += LARGURA + ESPACO) {
                var num = random.nextDouble();
                Color c = new Color(0, 0, 255, 255);
                try {
                    tijolos[index] = new Tijolo(x, y, LARGURA, ALTURA, 1, c);

                    if (num < 1) {
                        tijolos[index].setPowerUp(new PowerUp(x, y, "P", 0xFF0000, 1));
                    }
                } catch (ValorInvalidoException e) {
                    e.printStackTrace();
                }
                index += 1;
            }
        }

        barra = new Barra(largura / 2 - 40, 560, 100, 30);
    }

    @Override
    public void atualizar() {
        if (estado == Estado.JOGANDO) {
            bola.mover();
            if (bola.tratarBordas(getLargura(), getAltura())) {
                estado = Estado.GAMEOVER;
            }

            if (bola.colide(barra) == Barra.CIMA) {
                double velAbs = Math.abs(bola.getVelX());
                Random random = new Random();
                double fator = random.nextDouble(0.8, 1.2);
                if (direitaPressionada) {
                    bola.setVelX(fator * velAbs);
                } else if (esquerdaPressionada) {
                    bola.setVelX(-fator * velAbs);
                }

                bola.setVelY(-bola.getVelY());
            }

            for (int i = 0; i < tijolos.length; i++) {
                int ladoColisao = bola.colide(tijolos[i]);

                if (tijolos[i].getPowerUp() != null)
                    tijolos[i].getPowerUp().mover();

                if (colide(tijolos[i].getPowerUp(), barra)) {
                    tijolos[i].getPowerUp().setUsado(true);
                }

                if (ladoColisao != Bola.NAO_COLIDE) {
                    if (tijolos[i].getPowerUp() != null) {
                        tijolos[i].getPowerUp().setRevelado(true);
                        System.out.println("Power up do tijolo " + i + " revelado");
                    }

                    tijolos[i].diminuirResistencia();

                    if (tijolos[i] != null && tijolos[i].getResistencia() == 0) {
                        tijolos[i].setDestruido(true);
                        pontos++;
                    }

                    if (ladoColisao == Bola.CIMA ||
                            ladoColisao == Bola.BAIXO) {
                        bola.setVelY(-bola.getVelY());
                    } else if (ladoColisao == Bola.ESQUERDA ||
                            ladoColisao == Bola.DIREITA) {
                        bola.setVelX(-bola.getVelX());
                    }

                    break;
                }
            }

            final int INCREMENTO = 8;

            if (direitaPressionada) {
                barra.setX(barra.getX() + INCREMENTO);
            }

            if (esquerdaPressionada) {
                barra.setX(barra.getX() - INCREMENTO);
            }
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        if (estado == Estado.JOGANDO) {
            desenharPlanoDeFundo(g);
            desenharPontuacao(g);

            for (var i = 0; i < tijolos.length; i++) {
                Tijolo tijolo = tijolos[i];

                if (tijolo != null && tijolo.getPowerUp() != null) {
                    tijolo.getPowerUp().desenhar(g);
                }

                if (tijolo != null && tijolo.isDestruido() == false) {
                    tijolo.desenhar(g);

                    if (tijolo.getPowerUp() != null) {
                        g.setColor(Color.RED);
                        g.fillRect((int)tijolo.getX(), (int)tijolo.getY(), 20, 20);
                    }
                }
            }
            bola.desenhar(g);
            barra.desenhar(g);
        } else if (estado == Estado.GAMEOVER) {
            desenharGameOver(g);
        }
    }

    public boolean colide(PowerUp p, Barra b) {
        // 20 é a largura e altura do power-up
        return p != null &&
                p.getY() + 20 > b.getY() &&
                p.getY() < b.getY() + b.getAltura() &&
                p.getX() < b.getX() + b.getLargura() &&
                p.getX() + 20 > b.getX();
    }

    private void desenharPlanoDeFundo(Graphics2D g) {
        g.setColor(new Color(0xfcc63d));
        g.fillRect(0, 0, getLargura(), getAltura());
    }

    private void desenharPontuacao(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawString("Pontos: " + pontos, 20, 20);
    }

    private void desenharGameOver(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getLargura(), getAltura());

        g.setColor(Color.WHITE);

        Font font = new Font("Monospaced", Font.PLAIN, 30);
        desenharStringCentralizada(g, "Game Over!", font, 0);
        desenharStringCentralizada(g, "Pontos: " + pontos, font, 40);
    }

    public void desenharStringCentralizada(Graphics2D g, String text, Font font, int verticalSpacing) {
        Rectangle rect = new Rectangle(getLargura(), getAltura());
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y + verticalSpacing);
    }

    private void desenharVitoria(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getLargura(), getAltura());

        g.setColor(Color.WHITE);
        g.drawString("Vitória!", getLargura() / 2, getAltura() / 2);
        g.drawString("Pontos: " + pontos, getLargura() / 2, getAltura() / 2 + 60);
    }

    @Override
    public void teclaPressionada(KeyEvent e) {
        super.teclaPressionada(e);

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdaPressionada = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direitaPressionada = true;
        }
    }

    @Override
    public void teclaLiberada(KeyEvent e) {
        super.teclaLiberada(e);

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdaPressionada = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direitaPressionada = false;
        }
    }
}
