package com.umc.iod.aulaiod.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umc.iod.aulaiod.R;

public class TelaContador extends AppCompatActivity {

    private int contador = 0;

    public void botaoContadorClick(View meuBotao) {
        Log.i(this.getClass().getName(), "Dentro do botaoContadorClick");
        contador++;
        atualizarTextoBotao();
    }

    public void atualizarTextoBotao() {
        Button meuBotao = (Button) findViewById(R.id.botaoContador);
        String textoBotao = getResources().getString(R.string.status_contador);
        meuBotao.setText(textoBotao + " " + contador);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contador);

        Log.i(this.getClass().getName(), "Dentro do onCreate");

        if (savedInstanceState != null) {
            contador = savedInstanceState.getInt("contadorBackup");
        }

        atualizarTextoBotao();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.i(this.getClass().getName(), "Dentro do onSaveInstanceState");
        bundle.putInt("contadorBackup", contador);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this.getClass().getName(), "Dentro do onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(this.getClass().getName(), "Dentro do onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this.getClass().getName(), "Dentro do onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(this.getClass().getName(), "Dentro do onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getClass().getName(), "Dentro do onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(this.getClass().getName(), "Dentro do onRestart");
    }
}