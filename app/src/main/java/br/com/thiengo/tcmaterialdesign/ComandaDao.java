package br.com.thiengo.tcmaterialdesign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.tcmaterialdesign.domain.Comanda;

/**
 * Created by cquadros on 30/06/2016.
 */
public class ComandaDao extends Conexao {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = null;
    private DecimalFormat df = new DecimalFormat("0.00");


    public boolean abrirComanda(Comanda comanda) {
        try {
            con = abreConexao();
            sql = "INSERT INTO COMANDA(NOME_CLIENTE, DATA_INICIO, STATUS) VALUES('"+comanda.getNome()+"','"+comanda.getData()+"','"+comanda.getStatus()+"')";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comanda> comandasAbertas(){
        List<Comanda> listComandas = new ArrayList<>();
        try {
            con = abreConexao();
            stmt = con.createStatement();
            sql = "SELECT COD_COMANDA, NOME_CLIENTE FROM COMANDA WHERE STATUS = 'ABERTO'";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Comanda comanda = new Comanda(rs.getInt(1),rs.getString(2));
                listComandas.add(comanda);
            }
            con.close();
            return listComandas;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return listComandas;
        }

    }

    public boolean apagarComanda(String codComanda){

        try {
            con = abreConexao();
            stmt = con.createStatement();

            sql = "DELETE FROM PAGAMENTOS WHERE COD_COMANDA = '"+codComanda+"'";
            stmt.executeUpdate(sql);

            sql = "DELETE FROM ITENS_COMANDA WHERE COD_COMANDA = '"+codComanda+"'";
            stmt.executeUpdate(sql);

            sql = "DELETE FROM COMANDA WHERE COD_COMANDA = '"+codComanda+"'";
            stmt.executeUpdate(sql);
            con.close();
            return true;
        } catch (SQLException e) {
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    public String comprovante(int codComanda) throws SQLException{
        String itens = "";
        float valorTotal = 0;
        try {
            con = abreConexao();
            stmt = con.createStatement();

            sql = "SELECT"+
                    " P.DESCRICAO,P.PRECO, COUNT(*) AS CONT"+
                    " FROM " +
                    " ITENS_COMANDA IC, PRODUTOS P, CATEGORIAS CA"+
                    " WHERE"+
                    " P.COD_PRODUTO = IC.COD_PRODUTO"+
                    " AND"+
                    " P.COD_CATEGORIA = CA.COD_CATEGORIA"+
                    " AND"+
                    " IC.COD_COMANDA = '"+codComanda+"'"+
                    " GROUP BY"+
                    " P.DESCRICAO, P.PRECO";
            rs = stmt.executeQuery(sql);

            int i =0;
            while(rs.next()){
                itens += "\n"+ rs.getString(1) + "   x"+ rs.getInt(3);
                float valor = rs.getFloat(2)*rs.getInt(3);
                valorTotal += valor;
            }
            itens += "\n \n VALOR TOTAL: "+df.format(valorTotal);
                return itens;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            con.close();
        }
        con.close();
        return itens;
    }
}
