package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

public class Produto {
    protected int cod;
    protected String descricao;
    protected double precoCompra;
    protected double precoVenda;
    protected double lucro;
    protected int quant;
    protected int estoqueMinimo;
    protected Fornecedor fornecedor;

    public Produto(int cod, String desc, int min, double lucro){
        this.cod = cod;
        this.descricao = desc;
        this.precoCompra = 0;
        this.precoVenda = 0;
        this.lucro = lucro;
        this.quant = 0;
        this.estoqueMinimo = min;
        

    }

    //Método de compra
    public void compra(int quant2, double val){
        if (quant2 > 0 && val > 0){
            //Atualiza o preco de compra do produto
            this.precoCompra = (this.quant * this.precoCompra + quant2 * val) / (this.quant + quant2);
            //System.out.println(this.precoCompra);
            //Atualiza o preco de venda do produto
            this.precoVenda = (this.precoCompra * this.lucro) + this.precoCompra;
            //System.out.println(this.precoVenda);
            //Atualiza a quantidade de produtos
            this.quant += quant2;
        }
    }

    //Método de venda
    public double venda(int quant) throws ProdutoVencido{
        if (quant <= this.quant){
            this.quant -= quant;
            return quant * this.precoVenda;
        }
        return -1;
    }

    //Método para verificar se o produto está com estoque abaixo do mínimo
    public boolean abaixoDoMinimo(){
        if (this.quant < this.estoqueMinimo){
            return true;
        }else{
            return false;
        }
    }

    //Métodos Getters
    public int getCodigo() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public double getLucro() {
        return lucro;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public int getQuant() {
        return quant;
    }
    
    
}
