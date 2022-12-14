package br.com.ada.zombiedice;

import br.com.ada.zombiedice.dado.Dado;
import br.com.ada.zombiedice.dado.DadoAmarelo;
import br.com.ada.zombiedice.dado.DadoVerde;
import br.com.ada.zombiedice.dado.DadoVermelho;
import br.com.ada.zombiedice.jogo.Turno;
import br.com.ada.zombiedice.pote.PoteDado;
import br.com.ada.zombiedice.zumbi.Zumbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZombieDice {

    private List<Zumbi> jogadores;
    private int posicaoJogadorNaLista;

    public ZombieDice() {
        jogadores = new ArrayList<>();
        posicaoJogadorNaLista = 0;
    }

    public static void main(String[] args) {
        ZombieDice dice = new ZombieDice();
        System.out.println("      ▐███████▌");
        System.out.println("      ▐░▀░▀░▀░▌");
        System.out.println("      ▐▄▄▄▄▄▄▄▌");
        System.out.println("▄▀▀▀█▒▐░▀▀▄▀▀░▌▒█▀▀▀▄");
        System.out.println("▌▌▌▌▐▒▄▌░▄▄▄░▐▄▒▌▐▐▐▐");
        System.out.println("Z O M B I E  D I C E");
        System.out.println();
        dice.iniciarPartida();
    }

    public void cadastrarJogadores() {
        Scanner scanner = new Scanner(System.in);
        String continuarCadastrar = "n";

        do {
            System.out.println("Cadastre o nome do jogador: ");
            String nome = scanner.nextLine();
            Zumbi jogador = new Zumbi(nome);
            jogadores.add(jogador);
            System.out.println("Cadastrar um novo jogador?(s/n)");
            continuarCadastrar = scanner.nextLine();
        } while (continuarCadastrar.equalsIgnoreCase("s"));
    }

    public void iniciarPartida() {
        cadastrarJogadores();
        if (jogadores.size() < 2) {
            System.out.println("Você deve cadastrar no minimo 2 jogadores");
            cadastrarJogadores();
        }
        PoteDado pote = criarPote();

        boolean continuarJogando = true;
        do {
            Zumbi jogadorAtual = jogadores.get(posicaoJogadorNaLista);
            Turno turnoAtual = new Turno(jogadorAtual, pote);
            turnoAtual.jogar();
            System.out.println();
            System.out.println("------------- CEREBROS CONSUMIDOS --------------");
            placar();
            System.out.println();
            continuarJogando = jogadorAtual.getQtdeCerebros() < 13;
            if (continuarJogando) {
                posicaoJogadorNaLista++;
                if (posicaoJogadorNaLista >= jogadores.size()) {
                    posicaoJogadorNaLista = 0;
                }
            } else {
                parabenizarJogador(jogadorAtual);
            }
        } while (continuarJogando);
        System.out.println("Fim de jogo!");
    }

    public void parabenizarJogador(Zumbi jogador) {
        System.out.println();
        System.out.println("      ▐███████▌");
        System.out.println("      ▐░▀░▀░▀░▌");
        System.out.println("      ▐▄▄▄▄▄▄▄▌");
        System.out.println("▄▀▀▀█▒▐░▀▀▄▀▀░▌▒█▀▀▀▄");
        System.out.println("▌▌▌▌▐▒▄▌░▄▄▄░▐▄▒▌▐▐▐▐");
        System.out.println(" P A R A B É N S !  " + jogador.getNome().toUpperCase()+ " ");
        System.out.println("você consumiu 13 CEREBROS");
    }

    public PoteDado criarPote() {
        ArrayList<Dado> dados = new ArrayList<>();
        dados.add(new DadoVerde());
        dados.add(new DadoVerde());
        dados.add(new DadoVerde());
        dados.add(new DadoVerde());
        dados.add(new DadoVerde());
        dados.add(new DadoVerde());
        dados.add(new DadoAmarelo());
        dados.add(new DadoAmarelo());
        dados.add(new DadoAmarelo());
        dados.add(new DadoAmarelo());
        dados.add(new DadoVermelho());
        dados.add(new DadoVermelho());
        dados.add(new DadoVermelho());
        PoteDado pote = new PoteDado(dados);
        return pote;
    }

    public void placar() {
        for (Zumbi jogador : this.jogadores) {
            System.out.println(jogador.getQtdeCerebros() + " - " + jogador.getNome());
        }
    }
}

