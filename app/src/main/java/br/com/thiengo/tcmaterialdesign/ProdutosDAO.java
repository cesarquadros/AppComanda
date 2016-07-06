package br.com.thiengo.tcmaterialdesign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.tcmaterialdesign.domain.Produtos;

/**
 * Created by cquadros on 05/07/2016.
 */
public class ProdutosDAO extends Conexao{

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Produtos> listarItens(){
        ArrayList<Produtos> listaProdutos = new ArrayList<>();
        try {
            con = abreConexao();
            stmt = con.createStatement();
            sql = "SELECT COD_PRODUTO, DESCRICAO, PRECO FROM PRODUTOS WHERE COD_CATEGORIA = 4";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Produtos produtos = new Produtos(rs.getInt(1), rs.getString(2),rs.getFloat(3));
                listaProdutos.add(produtos);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }
}
