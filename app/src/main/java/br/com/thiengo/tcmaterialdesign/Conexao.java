package br.com.thiengo.tcmaterialdesign;

import android.annotation.TargetApi;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class Conexao {

    static Connection DbConn = null;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public Connection abreConexao() throws SQLException {
        try {
            // SET CONNECTIONSTRING
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String iip = MainActivity.ipConexao;
            String username = MainActivity.usuarioBanco;
            String password = MainActivity.senhaBanco;
            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+iip+"/COMANDA;user=" + username + ";password=" + password);
            //Toast.makeText(Conexao.this, "Conectado", Toast.LENGTH_LONG).show();
            Log.w("Connection", "open");
            Statement stmt = DbConn.createStatement();
            return DbConn;
        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }
        return DbConn;
    }

    public static void fecharConexao(){
        try {
            DbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
