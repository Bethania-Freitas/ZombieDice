package br.com.ada.zombiedice.dado;

public class DadoAmarelo extends Dado {

    public DadoAmarelo() {
        super(
                new Face[]{
                        Face.CEREBRO,
                        Face.CEREBRO,
                        Face.PASSOS,
                        Face.PASSOS,
                        Face.TIRO,
                        Face.TIRO,
                });
    }
}