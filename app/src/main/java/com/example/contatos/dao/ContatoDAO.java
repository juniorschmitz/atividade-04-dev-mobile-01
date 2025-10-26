package com.example.contatos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.contatos.model.Contato;
import com.example.contatos.sqlite.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private final SQLiteDatabase db;

    public ContatoDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void inserirContato(Contato contato) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NOME, contato.getNome());

        db.insert(DbHelper.TABLE_CONTATOS_NAME, null, values);
    }

    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = db.query(DbHelper.TABLE_CONTATOS_NAME,
                new String[] {DbHelper.COLUMN_ID, DbHelper.COLUMN_NOME},
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);

                Contato contato = new Contato(id, nome);
                contatos.add(contato);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return contatos;
    }

    public long inserirContatoReturnId(Contato contato) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NOME, contato.getNome());
        return db.insert(DbHelper.TABLE_CONTATOS_NAME, null, values); // -1 em erro
    }
}
