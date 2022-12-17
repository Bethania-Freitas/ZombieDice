package br.com.ada.zombiedice.dado;

public class DadoVermelho extends Dado{

    public DadoVermelho() {
        super(new Face[]{
                Face.CEREBRO,
                Face.PASSOS,
                Face.PASSOS,
                Face.TIRO,
                Face.TIRO,
                Face.TIRO,
        });
    }

    @Override
    public TipoDado getTipoDado() {
        return TipoDado.VERMELHO;
    }
}
