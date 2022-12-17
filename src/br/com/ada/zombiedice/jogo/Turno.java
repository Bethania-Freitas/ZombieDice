package br.com.ada.zombiedice.jogo;

import br.com.ada.zombiedice.dado.Dado;
import br.com.ada.zombiedice.dado.Face;
import br.com.ada.zombiedice.pote.PoteDado;
import br.com.ada.zombiedice.zumbi.Zumbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Turno {

    private List<Dado> dadosUtilizadosNoTurno;
    private int qtdeCerebros;
    private int qtdeTiros;
    private Zumbi jogador;
    private PoteDado pote;

    public Turno(Zumbi jogador, PoteDado pote) {
        this.jogador = jogador;
        this.pote = pote;
        this.qtdeCerebros = 0;
        this.qtdeTiros = 0;
        this.dadosUtilizadosNoTurno = new ArrayList<>();
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        String continuarJogando = "n";
        List<Dado> dadosASeeremRelancados = new ArrayList<>();
        do {
            System.out.println("Zumbie " + jogador.getNome()+" é a sua vez!");
            List<Dado> dadosLancados = jogador.lancarDado(pote, dadosASeeremRelancados);
            dadosASeeremRelancados.clear();
            for (Dado dado : dadosLancados) {
                System.out.println("Você lançou o dado " + dado.getTipoDado() +
                        " e obteve o resultado " + dado.getFaceSorteada());
                boolean dadoContabilizado = contabilizarDadoJogado(dado);
                if (!dadoContabilizado){
                    dadosASeeremRelancados.add(dado);
                }
            }
            boolean forcarEncerramento = forcarEncerramentoDeTurno();
            if (forcarEncerramento){
                if(qtdeTiros < 3){
                    System.out.println("Turno encerrado, pois você consumiu 13 cerebros");
                } else {
                    System.out.println("Turno encerrado, você levou 3 tiros!");
                    break;
                }
            }
            System.out.println(jogador.getNome()+", você tem nesta rodada " + qtdeCerebros + " cerebros e "+ qtdeTiros + " tiros");
            System.out.println(jogador.getNome() +", deseja continuar jogando?(s/n)");
            continuarJogando = scanner.nextLine();
        } while (continuarJogando.equalsIgnoreCase("s"));
        contabilizarTurno();
        pote.devolverDado(dadosUtilizadosNoTurno);
        pote.devolverDado(dadosASeeremRelancados);
    }

    private boolean forcarEncerramentoDeTurno() {

        return qtdeTiros >= 3 || jogador.getQtdeCerebros() + qtdeCerebros >= 13;
    }

    private boolean contabilizarDadoJogado(Dado dado) {
        boolean contablizado = dado.getFaceSorteada() != Face.PASSOS;
        if ( contablizado) {
            dadosUtilizadosNoTurno.add(dado);
            if (dado.getFaceSorteada() == Face.CEREBRO) {
                System.out.println("Você consumiu mais um cerebro");
            qtdeCerebros++;
            } else {
                System.out.println("Você levou um tiro");
                qtdeTiros++;
            }
        } else {
            System.out.println("Sua presa fugiu");
        }
        return contablizado;
    }

    private void contabilizarTurno() {
        if (qtdeTiros < 3) {
            int qtdeCerebrosAtual = jogador.getQtdeCerebros();
            int novaQtdeCerebrosConsumidos = qtdeCerebrosAtual + qtdeCerebros;
            jogador.setQtdeCerebros(novaQtdeCerebrosConsumidos);
            System.out.println("Contabilizado turno do jogador: " + jogador.getNome() +
                    ", encerrado com um total de " + novaQtdeCerebrosConsumidos +
                    " cerebros consumidos");
        }
    }
}
