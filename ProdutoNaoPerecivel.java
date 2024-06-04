package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

public class ProdutoNaoPerecivel extends Exception {
    String exc;
    public ProdutoNaoPerecivel(){
        exc = "Prudot não perecivel!";
    }
    
}
