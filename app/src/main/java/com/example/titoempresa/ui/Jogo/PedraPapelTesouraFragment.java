package com.example.titoempresa.ui.Jogo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.titoempresa.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PedraPapelTesouraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedraPapelTesouraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PedraPapelTesouraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedraPapelTesouraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PedraPapelTesouraFragment newInstance(String param1, String param2) {
        PedraPapelTesouraFragment fragment = new PedraPapelTesouraFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // Inflate the layout for this fragmen
        rootView = inflater.inflate(R.layout.fragment_pedra_papel_tesoura, container, false);
        return rootView;
    }
    private View rootView;
    public void selectPedra(View view){
        this.opSelct("pedra");
    }
    public void selectTesoura(View view){
        this.opSelct("tesoura");
    }
    public void selectPapel(View view){
        this.opSelct("papel");
    }
    public void opSelct( String opSelct){

        TextView textResultado  = rootView.findViewById(R.id.textResultado);
        ImageView imageResume   = rootView.findViewById(R.id.imagemAdversario);
        ImageView imageSelect   = rootView.findViewById(R.id.imagemEscolhida);

        int sorteio = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String selecAuto = opcoes[sorteio];

        switch (opSelct){
            case "pedra":
                imageSelect.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageSelect.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageSelect.setImageResource(R.drawable.tesoura);
                break;
        }

        switch (selecAuto){
            case "pedra":
                imageResume.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResume.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResume.setImageResource(R.drawable.tesoura);
                break;
        }
        if(
                (selecAuto == "pedra"   && opSelct =="tesoura") ||
                        (selecAuto == "papel"   && opSelct =="pedra")   ||
                        (selecAuto == "tesoura" && opSelct =="papel")
        ){
            textResultado.setText("Você perdeu!");



        }else if((opSelct == "pedra"   && selecAuto =="tesoura") ||
                (opSelct == "papel"   && selecAuto =="pedra")   ||
                (opSelct == "tesoura" && selecAuto =="papel")){

            textResultado.setText("parabéns você, venceu!");

        }else{
            textResultado.setText("EMPATEEE!");

        }

    }
}