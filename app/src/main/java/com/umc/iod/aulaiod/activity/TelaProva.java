package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.umc.iod.aulaiod.R;

public class TelaProva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_prova);
    }

    public void botaoClique(View view) {

        EditText campoTexto = findViewById(R.id.campoTexto);
        String textoDigitado = campoTexto.getText().toString();

        Button botaoTexto = findViewById(R.id.botaoCopiaTexto);
        botaoTexto.setText(textoDigitado);

        campoTexto.setText("");
    }
}