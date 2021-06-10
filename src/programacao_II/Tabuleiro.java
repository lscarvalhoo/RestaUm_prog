package programacao_II;

public class Tabuleiro {

    byte vetorTabuleiro[][];
    Posicao resposta;

    int jogadasBemSucedidas = 0;
    int quantidadeDeJogadas = 0;
    byte tamanhoHorizontal = 7;
    byte tamanhoVertical = 7;
    byte pecas = 32;

    Historico armazenaHistoricoJogadas = new Historico();

    public Tabuleiro(Posicao posicao) {
        vetorTabuleiro = new byte[tamanhoHorizontal][tamanhoVertical];
        for (byte horizontal = 0; horizontal < 7; horizontal++) {
            for (byte vertical = 0; vertical < 7; vertical++) {
                if ((horizontal < 2 && vertical < 2) || (horizontal < 2 && vertical > 4) || (horizontal > 4 && vertical < 2) || (horizontal > 4 && vertical > 4)) {
                    vetorTabuleiro[horizontal][vertical] = 2;
                } else if (horizontal == posicao.horizontal && vertical == posicao.vertical) {
                    vetorTabuleiro[horizontal][vertical] = 0;
                } else {
                    vetorTabuleiro[horizontal][vertical] = 1;
                }
            }
        }
    }

    public void defineParada(Posicao posicao) {
        this.resposta = posicao;
    }

    public void mostrarTabuleiro() {
        for (byte horizontalLoop = 0; horizontalLoop < 7; horizontalLoop++) {
            System.out.println(" ");
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (vetorTabuleiro[horizontalLoop][verticalLoop] == 2) {
                    System.out.println(" ");
                } else {
                    System.out.println(vetorTabuleiro[horizontalLoop][verticalLoop] + " ");
                }
            }
            System.out.println("\n");
        }
    }

    public boolean exitemJogadasPosiveis() {
        for (byte horizontalLoop = 0; horizontalLoop < 7; horizontalLoop++) {
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (horizontalLoop + 2 >= 0 && horizontalLoop + 2 <= 6)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop + 2][verticalLoop] == 0 && vetorTabuleiro[horizontalLoop + 1][verticalLoop] == 1)
                    {
                        return true;
                    }
                }
                if (horizontalLoop - 2 >= 0 && horizontalLoop + 2 < 9)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop - 2][verticalLoop] == 0 && vetorTabuleiro[horizontalLoop - 1][verticalLoop] == 1)
                    {
                        return true;
                    }
                }
            }
        }
    }
}
