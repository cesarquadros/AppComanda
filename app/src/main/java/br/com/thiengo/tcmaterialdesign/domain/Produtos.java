package br.com.thiengo.tcmaterialdesign.domain;

/**
 * Created by cquadros on 05/07/2016.
 */
public class Produtos {

    private int codProduto;
    private String descricao;
    private float preco;

    public Produtos() {
    }

    public Produtos(int codProduto, String descricao, float preco) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produtos(String descricao, float preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
