package com.example.titoempresa.ui.anotacoes;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacoesPreferencias {
    private Context context;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "anotacao.preferencias";
    private SharedPreferences.Editor editor;
    private final String CHAVE_NOME = "nome";

    public AnotacoesPreferencias(Context C) {
        this.context    = C;
        preferences     = context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();

        //nesse constructor o editor é para salvar a anotaçao.
        //o preferences é para recuperar as anotacoes
    }

    public void salvarAnotacoes(String anotacao){
        editor.putString(CHAVE_NOME, anotacao);
        editor.apply();
    }
    public String recuperarAnotacao(){
        return preferences.getString(CHAVE_NOME, "") ;
    }
}
