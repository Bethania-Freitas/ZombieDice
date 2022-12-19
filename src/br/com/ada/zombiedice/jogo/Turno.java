package br.com.ada.zombiedice.jogo;

import br.com.ada.zombiedice.dado.Dado;
import br.com.ada.zombiedice.dado.Face;
import br.com.ada.zombiedice.pote.PoteDado;
import br.com.ada.zombiedice.zumbi.Zumbi;

import java.io.IOException;
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
        ArrayList<Dado> dadosASeremRelancados = new ArrayList<>();
        System.out.println(jogador.getNome() + " é sua vez!");
        promptEnterKey();
        do {
            List<Dado> dadosLancados = jogador.lancarDados(pote, dadosASeremRelancados);
            dadosASeremRelancados.clear();
            for (Dado dado : dadosLancados) {
                boolean dadoContabilizado = contabilizarDadoJogado(dado);
                if (!dadoContabilizado) {
                    dadosASeremRelancados.add(dado);
                }
            }
            boolean forcarEncerramento = forcarEncerramentoTurno();
            if (forcarEncerramento) {
                if (qtdeTiros < 3) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Turno será encerrado pois você atingiu 13 cerebros consumidos");
                } else {
                    System.out.println(" /﹋\\");
                    System.out.println("(҂`_´)");
                    System.out.println("<,︻╦╤─ ҉ - -");
                    System.out.println("/﹋\\");
                    System.out.println("TURNO ENCERRADO  !");
                    System.out.println("você levou " + qtdeTiros + " tiros");
                }
                break;
            }
            System.out.println("Pontuação atual: " +  qtdeCerebros +
                    " cerebros consumidos, " + qtdeTiros + " tiros levados");
            System.out.println("------------------------------------------------------");
            System.out.println(jogador.getNome() + " deseja jogar novamente?(s/n)");
            continuarJogando = scanner.nextLine();
        } while (continuarJogando.equalsIgnoreCase("s"));
        contabilizarTurno();
        pote.devolverDado(dadosUtilizadosNoTurno);
        pote.devolverDado(dadosASeremRelancados);
    }

    private void promptEnterKey() {
        System.out.println("Aperte ENTER para jogar");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean forcarEncerramentoTurno() {
        return qtdeTiros >= 3 || jogador.getQtdeCerebros() + qtdeCerebros >= 13;
    }

    private boolean contabilizarDadoJogado(Dado dado) {
        System.out.println("Você lançou o dado " + dado.getTipoDado() +
                " e obteve o resultado " + dado.getFaceSorteada());
        boolean contabilizado = dado.getFaceSorteada() != Face.PASSOS;
        if (contabilizado) {
            dadosUtilizadosNoTurno.add(dado);
            if (dado.getFaceSorteada() == Face.CEREBRO) {
                qtdeCerebros++;
            } else {
                qtdeTiros++;
            }
        }
        return contabilizado;
    }

    private void contabilizarTurno() {
        if (qtdeTiros < 3) {
            int qtdeCerebrosAtual = jogador.getQtdeCerebros();
            int novaQtdeCerebrosConsumidos = qtdeCerebrosAtual + qtdeCerebros;
            jogador.setQtdeCerebros(novaQtdeCerebrosConsumidos);
            System.out.println("Contabilizado turno do jogador: ");
            System.out.println(jogador.getNome() + " - " + novaQtdeCerebrosConsumidos + " cerebros consumidos no turno");
        }
    }
}
