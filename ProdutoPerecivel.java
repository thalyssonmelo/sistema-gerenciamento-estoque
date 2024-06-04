package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

import java.util.ArrayList;
import java.util.Date;

public class ProdutoPerecivel extends Produto{
    private ArrayList<Lote> lotes = new ArrayList<Lote>();

    public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
        super(cod, desc, min, lucro);
    }

    //Método para compra
    public boolean compra(int quantidade, double val, Date data){
        if (quantidade > 0 && val > 0){
            if (data == null){
                return false;
            }
            //Verifica se o lote já está vencido
            Date hoje = new Date(); 
            if (data.before(hoje)){
                return false;
            }
            //Atualiza o preco de compra do produto
            super.precoCompra = (super.quant * super.precoCompra + quantidade * val) / (super.quant + quantidade);
            //Atualiza o preco de venda do produto
            super.precoVenda = (super.precoCompra * super.lucro) + super.precoCompra;
            //Atualiza a quantidade de produtos
            this.quant += quantidade;
            //Coloca um lote no produto
            Lote l = new Lote(quantidade, data);
            this.lotes.add(l);
            return true;
        }
        return false;
    }

    //Método para verificar se o produto é válido
    public boolean loteValido(Lote l){
        Date today = new Date();
        return l.getDataValidade().after(today);
    }

    //Método para verificar quantos produtos válidos há
    public int quantidadeProdutosValidos(){
        int quantidadeProdutos = 0;
        for (Lote l: this.lotes){
            if (loteValido(l) == true){
                quantidadeProdutos += l.getQuantidade();
            }
      
        }
        return quantidadeProdutos;
    }

    public int quantidadeProdutosVencidos(){
        int quantidadeProdutos = 0;
        for (Lote l: this.lotes){
            if (loteValido(l)==false){
                quantidadeProdutos += l.getQuantidade();
            }
        }
        return quantidadeProdutos;
    }

    //Método para verificar se o estoque tá abaixo do mínimo
    public boolean abaixoDoMinimo(){
        int quantidade = 0;
        for (Lote l: lotes){
            quantidade+= l.getQuantidade();
        }
        if (quantidade < super.estoqueMinimo){
            return true;
        }
        return false;
    }


    public boolean produtoVencido(){
        for (Lote l: lotes){
            if (loteValido(l)==false){
                return true;
            }
        }
        return false;
    }
    
    //Método de venda
    public double venda(int quant) throws ProdutoVencido{
        int  quantidadeProd = quant;
        if (quant <= quantidadeProdutosValidos() && quant > 0){
            for (Lote l: lotes){
                if (loteValido(l)){
                    if (quant == l.getQuantidade()){
                        quant = 0;
                        l.setQuantidade(0);
                    }else if (quant > l.getQuantidade() && l.getQuantidade() > 0){
                        quant -= l.getQuantidade();
                        l.setQuantidade(0);
                    }else{
                        l.setQuantidade(l.getQuantidade() - quant);
                        quant -= quant;
                        quant = 0;
                    }
                }
                if (quant == 0){
                    break;
                }
            }
            return quantidadeProd * super.getPrecoVenda();
        }
        throw new ProdutoVencido();
    }

    public ArrayList<Lote> getLotes() {
        return lotes;
    }

    
}
