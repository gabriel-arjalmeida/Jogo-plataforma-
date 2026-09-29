package me.antoniodias.solutions.plataforma;

import me.antoniodias.base.ObjetoJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PowerUpVelocidade extends ObjetoJogo implements Coletavel, Expiravel {

    private BufferedImage imagem;
    private long coletadoEm;
    private final static long DURACAO_NANOS = 3_000_000_000L;

    public PowerUpVelocidade(double x, double y, int largura, int altura) {
        super(x, y, 0, 0, largura, altura);
        try {
            imagem = ImageIO.read(getClass().getResource("/imagens/power-speed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(imagem, (int)getX(), (int)getY(), getLargura(), getAltura(), null);
    }

    @Override
    public void coletar(Personagem personagem) {
        personagem.setVelX(personagem.getVelX() * 2);
        coletadoEm = System.nanoTime();
    }

    @Override
    public boolean expirou() {
        return coletadoEm != 0
                && System.nanoTime() - coletadoEm > DURACAO_NANOS;
    }

    @Override
    public void expirar(Personagem personagem) {
        personagem.setVelX(personagem.getVelX() / 2);
    }

    @Override
    public void atualizar() {

    }
}
