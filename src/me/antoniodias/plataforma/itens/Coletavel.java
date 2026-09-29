package me.antoniodias.plataforma.itens;

import me.antoniodias.base.Atualizavel;
import me.antoniodias.plataforma.Personagem;

public interface Coletavel extends Atualizavel {

    void coletar(Personagem personagem);
}
