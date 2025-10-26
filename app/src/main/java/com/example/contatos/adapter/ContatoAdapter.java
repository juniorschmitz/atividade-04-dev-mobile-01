package com.example.contatos.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contatos.R;
import com.example.contatos.model.Contato;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {
    private final List<Contato> contatos;
    private final Activity activity;

    public ContatoAdapter(List<Contato> contatos, Activity activity) {
        this.contatos = contatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = activity.getLayoutInflater().inflate(R.layout.lista_contato, viewGroup, false);
        Contato contato = contatos.get(position);

        TextView tvNomeContatoLista = view.findViewById(R.id.tvNomeContatoLista);
        tvNomeContatoLista.setText(contato.getNome());

        return view;
    }
}
