package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

import java.util.Date;
public class Lote {
    private int quantidade;
    private Date dataValidade;

    public Lote(int quantidade, Date dataValidade) {
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

