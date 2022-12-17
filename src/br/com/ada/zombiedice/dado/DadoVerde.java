package br.com.ada.zombiedice.dado;

public class DadoVerde extends Dado {

    public DadoVerde() {
        super(
                new Face[]{
                    Face.CEREBRO,
                    Face.CEREBRO,
                    Face.CEREBRO,
                    Face.PASSOS,
                    Face.PASSOS,
                    Face.TIRO,
        });
    }

    @Override
    public TipoDado getTipoDado() {
        return TipoDado.VERDE;
    }
}
