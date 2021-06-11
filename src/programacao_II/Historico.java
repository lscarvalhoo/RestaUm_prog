package programacao_II;

import java.util.Stack;

public class Historico {

    private Stack historicoJogadas = new Stack();

    Historico(){}

    public void adicionaJogadas(Jogadas jogadas)
    {
        this.historicoJogadas.push(jogadas);
    }

    public void removeJogada()
    {
        this.historicoJogadas.pop();
    }

    public void visualizaHistoricoJogadas() {
        Jogadas temporario;
        Stack pilhaTemporaria = new Stack();
        while (!historicoJogadas.empty())
        {
            pilhaTemporaria.push(historicoJogadas.pop());
        }
        while(!pilhaTemporaria.empty())
        {
            temporario = (Jogadas) pilhaTemporaria.pop();
            temporario.mostraJogada();
            System.out.println(" ");
            historicoJogadas.push(temporario);
            System.out.println(" ");
        }
    }
}
