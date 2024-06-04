package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

public class ProdutoVencido extends Exception {
    String exc;
    public ProdutoVencido(){
        exc = "Produto vencido!";
    }
    
}
