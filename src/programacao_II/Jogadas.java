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

    public void setPosicaoOriginal (Posicao posicaoOriginal)
    {
        this.posicaoOriginal = posicaoOriginal;
    }

    public void setPosicaoPosJogada (Posicao posicaoPosJogada)
    {
        this.posicaoPosJogada = posicaoPosJogada;
    }
    public void setTipoMovimento (Movimentos tipoMovimento)
    {
        this.tipoMovimento = tipoMovimento;
    }

    public Posicao getPosicaoOriginal() { return posicaoOriginal; }
    public Posicao getPosicaoPosJogada() { return posicaoPosJogada; }
    public Movimentos getTipoMovimento() { return tipoMovimento; }

    public void mostraJogada()
    {
        System.out.println("Aqui: " + getPosicaoOriginal());
        System.out.println("Antes: " + getPosicaoPosJogada());
        System.out.println("depos: " + getTipoMovimento());


    }
}
