package me.antoniodias.base.mapeamento;

import java.util.List;

public record MapeamentoAnimacao(
        List<Coordenada> frames,
        int frameHeight,
        int frameWidth
) {
}
