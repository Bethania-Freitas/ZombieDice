package br.com.ada.zombiedice.zumbi;

public class Zumbi {

    private String nome;
    private int qtdeCerebros;
    private int qtdeTiros;

    public Zumbi(String nome) {
        this.nome = nome;
        this.qtdeCerebros = 0;
        this.qtdeTiros = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdeCerebros() {
        return qtdeCerebros;
    }

    public void setQtdeCerebros(int qtdeCerebros) {
        this.qtdeCerebros = qtdeCerebros;
    }

    public int getQtdeTiros() {
        return qtdeTiros;
    }

    public void setQtdeTiros(int qtdeTiros) {
        this.qtdeTiros = qtdeTiros;
    }
}
