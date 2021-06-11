package programacao_II;

public class Posicao {

    byte horizontal;
    byte vertical;

    public Posicao() {}

    Posicao(byte horizontal, byte vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public void setHorizontal(byte horizontal)
    {
        this.horizontal = horizontal;
    }

    public void setVertical(byte vertical)
    {
        this.vertical = vertical;
    }


}
