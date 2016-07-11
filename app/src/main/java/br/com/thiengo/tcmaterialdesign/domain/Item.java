package br.com.thiengo.tcmaterialdesign.domain;

/**
 * Created by cquadros on 11/07/2016.
 */
public class Item {

    private int codProduto;
    private int codComanda;

    public Item(int codProduto, int codComanda) {
        this.codProduto = codProduto;
        this.codComanda = codComanda;
    }

    public Item() {
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getCodComanda() {
        return codComanda;
    }

    public void setCodComanda(int codComanda) {
        this.codComanda = codComanda;
    }
}
