package me.antoniodias.plataforma;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.antoniodias.base.*;
import me.antoniodias.plataforma.fase.Fase;
import me.antoniodias.plataforma.fase.ItemJogo;
import me.antoniodias.plataforma.fase.Recurso;
import me.antoniodias.plataforma.itens.Coletavel;
import me.antoniodias.plataforma.itens.Moeda;
import me.antoniodias.plataforma.itens.Vida;
import me.antoniodias.plataforma.spritesheet.Animacao;
import me.antoniodias.plataforma.spritesheet.Coordenada;
import me.antoniodias.plataforma.spritesheet.MapeamentoSpritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogoPlataforma extends Jogo {
    private Personagem personagem;
    private Animacao[] animacoes;
    private Camera camera;
    private List<Plataforma> plataformas = new ArrayList<>();
    private List<Monstro> monstros = new ArrayList<>();

    private List<ObjetoJogo> itens = new ArrayList<>();
    private List<Caixa> caixas = new ArrayList<>();
    private MapeamentoSpritesheet mapeamentoRun;
    private MapeamentoSpritesheet mapeamentoIdle;
    private MapeamentoSpritesheet mapeamentoJump;
    private MapeamentoSpritesheet mapeamentoGhost;
    private MapeamentoSpritesheet mapeamentoSlime;
    private Fase fase1;
    private Plataforma ultimaPisada;
    private boolean direitaPressionada = false, esquerdaPressionada = false;

    BufferedImage moeda;
    BufferedImage vida;
    BufferedImage slime;
    BufferedImage ghost;
    BufferedImage[] imgsPersonagem;
    Map<String,BufferedImage> imagens = new HashMap<>();

    @Override
    public void inicializar(int largura, int altura) {
        super.inicializar(largura, altura);

        ObjectMapper mapper = new ObjectMapper();

        try {
            slime = ImageIO.read(getClass().getResource("/imagens/slime.png"));
            ghost = ImageIO.read(getClass().getResource("/imagens/ghost.png"));
            moeda = ImageIO.read(getClass().getResource("/imagens/moeda.png"));
            vida = ImageIO.read(getClass().getResource("/imagens/vida.png"));
            imgsPersonagem = new BufferedImage[] {
                    ImageIO.read(getClass().getResource("/imagens/Run.png")),
                    ImageIO.read(getClass().getResource("/imagens/Idle.png")),
                    ImageIO.read(getClass().getResource("/imagens/Jump.png"))
            };

            mapeamentoRun = mapper.readValue(
                    getClass().getResource("/imagens/Run.json"),
                    MapeamentoSpritesheet.class);
            mapeamentoIdle = mapper.readValue(
                    getClass().getResource("/imagens/Idle.json"),
                    MapeamentoSpritesheet.class);
            mapeamentoJump = mapper.readValue(
                    getClass().getResource("/imagens/Jump.json"),
                    MapeamentoSpritesheet.class);
            mapeamentoGhost = mapper.readValue(
                    getClass().getResource("/imagens/ghost.json"),
                    MapeamentoSpritesheet.class);
            mapeamentoSlime = mapper.readValue(
                    getClass().getResource("/imagens/slime.json"),
                    MapeamentoSpritesheet.class);
            fase1 = mapper.readValue(
                    getClass().getResource("/imagens/fase1.json"),
                    Fase.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*plataformas.add(new Plataforma(0, getAltura() - 70, getLargura(), 70));
        plataformas.add(new Plataforma(650, getAltura() - 170, 100, 30));
        plataformas.add(new Plataforma(550, getAltura() - 270, 100, 30));
        plataformas.add(new Plataforma(getLargura() + 150, getAltura() - 140, 200, 70));
        plataformas.add(new Plataforma(getLargura() + 550, getAltura() - 140, 350, 70));

        itens.add(new Moeda(100, getAltura() - 360, 0.5, 40, 40, moeda));
        itens.add(new Moeda(100, getAltura() - 280, 0.5, 40, 40, moeda));
        itens.add(new Moeda(100, getAltura() - 200, 0.5, 40, 40, moeda));
        itens.add(new Vida(getLargura() + 150, getAltura() - 200, 0.5, 40, 40, vida));
*/
        animacoes = new Animacao[]{
                new Animacao(100, mapeamentoRun, imgsPersonagem[0]),
                new Animacao(200, mapeamentoIdle, imgsPersonagem[1]),
                new Animacao(200, mapeamentoJump, imgsPersonagem[2]),
                new Animacao(100, mapeamentoGhost, ghost),
                new Animacao(100, mapeamentoSlime, slime)
        };
        personagem = new Personagem(largura / 2, 10, 7, 0.8, 50, 80, animacoes);

        for (Monstro monstro : fase1.monstros()) {
            switch (monstro.getTipo()) {
                case "ghost" -> monstro.setAnimacao(animacoes[3]);
                case "slime" -> monstro.setAnimacao(animacoes[4]);
            }
            monstro.setxInicial(monstro.getX());
            monstro.setyInicial(monstro.getY());
            monstros.add(monstro);
        }

        for(int i = 0; i < fase1.plataforma().size();i++){
            plataformas.add(fase1.plataforma().get(i));
        }
        for(int i = 0; i < fase1.itens().size();i++){
            ItemJogo d = fase1.itens().get(i);

            ObjetoJogo c = switch (d.nome()) {
                case "moeda" -> new Moeda(d.x(), d.y(), d.velY(), d.largura(), d.altura(),moeda);
                case "vida"  -> new Vida(d.x(), d.y(), d.velY(), d.largura(), d.altura(),vida);
                default -> System.out.println("Erro!");
            };

            itens.add(c);

        }
        for(Recurso res : fase1.recursos()){
            imagens.put(res.)
        }
    }

    @Override
    public void atualizar() {
        personagem.atualizar();

        if (personagem.getY() > getAltura()) {
            personagem.setVida(personagem.getVida() - 1);

            retornarParaUltimaPlataforma();
        }

        int deslocamento = 0;

        if (esquerdaPressionada) {
            personagem.moverEsquerda();
            deslocamento = 5;
        } else if (direitaPressionada) {
            personagem.moverDireita();
            deslocamento = -5;
        } else {
            personagem.parar();
        }

        deslocar(plataformas, deslocamento);
        deslocar(itens, deslocamento);
        deslocar(monstros, deslocamento);

        atualizar(itens);
        atualizar(monstros);

        for (Plataforma plataforma : plataformas) {
          if (personagem.colide(plataforma) == Personagem.CIMA) {
              personagem.pisar(plataforma);
              ultimaPisada = plataforma;
          }
        }

        for (int i = itens.size() - 1; i >= 0; i--) {
            ObjetoJogo obj = itens.get(i);

            if (personagem.colide(obj) > 0) {
                if (obj instanceof Coletavel coletavel) {
                    coletavel.coletar(personagem);
                }

                itens.remove(obj);
            }
        }
    }

    private void retornarParaUltimaPlataforma() {
        if (ultimaPisada != null) {
            // O novo valor de x coloca a última plataforma centralizada na tela
            double novoX =  (getLargura() / 2) - (ultimaPisada.getLargura() / 2);
            // O deslocamento é quanto falta para a plataforma, do seu x atual,
            // chegar ao novo x. Somamos a metade da largura do personagem para
            // ele se posicionar no centro
            double deslocamento = novoX - ultimaPisada.getX() + (personagem.getLargura()) / 2;

            deslocar(plataformas, (int)deslocamento);
            deslocar(itens, (int)deslocamento);
            deslocar(monstros, (int)deslocamento);

            personagem.setX(getLargura() / 2);
            personagem.setY(10);
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        desenharPlanoDeFundo(g);
        desenharPontuacao(g);

        personagem.desenhar(g);

        desenhar(g, plataformas);
        desenhar(g, itens);
        desenhar(g, monstros);
    }

    private void atualizar(List<? extends Atualizavel> atualizaveis) {
        for (Atualizavel atualizavel : atualizaveis) {
            atualizavel.atualizar();
        }

    }

    private void deslocar(List<? extends ObjetoJogo> objetos, int deslocamento) {
        if (deslocamento == 0) return;

        for (ObjetoJogo objeto : objetos) {
            objeto.deslocar(deslocamento);
        }
    }

    private void desenhar(Graphics2D g, List<? extends ObjetoJogo> objetos) {
        for (ObjetoJogo objeto : objetos) {
            objeto.desenhar(g);
        }
    }

    private void desenharPontuacao(Graphics2D g) {
        Font font = new Font("Monospaced", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawImage(vida, 30, 30, 30, 30, null);
        g.drawString("x" + personagem.getVida(), 65, 55);

        g.drawImage(moeda, 180, 30, 30, 30, null);
        g.drawString("x" + personagem.getPontos(), 215, 55);

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
