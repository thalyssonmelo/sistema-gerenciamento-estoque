package thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao;

import java.util.ArrayList;
import java.util.Date;

import thalyssonAraujoMelo.estoqueProdutoPerecivelComExcessao.Produto;

public class Estoque implements InterfaceEstoqueComExcecoes{
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    //private Produto[] produtos = new Produto[100];
    private int quant = 0;

    //Método auxiliar para pesquisar produtos
    public Produto pesquisar(int cod) throws ProdutoInexistente{
        for (Produto p: produtos){
            if (p.getCodigo() == cod){
                return p;
            }
        }
        throw new ProdutoInexistente();
    }
    
    public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos{
        if (p == null){
        	throw new DadosInvalidos();
        }
        //if (p.getFornecedor()==null){
       // 	throw new DadosInvalidos();
        //}
        if (p == null || p.getCodigo() <= 0 || p.getDescricao() == null || p.getDescricao().trim().isEmpty() || p.getEstoqueMinimo() <= 0 || p.getLucro() <=0){
        	throw new DadosInvalidos();
        }
        try{
            Produto prod = this.pesquisar(p.getCodigo());
            throw new ProdutoJaCadastrado(p.getCodigo());
        } catch (ProdutoInexistente e){
            this.produtos.add(p);
            //this.produtos[this.quant] = p;
            this.quant++;
        }
    }
    
    //Método para Incluir produtos
    /*public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos{
        if (p == null || p.getDescricao() == null || p.getCodigo() < 0 || p.getFornecedor().getCnpj() < 0 || p.getFornecedor().getCnpj() == 0 || p.getDescricao().trim().isEmpty()){
            throw new DadosInvalidos();
        }
        try{
            Produto prod = this.pesquisar(p.getCodigo());
            throw new ProdutoJaCadastrado(p.getCodigo());
        } catch (ProdutoInexistente e){
            this.produtos.add(p);
            //this.produtos[this.quant] = p;
            this.quant++;
        }
    }
    */    

    ///Método para ver a quantidade de produtos
    public int quantidade(int cod) throws ProdutoInexistente{
        try{
            Produto prod = pesquisar(cod);
            return prod.getQuant();
        }catch (ProdutoInexistente e){
            return 0;
        }
    }

    //Método para vender um produto
    public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos{
        Produto prod = pesquisar(cod);
        if (quant <= 0 ){
            throw new DadosInvalidos();
        }
        if (prod instanceof ProdutoPerecivel){
            return ((ProdutoPerecivel)prod).venda(quant);
        }
        if (prod.getQuant() - quant < prod.estoqueMinimo ){
            throw new DadosInvalidos();
        }
        
        return prod.venda(quant);
    }

    //Método para comprar um produto
    public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, ProdutoNaoPerecivel, DadosInvalidos {
        if (cod > 0 && quant > 0 && preco > 0){
            Produto prod = pesquisar(cod);
            if (val == null){
                prod.compra(quant, preco);
            }else{
                if (prod instanceof ProdutoPerecivel){
                    ((ProdutoPerecivel)prod).compra(quant, preco, val);
                } else{
                    throw new ProdutoNaoPerecivel();
                }
            }
        }else{
            throw new DadosInvalidos();
        }
    }

    //Método para pesquisar um fornecedor
    public Fornecedor fornecedor(int cod) throws ProdutoInexistente{
        Produto prod = pesquisar(cod);
        if (prod != null){
            return prod.getFornecedor();
        }
        return null;
    }

    public ArrayList<Produto> estoqueAbaixoDoMinimo(){
        ArrayList<Produto> abaixoDoMinimo = new ArrayList<Produto>(); 
        for (Produto p: produtos){
            if (p instanceof ProdutoPerecivel){
                if (((ProdutoPerecivel)p).abaixoDoMinimo()){
                    abaixoDoMinimo.add(p);
                }
            }else if (p instanceof Produto){
                if (p.abaixoDoMinimo()){
                    abaixoDoMinimo.add(p);
                }
            }
        }
        return abaixoDoMinimo;
    }

    public ArrayList<Produto> estoqueVencido(){
        ArrayList<Produto> vencidos = new ArrayList<Produto>();
        for (Produto  p: produtos){
            if (p instanceof ProdutoPerecivel){
                if (((ProdutoPerecivel)p).produtoVencido()){
                    vencidos.add(p);
                }
            }
        }
        return vencidos;
    }

    public int quantidadeVencidos(int cod) throws ProdutoInexistente {
        Produto prod = pesquisar(cod);
        return ((ProdutoPerecivel)prod).quantidadeProdutosVencidos();
    }
    
}
