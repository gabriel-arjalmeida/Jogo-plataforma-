package me.antoniodias.solutions.plataforma;

import me.antoniodias.base.Jogo;
import me.antoniodias.base.ObjetoJogo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class JogoPlataforma extends Jogo {
    private Personagem personagem;
    private List<Plataforma> plataformas = new ArrayList<>();
    private List<ObjetoJogo> itens = new ArrayList<>();

    private boolean direitaPressionada = false, esquerdaPressionada = false;
    @Override
    public void inicializar(int largura, int altura) {
        super.inicializar(largura, altura);

        personagem = new Personagem(largura / 2, 10, 7, 0.8, 50, 80);

        plataformas.add(new Plataforma(0, getAltura() - 70, getLargura(), 70));
        plataformas.add(new Plataforma(650, getAltura() - 170, 100, 30));
        plataformas.add(new Plataforma(550, getAltura() - 270, 100, 30));

//        itens.add(new Moeda(100, 100, 40, 40));
//        itens.add(new Vida(680, getAltura() - 230, 40, 40));
//        itens.add(new PowerUpVelocidade(580, getAltura() - 330, 40, 40));
    }

    @Override
    public void atualizar() {
        personagem.atualizar();

        if (esquerdaPressionada) {
            personagem.setX(personagem.getX() - personagem.getVelX());
        }
        if (direitaPressionada) {
            personagem.setX(personagem.getX() + personagem.getVelX());
        }

        for (Plataforma plataforma : plataformas) {
          if (personagem.colide(plataforma) == Personagem.CIMA) {
              personagem.pisar(plataforma);
          }
        }

        for (int i = 0; i < itens.size(); i++) {
            ObjetoJogo objeto = itens.get(i);

            if (objeto instanceof Coletavel coletavel) {
                if (personagem.colide(objeto) > 0) {
                    coletavel.coletar(personagem);

                    if (objeto instanceof Expiravel expiravel) {
                        personagem.setEfeito(expiravel);
                    }

                    itens.remove(objeto);
                }
            }
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        desenharPlanoDeFundo(g);
        desenharPontuacao(g);
        personagem.desenhar(g);

        for (Plataforma plataforma : plataformas) {
            plataforma.desenhar(g);
        }

        for (ObjetoJogo item : itens) {
            if (item instanceof Expiravel expiravel
                    && expiravel.expirou()) {
                continue;
            }

            item.desenhar(g);
        }
    }

    private void desenharPontuacao(Graphics2D g) {
        Font font = new Font("Monospaced", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Pontos: " + personagem.getPontos() + " Vidas: " + personagem.getVida(), 30, 30);
    }

    private void desenharPlanoDeFundo(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getLargura(), getAltura());
    }

    @Override
    public void teclaPressionada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            personagem.pular();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdaPressionada = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direitaPressionada = true;
        }
    }

    @Override
    public void teclaLiberada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdaPressionada = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direitaPressionada = false;
        }
    }
}
