package br.com.thiengo.tcmaterialdesign.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import br.com.thiengo.tcmaterialdesign.MainActivity;
import br.com.thiengo.tcmaterialdesign.ProdutosDAO;
import br.com.thiengo.tcmaterialdesign.R;
import br.com.thiengo.tcmaterialdesign.adapters.ProdutoAdapter;
import br.com.thiengo.tcmaterialdesign.domain.Comanda;
import br.com.thiengo.tcmaterialdesign.domain.Produtos;

public class ProdutosComandaActivity extends AppCompatActivity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    ListView listProdutos;
    private static Comanda comanda;
    private static ArrayList<Produtos> arrayProd;
    private static boolean controle = true;
    private static int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_comanda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listProdutos = (ListView) findViewById(R.id.listProdutos);


       /* ArrayList<Produtos> listaProdutos = intemComandaDAO.listarItens();
        ProdutoAdapter produtoAdapter = new ProdutoAdapter(this, listaProdutos);
        listProdutos.setAdapter(produtoAdapter);*/


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new

                SectionsPagerAdapter(getSupportFragmentManager()

        );

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager)

                findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itens_comanda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
     public void onStart() {
         super.onStart();

         // ATTENTION: This was auto-generated to implement the App Indexing API.
         // See https://g.co/AppIndexing/AndroidStudio for more information.
         client.connect();
         Action viewAction = Action.newAction(
                 Action.TYPE_VIEW, // TODO: choose an action type.
                 "ItensComanda Page", // TODO: Define a title for the content shown.
                 // TODO: If you have web page content that matches this app activity's content,
                 // make sure this auto-generated web page URL is correct.
                 // Otherwise, set the URL to null.
                 Uri.parse("http://host/path"),
                 // TODO: Make sure this auto-generated app URL is correct.
                 Uri.parse("android-app://br.com.thiengo.tcmaterialdesign.fragments/http/host/path")
         );
    //        AppIndex.AppIndexApi.start(client, viewAction);
     }

     @Override
     public void onStop() {
         super.onStop();

         // ATTENTION: This was auto-generated to implement the App Indexing API.
         // See https://g.co/AppIndexing/AndroidStudio for more information.
         Action viewAction = Action.newAction(
                 Action.TYPE_VIEW, // TODO: choose an action type.
                 "ItensComanda Page", // TODO: Define a title for the content shown.
                 // TODO: If you have web page content that matches this app activity's content,
                 // make sure this auto-generated web page URL is correct.
                 // Otherwise, set the URL to null.
                 Uri.parse("http://host/path"),
                 // TODO: Make sure this auto-generated app URL is correct.
                 Uri.parse("android-app://br.com.thiengo.tcmaterialdesign.fragments/http/host/path")
         );
         AppIndex.AppIndexApi.end(client, viewAction);
         client.disconnect();
     }*/

     /**
      * A placeholder fragment containing a simple view.
      */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

                View rootView = inflater.inflate(R.layout.fragment_produtos_comanda, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText("Nome: " + ComandaFragment.nomeComanda);
                final String codComanda = ComandaFragment.codComanda;
                int aba = 0;
                ProdutosDAO itemComandaDAO = new ProdutosDAO();
           //if (contador == 1) {
               aba = getArguments().getInt(ARG_SECTION_NUMBER);
               contador = 0;
         //  }else{
         //      aba = getArguments().getInt(ARG_SECTION_NUMBER);
               contador++;
        //   }
                if (aba == 1) {
                    arrayProd = itemComandaDAO.listarItens(1);

                } else if (aba == 2) {
                    arrayProd = itemComandaDAO.listarItens(2);

                } else if (aba == 3) {
                    arrayProd = itemComandaDAO.listarItens(3);

                } else if (aba == 4) {
                    arrayProd = itemComandaDAO.listarItens(4);
                }

                final ListView listProdutos = (ListView) rootView.findViewById(R.id.listProdutos);
                ProdutoAdapter produtoAdapter = new ProdutoAdapter(getActivity(), arrayProd);
                listProdutos.setAdapter(produtoAdapter);
                listProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // ListView Clicked item index
                        int itemPosition = arrayProd.get(position).getCodProduto();
                        // Show Alert
                        Toast.makeText(getActivity(), "Codigo :" + itemPosition + "  Nome " + ComandaFragment.nomeComanda, Toast.LENGTH_LONG).show();

                   /* final AlertDialog.Builder mensagem = new AlertDialog.Builder(getActivity());
                    mensagem.setTitle("Bar do Bugão \ncomanda: " + codComanda);
                    mensagem.setMessage("Digite a Quantidade:");
                    // DECLARACAO DO EDITTEXT
                    final EditText input = new EditText(getActivity());
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mensagem.setView(input);
                    mensagem.setNeutralButton("Incluir item a comanda", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Toast.makeText(getApplicationContext(), input.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    mensagem.show();*/
                    }
                });

                return rootView;

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position+1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Cerveja";
                case 1:
                    return "Bebidas";
                case 2:
                    return "Caldos";
                case 3:
                    return "Outros";
            }
            return null;
        }
    }

}
