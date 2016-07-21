package br.com.thiengo.tcmaterialdesign.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.com.thiengo.tcmaterialdesign.ComandaDao;
import br.com.thiengo.tcmaterialdesign.MainActivity;
import br.com.thiengo.tcmaterialdesign.R;
import br.com.thiengo.tcmaterialdesign.adapters.ComandaAdapter;
import br.com.thiengo.tcmaterialdesign.domain.Comanda;
import br.com.thiengo.tcmaterialdesign.interfaces.RecyclerViewOnClickListenerHack;

public class ComandaFragment extends Fragment implements RecyclerViewOnClickListenerHack,SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<Comanda> mList;
    public static String codComanda;
    public static String nomeComanda;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_car, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //llm.setReverseLayout(true);
        mRecyclerView.setLayoutManager(llm);


        mList = ((MainActivity) getActivity()).getSetCarList();
        ComandaAdapter adapter = new ComandaAdapter(getActivity(), mList);
        //adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onRefresh(){
        swipeRefreshLayout.setRefreshing(true);
        Toast.makeText(getActivity(), "Comandas atualizadas", Toast.LENGTH_SHORT).show();
        refreshList();
    }
   public void refreshList(){
        //do processing to get new data and set your listview's adapter, maybe  reinitialise the loaders you may be using or so
        //when your data has finished loading, cset the refresh state of the view to false
       atualizarComanda();
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onClickListener(View view, int position) {
        //Toast.makeText(getActivity(), mList.get(position).getNome() + " " + mList.get(position).getCodComanda(), Toast.LENGTH_SHORT).show();
        nomeComanda = mList.get(position).getNome();
        codComanda = mList.get(position).getCodComanda();
        //mList.get(position).getNome();
        Intent intent = new Intent(getActivity(), ProdutosComandaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLongPressClickListener(View view, int position) {


        //Toast.makeText(getActivity(), "onLongPressClickListener(): " + position, Toast.LENGTH_SHORT).show();
        codComanda = mList.get(position).getCodComanda();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Itens da comanda: " + codComanda);
        //adb.setIcon(android.R.drawable.ic_dialog_alert);
        ComandaDao comandaDao = new ComandaDao();
        String itens = "";
        try {
            itens = comandaDao.comprovante(Integer.parseInt(codComanda));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adb.setMessage(itens);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
       /* adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });*/
        adb.show();
    }


    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                                rv.getChildPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv,
                                rv.getChildPosition(cv));
                    }

                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public void atualizarComanda(){
        ComandaFragment frag = (ComandaFragment) getFragmentManager().findFragmentByTag("mainFrag");
        frag = new ComandaFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
        ft.commit();
    }
}
