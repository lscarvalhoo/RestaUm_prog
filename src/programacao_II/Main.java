package programacao_II;

public class Main
{
        public static void main(String[] args)
        {
                Posicao posicaoInicial = new Posicao((byte)3,(byte)3);
                Tabuleiro tabuleiro = new Tabuleiro(posicaoInicial);
                System.out.println("Tabuleiro Inicializado");
                tabuleiro.mostrarTabuleiro();
                Posicao posicaoFinal = new Posicao((byte)3, (byte) 3);
                tabuleiro.defineParada(posicaoFinal);
                System.out.println("Fim de jogo, sobram: "  /*numeroPeças*/ );
                tabuleiro.mostrarTabuleiro();
                tabuleiro.jogada();


        }
}
