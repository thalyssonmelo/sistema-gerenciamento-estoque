package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

public class ProdutoJaCadastrado extends Exception {
    private String exc = new String();
    
    public ProdutoJaCadastrado(int cod){
        exc = "Produto com código " + cod + " Já cadastrado!";
    }
}
