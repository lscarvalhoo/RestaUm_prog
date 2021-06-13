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
            System.out.println("  ");
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (vetorTabuleiro[horizontalLoop][verticalLoop] == 2) {
                    System.out.print("  ");
                } else {
                    System.out.print(vetorTabuleiro[horizontalLoop][verticalLoop] + " ");
                }
            }
            System.out.println("\n");
        }
    }

    public void mosrtarResultadoFinal() {
        for (byte horizontalLoop = 0; horizontalLoop < 7; horizontalLoop++) {
            System.out.println(" ");
            for (byte verticalLoop = 0; verticalLoop < 7; verticalLoop++) {
                if (vetorTabuleiro[horizontalLoop][verticalLoop] == 2) {
                    System.out.print(" ");
                } else {
                    System.out.print(vetorTabuleiro[horizontalLoop][verticalLoop] + " ");
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

                if (verticalLoop + 2 <= 6 && verticalLoop >= 0)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop + 2] == 0 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop  + 1] == 1)
                    {
                        return true;
                    }
                }

                if (verticalLoop - 2 >= 0 && verticalLoop - 2 >= 0)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 0 &&
                        vetorTabuleiro[horizontalLoop][verticalLoop - 2] == 1 &&
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

                        vetorTabuleiro[posicaoFinalCimaParaBaixo.horizontal][posicaoFinalCimaParaBaixo.vertical] = 1;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.horizontal][posicaoInicialCimaParaBaixo.vertical] = 0;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.horizontal + 1][posicaoInicialCimaParaBaixo.vertical] = 0;
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
                                    System.out.println("  -VALIDO  ");
                                    System.out.printf(" Finalizado");
                                    System.out.printf(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.printf(" Jogadas Realizadas : " + jogadasBemSucedidas);
                                    System.out.println(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.println(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.println("  ");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalCimaParaBaixo.horizontal][posicaoFinalCimaParaBaixo.vertical] = 0;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.horizontal][posicaoInicialCimaParaBaixo.vertical] = 0;
                        vetorTabuleiro[posicaoInicialCimaParaBaixo.horizontal + 1][posicaoInicialCimaParaBaixo.vertical] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }

                if (horizontalLoop - 2 >= 0)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop - 2][verticalLoop] == 0 &&
                        vetorTabuleiro[horizontalLoop - 1][verticalLoop] == 1)
                    {
                        Posicao posicaoInicialBaixoParaCima = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalBaixoParaCima = new Posicao((byte) (horizontalLoop - 2), verticalLoop);

                        vetorTabuleiro[posicaoFinalBaixoParaCima.horizontal][posicaoFinalBaixoParaCima.vertical] = 1;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.horizontal][posicaoInicialBaixoParaCima.vertical] = 0;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.horizontal + 1][posicaoInicialBaixoParaCima.vertical] = 0;
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
                                    System.out.println("  -VALIDO  ");
                                    System.out.printf(" Finalizado");
                                    System.out.printf(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.printf(" Jogadas Realizadas : " + jogadasBemSucedidas);
                                    System.out.printf(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.println(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.println("  ");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalBaixoParaCima.horizontal][posicaoFinalBaixoParaCima.vertical] = 0;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.horizontal][posicaoInicialBaixoParaCima.vertical] = 1;
                        vetorTabuleiro[posicaoInicialBaixoParaCima.horizontal + 1][posicaoInicialBaixoParaCima.vertical] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }
                if (verticalLoop + 2 <= 6)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop][verticalLoop  + 2] == 0 &&
                            vetorTabuleiro[horizontalLoop][verticalLoop - 1] == 1)
                    {
                        Posicao posicaoInicialEsquerdaParaDireita = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalEsquerdaParaDireita = new Posicao(horizontalLoop, (byte) (verticalLoop + 2));

                        vetorTabuleiro[posicaoFinalEsquerdaParaDireita.horizontal][posicaoFinalEsquerdaParaDireita.vertical] = 1;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.horizontal][posicaoInicialEsquerdaParaDireita.vertical] = 0;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.horizontal + 1][posicaoInicialEsquerdaParaDireita.vertical] = 0;
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
                                    System.out.println("  -VALIDO  ");
                                    System.out.print(" Finalizado");
                                    System.out.print(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.print(" Jogadas Realizadas : " + jogadasBemSucedidas);
                                    System.out.println(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.println(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.println("");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalEsquerdaParaDireita.horizontal][posicaoFinalEsquerdaParaDireita.vertical] = 0;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.horizontal][posicaoInicialEsquerdaParaDireita.vertical] = 1;
                        vetorTabuleiro[posicaoInicialEsquerdaParaDireita.horizontal][posicaoInicialEsquerdaParaDireita.vertical + 1] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }
                if (verticalLoop - 2 >= 0)
                {
                    if (vetorTabuleiro[horizontalLoop][verticalLoop] == 1 && vetorTabuleiro[horizontalLoop][verticalLoop - 1] == 1 &&
                            vetorTabuleiro[horizontalLoop][verticalLoop  - 2] == 0)
                    {
                        Posicao posicaoInicialDireitaParaEsquerda = new Posicao(horizontalLoop, verticalLoop);
                        Posicao posicaoFinalDireitaParaEsquerda = new Posicao(horizontalLoop, (byte) (verticalLoop - 2));

                        vetorTabuleiro[posicaoFinalDireitaParaEsquerda.horizontal][posicaoFinalDireitaParaEsquerda.vertical] = 1;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.horizontal][posicaoInicialDireitaParaEsquerda.vertical] = 0;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.horizontal + 1][posicaoInicialDireitaParaEsquerda.vertical] = 0;
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
                                    System.out.println("  -VALIDO  ");
                                    System.out.print(" Finalizado");
                                    System.out.print(" Jogadas Finais : " + jogadasBemSucedidas);
                                    System.out.print(" Jogadas Realizadas : " + jogadasBemSucedidas);
                                    System.out.print(" Resultado ");
                                    mostrarTabuleiro();
                                    System.out.println(" ");
                                    armazenaHistoricoJogadas.visualizaHistoricoJogadas();
                                    System.out.println(" ");
                                }
                            }
                        }
                        vetorTabuleiro[posicaoFinalDireitaParaEsquerda.horizontal][posicaoFinalDireitaParaEsquerda.vertical] = 0;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.horizontal][posicaoInicialDireitaParaEsquerda.vertical] = 0;
                        vetorTabuleiro[posicaoInicialDireitaParaEsquerda.horizontal][posicaoInicialDireitaParaEsquerda.vertical + 1] = 1;
                        this.pecas++;

                        armazenaHistoricoJogadas.removeJogada();
                    }
                }

            }
        }
    }

}
