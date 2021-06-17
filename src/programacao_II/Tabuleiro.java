package programacao_II;

public class Tabuleiro {

    byte[][] vetorTabuleiro;
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
                if ((horizontal < 2 && vertical < 2) || (horizontal < 2 && vertical > 4) ||
                        (horizontal > 4 && vertical < 2) || (horizontal > 4 && vertical > 4)) {
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
            System.out.print("  ");
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (vetorTabuleiro[horizontalLoop][verticalLoop] == 2) {
                    System.out.print("  ");
                } else {
                    System.out.print(vetorTabuleiro[horizontalLoop][verticalLoop] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    public void mosrtarResultadoFinal() {
        for (byte horizontalLoop = 0; horizontalLoop < 7; horizontalLoop++) {
            System.out.print(" ");
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (vetorTabuleiro[resposta.getHorizontal()][resposta.getVertical()] == 2) {
                    System.out.print(" ");
                } else {
                    System.out.print(vetorTabuleiro[resposta.getHorizontal()][resposta.getVertical()] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    public boolean exitemJogadasPosiveis() {
        for (byte horizontalLoop = 0; horizontalLoop < 7; horizontalLoop++) {
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (horizontalLoop + 2 >= 0 && horizontalLoop + 2 <= 6)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                        vetorTabuleiro[horizontalLoop + 2][verticalLoop] == 0 &&
                        vetorTabuleiro[horizontalLoop + 1][verticalLoop] == 1)
                    {
                        return true;
                    }
                }
                if (horizontalLoop - 2 >= 0  && horizontalLoop + 2 < 9)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                        vetorTabuleiro[horizontalLoop - 2][verticalLoop] == 0 &&
                        vetorTabuleiro[horizontalLoop - 1][verticalLoop] == 1)
                    {
                        return true;
                    }
                }

                if (verticalLoop + 2 <= 6 && verticalLoop + 2 >= 0 )
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop + 2] == 0 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop  + 1] == 1)
                    {
                        return true;
                    }
                }

                if (verticalLoop - 2 >= 0 && verticalLoop - 2 <= 6)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop - 2] == 0 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop - 1] == 1)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void jogada() {
        for (byte horizontalLoop = 0; horizontalLoop < 7; horizontalLoop++)
        {
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++)
            {
                if (horizontalLoop + 2 >= 0 && horizontalLoop + 2 <= 6)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                        vetorTabuleiro[horizontalLoop + 2][verticalLoop] == 0 &&
                        vetorTabuleiro[horizontalLoop + 1 ][verticalLoop] == 1)
                    {
                        Posicao posicaoInicialCimaParaBaixo = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalCimaParaBaixo = new Posicao((byte) (horizontalLoop + 2), verticalLoop);

                        vetorTabuleiro[posicaoFinalCimaParaBaixo.getHorizontal()][posicaoFinalCimaParaBaixo.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.getHorizontal()][posicaoInicialCimaParaBaixo.getVertical()] = 0;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.getHorizontal() + 1][posicaoInicialCimaParaBaixo.getVertical()] = 1;
                        this.quantidadeDeJogadas++;
                        this.pecas--;

                        Jogadas novaJogada = new Jogadas(posicaoInicialCimaParaBaixo, posicaoFinalCimaParaBaixo, Movimentos.CimaParaBaixo);
                        armazenaHistoricoJogadas.adicionaJogadas(novaJogada);

                        if (this.pecas > 1 && exitemJogadasPosiveis())
                        {
                            jogada();
                        }
                        else
                        {
                            if (this.pecas == 1)
                            {
                                if (vetorTabuleiro[resposta.horizontal][resposta.vertical] == 1)
                                {
                                    jogadasBemSucedidas++;
                                    System.out.print("  -VALIDO  ");
                                    System.out.printf(" Finalizado");
                                    System.out.printf(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.printf(" Jogadas Realizadas : " + quantidadeDeJogadas);
                                    System.out.println(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.print(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.print("  ");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalCimaParaBaixo.getHorizontal()][posicaoFinalCimaParaBaixo.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.getHorizontal()][posicaoInicialCimaParaBaixo.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.getHorizontal() + 1][posicaoInicialCimaParaBaixo.getVertical()] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }

                if (horizontalLoop - 2 >= 0 && horizontalLoop + 2 < 9)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop - 1][verticalLoop] == 1 &&
                            vetorTabuleiro[horizontalLoop - 2][verticalLoop] == 0 )
                    {
                        Posicao posicaoInicialBaixoParaCima = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalBaixoParaCima = new Posicao((byte) (horizontalLoop - 2), verticalLoop);

                        vetorTabuleiro[posicaoFinalBaixoParaCima.getHorizontal()][posicaoFinalBaixoParaCima.getVertical()] = 1; //0, 1
                        vetorTabuleiro[posicaoInicialBaixoParaCima.getHorizontal()][posicaoInicialBaixoParaCima.getVertical()] = 0;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.getHorizontal() - 1][posicaoInicialBaixoParaCima.getVertical()] = 1;
                        this.quantidadeDeJogadas++;
                        this.pecas--;

                        Jogadas novaJogada = new Jogadas(posicaoInicialBaixoParaCima, posicaoFinalBaixoParaCima, Movimentos.BaixoParaCima);
                        armazenaHistoricoJogadas.adicionaJogadas(novaJogada);

                        if (this.pecas > 1 && exitemJogadasPosiveis())
                        {
                            jogada();
                        }
                        else
                        {
                            if (this.pecas == 1)
                            {
                                if (vetorTabuleiro[resposta.horizontal][resposta.vertical] == 1)
                                {
                                    jogadasBemSucedidas++;
                                    System.out.print("  -VALIDO  ");
                                    System.out.printf(" Finalizado");
                                    System.out.printf(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.printf(" Jogadas Realizadas : " + quantidadeDeJogadas);
                                    System.out.println(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.print(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.print("  ");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalBaixoParaCima.getHorizontal()][posicaoFinalBaixoParaCima.getVertical()] = 0;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.getHorizontal()][posicaoInicialBaixoParaCima.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.getHorizontal() - 1][posicaoInicialBaixoParaCima.getVertical()] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }

                if (verticalLoop + 2 <= 6 && verticalLoop + 2 >= 0 )
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                            vetorTabuleiro[horizontalLoop][verticalLoop + 1] == 1 &&
                            vetorTabuleiro[horizontalLoop][verticalLoop  + 2] == 0 )
                    {
                        Posicao posicaoInicialEsquerdaParaDireita = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalEsquerdaParaDireita = new Posicao(horizontalLoop, (byte) (verticalLoop + 2));

                        vetorTabuleiro[posicaoFinalEsquerdaParaDireita.getHorizontal()][posicaoFinalEsquerdaParaDireita.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.getHorizontal()][posicaoInicialEsquerdaParaDireita.getVertical()] = 0;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.getHorizontal()][posicaoInicialEsquerdaParaDireita.getVertical() + 1] = 0;
                        this.quantidadeDeJogadas++;
                        this.pecas--;

                        Jogadas novaJogada = new Jogadas(posicaoInicialEsquerdaParaDireita, posicaoFinalEsquerdaParaDireita, Movimentos.EsquerdaParaDireita);
                        armazenaHistoricoJogadas.adicionaJogadas(novaJogada);

                        if (this.pecas > 1 && exitemJogadasPosiveis())
                        {
                            jogada();
                        }
                        else
                        {
                            if (this.pecas == 1)
                            {
                                if (vetorTabuleiro[resposta.horizontal][resposta.vertical] == 1)
                                {
                                    jogadasBemSucedidas++;
                                    System.out.print("  -VALIDO  ");
                                    System.out.print(" Finalizado");
                                    System.out.print(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.print(" Jogadas Realizadas : " + quantidadeDeJogadas);
                                    System.out.println(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.print(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.print("");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalEsquerdaParaDireita.getHorizontal()][posicaoFinalEsquerdaParaDireita.getVertical()] = 0;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.getHorizontal()][posicaoInicialEsquerdaParaDireita.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.getHorizontal()][posicaoInicialEsquerdaParaDireita.getVertical() + 1] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }

                if (verticalLoop - 2 >= 0 && verticalLoop - 2 <= 6)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop][verticalLoop - 1] == 1 &&
                            vetorTabuleiro[horizontalLoop][verticalLoop  - 2] == 0)
                    {
                        Posicao posicaoInicialDireitaParaEsquerda = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalDireitaParaEsquerda = new Posicao(horizontalLoop, (byte) (verticalLoop - 2));

                        vetorTabuleiro[posicaoFinalDireitaParaEsquerda.getHorizontal()][posicaoFinalDireitaParaEsquerda.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.getHorizontal()][posicaoInicialDireitaParaEsquerda.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.getHorizontal()][posicaoInicialDireitaParaEsquerda.getVertical() - 1] = 0;
                        this.quantidadeDeJogadas++;
                        this.pecas--;

                        Jogadas novaJogada = new Jogadas(posicaoInicialDireitaParaEsquerda, posicaoFinalDireitaParaEsquerda, Movimentos.DireitaParaEsquerda);
                        armazenaHistoricoJogadas.adicionaJogadas(novaJogada);

                        if (this.pecas > 1 && exitemJogadasPosiveis())
                        {
                            jogada();
                        }
                        else
                        {
                            if (this.pecas == 1)
                            {
                                if (vetorTabuleiro[resposta.horizontal][resposta.vertical] == 1)
                                {
                                    jogadasBemSucedidas++;
                                    System.out.print("  -VALIDO  ");
                                    System.out.print(" Finalizado");
                                    System.out.print(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.print(" Jogadas Realizadas : " + quantidadeDeJogadas);
                                    System.out.println(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.print(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.print(" ");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalDireitaParaEsquerda.getHorizontal()][posicaoFinalDireitaParaEsquerda.getVertical()] = 1;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.getHorizontal()][posicaoInicialDireitaParaEsquerda.getVertical()] = 0;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.getHorizontal()][posicaoInicialDireitaParaEsquerda.getVertical() - 1] = 0;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }
            }
        }
    }

}
