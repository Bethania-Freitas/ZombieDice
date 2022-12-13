import br.com.ada.zombiedice.dado.DadoVerde;

public class ZombieDice {
    public static void main(String[] args) {
        DadoVerde dadoVerde = new DadoVerde();
        System.out.println("O valor pro dado verde foi " + dadoVerde.getFaceSorteada());
    }
}
