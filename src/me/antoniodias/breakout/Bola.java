package me.antoniodias.breakout;

import java.awt.*;

public class Bola {
    private double x, y;
    private double raio;
    private double velX, velY;

    public static final int NAO_COLIDE = 0;
    public static final int CIMA = 1;
    public static final int BAIXO = 2;
    public static final int ESQUERDA = 3;
    public static final int DIREITA = 4;

    public Bola(double x, double y, double raio, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.raio = raio;
        this.velX = velX;
        this.velY = velY;
    }

    public void mover() {
        x += velX;
        y += velY;
    }

    /**
     * Determina o comportamento da bola ao atingir as bordas
     *
     * @param largura A largura da tela do jogo.
     * @param altura A altura da tela do jogo
     * @return true, se a bola colide com a borda inferior ou false, caso contrário.
     */
    public boolean tratarBordas(int largura, int altura) {
        if (x < 0 || x > largura - 2 * raio) {
            velX = -velX;
        }

        if (y < 0) {
            velY = -velY;
        }

        if (y > altura - 2 * raio) {
            velY = -velY;

            return true;
        }

        return false;
    }

    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, (int) (raio * 2), (int) (raio * 2));
    }

    public int colide(Barra obj) {
        boolean colide = this.y + this.raio * 2 > obj.getY() &&
                this.y < obj.getY() + obj.getAltura() &&
                this.x < obj.getX() + obj.getLargura() &&
                this.x + this.raio * 2 > obj.getX();

        if (colide) {
            var overlapLeft = this.x + this.raio * 2 - obj.getX();
            var overlapRight = obj.getX() + obj.getLargura() - this.x;
            var overlapTop = this.y + this.raio * 2 - obj.getY();
            var overlapBottom = obj.getY() + obj.getAltura() - this.y;

            var minOverlap = Math.min(
                    Math.min(
                        overlapBottom,
                        overlapLeft),
                    Math.min(
                        overlapRight,
                        overlapTop
                    ));

            if (minOverlap == overlapTop && this.velY > 0) {
                // colide por cima
                return CIMA;
            } else if (minOverlap == overlapBottom && this.velY < 0) {
                // colide por baixo
                return BAIXO;
            } else if (minOverlap == overlapLeft && this.velX > 0) {
                // colide pela esquerda
                return ESQUERDA;
            } else if (minOverlap == overlapRight && this.velX < 0) {
                // colide pela direita
                return DIREITA;
            }
        }

        return NAO_COLIDE;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public int colide(Tijolo obj) {
        boolean colide = obj != null && this.y + this.raio * 2 > obj.getY() &&
                this.y < obj.getY() + obj.getAltura() &&
                this.x < obj.getX() + obj.getLargura() &&
                this.x + this.raio * 2 > obj.getX() &&
                !obj.isDestruido();

        if (colide) {
            var overlapLeft = this.x + this.raio * 2 - obj.getX();
            var overlapRight = obj.getX() + obj.getLargura() - this.x;
            var overlapTop = this.y + this.raio * 2 - obj.getY();
            var overlapBottom = obj.getY() + obj.getAltura() - this.y;

            var minOverlap = Math.min(
                    Math.min(
                            overlapBottom,
                            overlapLeft),
                    Math.min(
                            overlapRight,
                            overlapTop
                    ));

            if (minOverlap == overlapTop && this.velY > 0) {
                // colide por cima
                return CIMA;
            } else if (minOverlap == overlapBottom && this.velY < 0) {
                // colide por baixo
                return BAIXO;
            } else if (minOverlap == overlapLeft && this.velX > 0) {
                // colide pela esquerda
                return ESQUERDA;
            } else if (minOverlap == overlapRight && this.velX < 0) {
                // colide pela direita
                return DIREITA;
            }
        }

        return NAO_COLIDE;
    }
}
