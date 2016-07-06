package br.com.thiengo.tcmaterialdesign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


    public boolean abrirComanda(Comanda comanda) {
        try {
            con = abreConexao();
            sql = "INSERT INTO COMANDA(NOME_CLIENTE, DATA_INICIO, STATUS) VALUES('"+comanda.getNome()+"','"+comanda.getData()+"','"+comanda.getStatus()+"')";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            return true;
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
            sql = "SELECT COD_COMANDA, NOME_CLIENTE FROM COMANDA";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Comanda comanda = new Comanda(rs.getInt(1),rs.getString(2));
                listComandas.add(comanda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listComandas;
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

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
