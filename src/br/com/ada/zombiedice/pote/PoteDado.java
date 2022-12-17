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
        List<Dado> buffer = new ArrayList<>();
        int contador = quantidade;
        while(contador > 0) {
            Dado removidoDaListaOriginal = this.dados.remove(0);
            buffer.add(removidoDaListaOriginal);
            contador--;
        }
        return buffer;
    }

    public void devolverDado(List<Dado> dados) {
        for (Dado dado: dados) {
            dado.resetFaceSorteada();
        }
        this.dados.addAll(dados);

    }
}
