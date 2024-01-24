package com.example.titoempresa.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.titoempresa.R;
import com.example.titoempresa.databinding.FragmentHomeBinding;
import com.example.titoempresa.ui.bancoDeDados.BancoDeDados;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button buttonEnviar;
    private TextInputEditText editNome, editSobrenome, editIdade;
    private TextView textResultadoTeste;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Agora pode acessar os elementos da interface diretamente usando o binding
        buttonEnviar        = binding.buttonEnviar;
        editNome            = binding.editNome;
        editSobrenome       = binding.editSobrenome;
        editIdade           = binding.editIdade;
        textResultadoTeste  = binding.textResultadoTeste;


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //arquivos para salvar coisas no proprio celular. e para salvar informacoes do celular.
                SharedPreferences preferences = getActivity().getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //validar o nome:
                if(editNome.getText().toString().equals("") && editSobrenome.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "preencha o nome e o sobrenome", Toast.LENGTH_LONG).show();

                }else{
                    String nome         = editNome.getText().toString();
                    String sobrenome    = editSobrenome.getText().toString();
                    String idade        = editIdade.getText().toString();

                    editor.putString("nome", nome );
                    editor.putString("sobrenome", sobrenome );
                    editor.putString("idade", idade );
                    editor.commit();


                    Intent intent = new Intent(getContext(), BancoDeDados.class);

                    intent.putExtra("nome", nome);
                    intent.putExtra("sobrenome", sobrenome);
                    intent.putExtra("idade", idade);
                    startActivity(intent);

                }

            }
        });
        //recuperar dados.
        SharedPreferences preferences = getActivity().getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        // validar se temos um arquivos o nome em preferencias.

        if( preferences.contains("nome")){
            String nome         = preferences.getString("nome" , "usuario nao definido");
            String sobrenome    = preferences.getString("sobrenome" , "usuario nao definido");
            String idade        = preferences.getString("idade" , "usuario nao definido");



        }else{
            textResultadoTeste.setText("Usuario nao definido");


        }
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}