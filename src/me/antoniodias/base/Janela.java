package me.antoniodias.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Este código faz parte da infraestrutura do jogo.
// Por enquanto, concentre-se apenas na classe me.antoniodias.breakout.MeuJogo.
// Ass: Antonio
public class Janela {

    private final JFrame frame;
    private final JPanel painel;
    private final Timer timer;
    private static final int MILISSEGUNDOS_EM_UM_SEGUNDO = 1000;
    private static final int FPS_ALVO = 60;

    public Janela(Jogo jogo, String titulo, int largura, int altura) {
        frame = new JFrame(titulo);
        painel = criarPainel(jogo);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel.setPreferredSize(new Dimension(largura, altura));
        jogo.inicializar(largura, altura);

        frame.setContentPane(painel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        int delay = MILISSEGUNDOS_EM_UM_SEGUNDO / FPS_ALVO;

        timer = new Timer(delay, e -> {
            jogo.atualizar();
            painel.repaint();
        });
    }

    private JPanel criarPainel(Jogo jogo) {
        JPanel painel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                );

                jogo.desenhar(g2);
            }
        };

        painel.setFocusable(true);

        painel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                jogo.teclaPressionada(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                jogo.teclaLiberada(e);
            }
        });

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                jogo.mousePressionado(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jogo.mouseLiberado(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                jogo.rodaMouseGirada(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                jogo.mouseArrastado(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jogo.mouseMovido(e);
            }
        };

        painel.addMouseListener(adapter);
        painel.addMouseMotionListener(adapter);
        painel.addMouseWheelListener(adapter);

        return painel;
    }

    public void exibir() {
        timer.start();
        frame.setVisible(true);
        painel.requestFocusInWindow();
    }
}