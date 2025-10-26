package com.example.contatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contatos.adapter.ContatoAdapter;
import com.example.contatos.dao.ContatoDAO;
import com.example.contatos.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadContatos();

        Button btnIrCadastro = findViewById(R.id.btnIrCadastro);
        btnIrCadastro.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent cadastroContatoView = new Intent (MainActivity.this, CadastroContato.class);
                 startActivity(cadastroContatoView);
                 finish();
             }
         });
    }

    // db browser for sqlite
    // view -> tools -> device explorer
    // data -> data -> com.example.contatos -> databases -> contatos_db
    private void loadContatos() {
        ContatoDAO contatoDAO = new ContatoDAO(this);

//        Contato c1 = new Contato( 1, "Contato 01");
//        Contato c2 = new Contato(2, "Contato 02");
//        Contato c3 = new Contato(3, "Contato 03");
//        contatoDAO.inserirContato(c1);
//        contatoDAO.inserirContato(c2);
//        contatoDAO.inserirContato(c3);

        List<Contato> contatos = contatoDAO.listarContatos();

//        List<Contato> contatos = fakeContatos();
        ContatoAdapter contatoAdapter = new ContatoAdapter(contatos, this);

        ListView lvContatos = findViewById(R.id.lvContatos);
        lvContatos.setAdapter(contatoAdapter);
    }

    private List<Contato> fakeContatos() {
        Contato c1 = new Contato( 1, "Contato 01");
        Contato c2 = new Contato(2, "Contato 02");
        Contato c3 = new Contato(3, "Contato 03");

        List<Contato> contatos = new ArrayList<>();
        contatos.add(c1);
        contatos.add(c2);
        contatos.add(c3);
        return contatos;
    }
}