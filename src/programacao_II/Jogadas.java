package programacao_II;

public class Jogadas
{
    Posicao posicaoOriginal = new Posicao();
    Posicao posicaoPosJogada = new Posicao();
    Movimentos tipoMovimento;

    public Jogadas(){}

    public Jogadas(Posicao posicaoOriginal, Posicao posicaoPosJogada, Movimentos tipoMovimento)
    {
        this.posicaoOriginal = posicaoOriginal;
        this.posicaoPosJogada = posicaoPosJogada;
        this.tipoMovimento = tipoMovimento;
    }

    public void mostraJogada()
    {

    }
}
