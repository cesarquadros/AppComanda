package br.com.thiengo.tcmaterialdesign.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.thiengo.tcmaterialdesign.R;
import br.com.thiengo.tcmaterialdesign.domain.Produtos;

/**
 * Created by cquadros on 05/07/2016.
 */
public class ProdutoAdapter extends ArrayAdapter<Produtos> {

    private Context context;
    private ArrayList<Produtos> listaProdutos;

    public ProdutoAdapter(Context context, ArrayList<Produtos> listaProdutos){
        super(context, 0, listaProdutos);
        this.context = context;
        this.listaProdutos = listaProdutos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produtos itemPosicao = this.listaProdutos.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.produtos, null);
        TextView txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
        txtDescricao.setText(itemPosicao.getDescricao());
        TextView txtPreco = (TextView) convertView.findViewById(R.id.txtPreco);
        txtPreco.setText(String.valueOf(itemPosicao.getPreco()));

        return convertView;
    }
}
