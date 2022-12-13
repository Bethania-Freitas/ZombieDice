package br.com.ada.zombiedice.pote;

import br.com.ada.zombiedice.dado.Dado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PoteDado {

    private List<Dado> dados;

    public PoteDado(List<Dado> dados) {
        this.dados = dados;
    }

    public void embaralhar(){
        Collections.shuffle(dados);
    }

    public List<Dado> entregarDado(int quantidade) {

    }

    public void devolverDado(Dado[] dado) {

    }
}
