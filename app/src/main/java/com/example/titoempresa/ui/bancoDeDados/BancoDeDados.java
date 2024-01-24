package com.example.titoempresa.ui.bancoDeDados;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.titoempresa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BancoDeDados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BancoDeDados extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BancoDeDados() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BancoDeDados.
     */
    // TODO: Rename and change types and number of parameters
    public static BancoDeDados newInstance(String param1, String param2) {
        BancoDeDados fragment = new BancoDeDados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private TextView textNome2, textSobrenome2, textIdade;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banco_de_dados, container, false);
        BancoDeDados fragment = new BancoDeDados();
        textNome2       = view.findViewById(R.id.textNome2);
        textSobrenome2  = view.findViewById(R.id.textSobrenome2);
        textIdade       = view.findViewById(R.id.textIdade);

        Bundle dados = getArguments();

        if (dados != null) {
            String nome = dados.getString("nome");
            String sobrenome = dados.getString("sobrenome");
            String idade = dados.getString("idade");

            textNome2.setText(nome);
            textSobrenome2.setText(sobrenome);
            textIdade.setText(idade);

        }

        // Chame o método aqui para garantir que seja executado ao criar a visualização
        criarOuAbrirBancoDeDados();

        return view;

    }
    private TextView textTeste, textTesteTeste;

    private void criarOuAbrirBancoDeDados() {
        try {
            //cria o banco
            SQLiteDatabase bancoDados = getContext().openOrCreateDatabase("app", MODE_PRIVATE, null);

            //cria a tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, sobrenome VARCHAR, idade VARCHAR)");
            bancoDados.execSQL("DROP TABLE pessoas");


            //insere os dados
            bancoDados.execSQL("INSERT INTO pessoas(nome, sobrenome) VALUES (nome, sombrenome, idade)");

            //recuperando dados
            // o cursor é ultilizado para percorrer os dados

            String consulta = "SELECT * FROM pessoas ";
            //bancoDados.execSQL("UPDATE pessoas SET idade = 11 WHERE nome = 'Clara' ");

            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //recupera os indices da tabela
            int indiceId        = cursor.getColumnIndex("id");
            int indiceNome      = cursor.getColumnIndex("nome");
            int indiceSobrenome = cursor.getColumnIndex("sobrenome");
            int indiceIdade     = cursor.getColumnIndex("idade");

            //depois de ver tiodos ele para onde está, entao voltamos ele para o primeiro
            cursor.moveToFirst();

            while (cursor != null){
                String id         =  cursor.getString(indiceId);
                String nome       =  cursor.getString(indiceNome);
                String sobrenome  =  cursor.getString(indiceSobrenome);
                String idade      =  cursor.getString(indiceIdade);

                Log.e("RESULTADO - id ", id + " nome " + nome + " sobrenome " + sobrenome +" idade " + idade);

                cursor.moveToNext();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}