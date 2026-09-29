package me.antoniodias.plataforma.fase;

import me.antoniodias.base.ObjetoJogo;
import me.antoniodias.plataforma.Monstro;
import me.antoniodias.plataforma.Plataforma;
import me.antoniodias.plataforma.itens.Coletavel;

import java.util.List;

public record Fase(List<Plataforma> plataforma, List<Monstro> monstros, List<Recurso> recursos, List<ItemJogo> itens) {
}
