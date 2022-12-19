package br.com.ada.zombiedice.zumbi;

import br.com.ada.zombiedice.dado.Dado;
import br.com.ada.zombiedice.pote.PoteDado;

import java.util.ArrayList;
import java.util.List;

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

    public List<Dado> lancarDados(PoteDado pote, List<Dado> dadosSortearamPassosNaJogadaAnterior) {
        //3 quantidade base de dados
        int qtdeNecessario = 3 - dadosSortearamPassosNaJogadaAnterior.size();
        pote.embaralhar();
        List<Dado> dadosDoPote = pote.entregarDado(qtdeNecessario);
        List<Dado> dadosDisponiveis = new ArrayList<>(dadosDoPote);
        dadosDisponiveis.addAll(dadosSortearamPassosNaJogadaAnterior);
        for (Dado dado: dadosDisponiveis) {
            dado.jogar();
        }
        return dadosDisponiveis;
    }
    }

