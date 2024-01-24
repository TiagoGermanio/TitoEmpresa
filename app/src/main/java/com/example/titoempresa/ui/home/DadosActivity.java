package com.example.titoempresa.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.titoempresa.R;

public class DadosActivity extends AppCompatActivity {
    private TextView textSobrenome, textNome;
    private Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        textNome = findViewById(R.id.textNome);
        textSobrenome = findViewById(R.id.textSobrenome);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        Bundle dados = getIntent().getExtras();

        if (dados != null) {
            String nome = dados.getString("nome");
            String sobrenome = dados.getString("sobrenome");

            textNome.setText(nome);
            textSobrenome.setText(sobrenome);

        }

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activityId, new HomeFragment())
                        .commit();
                        finish();
            }
        });
    }
}