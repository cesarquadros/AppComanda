package br.com.thiengo.tcmaterialdesign;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.thiengo.tcmaterialdesign.domain.Comanda;
import br.com.thiengo.tcmaterialdesign.fragments.ComandaFragment;
import br.com.thiengo.tcmaterialdesign.fragments.ProdutosComandaActivity;

public class MainActivity extends ActionBarActivity {
    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    public static String curDate;
    private ComandaDao comandaDao = new ComandaDao();
    public static String ipConexao = "192.168.0.10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        curDate = dataFormat.format(new Date());

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Bar do Bugao");
        mToolbar.setSubtitle("Comandas em aberto");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);


        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(MainActivity.this);
                mensagem.setTitle("Bar do Bugão");
                mensagem.setMessage(curDate + "\nDigite o nome:");
                // DECLARACAO DO EDITTEXT
                final EditText input = new EditText(MainActivity.this);
                mensagem.setView(input);
                mensagem.setNeutralButton("Abrir comanda", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(getApplicationContext(), input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        Comanda comanda = new Comanda();
                        comanda.setNome(input.getText().toString());
                        comanda.setStatus("ABERTO");
                        comanda.setData(curDate);
                        boolean abrirComanda = comandaDao.abrirComanda(comanda);
                        if(abrirComanda){
                            Toast.makeText(getApplicationContext(),"Comanda aberta para "+ input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                            // FRAGMENT
                            atualizarComanda();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao abrir a comanda", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mensagem.show();
                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

        // FRAGMENT
        ComandaFragment frag = (ComandaFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if (frag == null) {
            frag = new ComandaFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_second_activity) {
            startActivity(new Intent(this, SecondActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Comanda> getSetCarList() {
        ComandaDao comandaDao = new ComandaDao();
        Conexao conexao = new Conexao();
        try {
            if(conexao.abreConexao()==null){
                Comanda comanda = new Comanda();
                List<Comanda> list = new ArrayList<>();

                final AlertDialog.Builder mensagem = new AlertDialog.Builder(MainActivity.this);
                mensagem.setTitle("Bar do Bugão");
                mensagem.setMessage("Erro ao conectar, digite o ip do servidor:");
                final EditText input = new EditText(MainActivity.this);
                input.addTextChangedListener(Mask.insert("###.###.###.###",input));
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                mensagem.setView(input);
                mensagem.setNeutralButton("Reconectar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(getApplicationContext(), input.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        ipConexao = input.getText().toString();
                        ComandaFragment frag = (ComandaFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
                            frag = new ComandaFragment();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                            ft.commit();
                    }
                });
                mensagem.show();
                return list;
            }else{
                return (comandaDao.comandasAbertas());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (comandaDao.comandasAbertas());
    }

    public void atualizarComanda(){
        ComandaFragment frag = (ComandaFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        frag = new ComandaFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
        ft.commit();
    }
}
