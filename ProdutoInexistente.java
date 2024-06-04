package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

public class ProdutoInexistente extends Exception {
    String exc = new String();
    public ProdutoInexistente(){
        exc = "Produto inexistente!";
    }
    
}
