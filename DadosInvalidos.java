package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

public class DadosInvalidos extends Exception{
    private String exc = new String();
    public DadosInvalidos(){
        exc = "Dados Inválidos!";
    }
    
}
