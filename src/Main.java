import me.antoniodias.plataforma.JogoPlataforma;
import me.antoniodias.base.Janela;

public class Main {
    public static void main(String[] args) {
        Janela janela = new Janela(new JogoPlataforma(), "Meu Jogo", 800, 600);
        janela.exibir();
    }
}
