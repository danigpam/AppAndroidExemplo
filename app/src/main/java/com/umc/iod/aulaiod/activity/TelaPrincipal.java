package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.umc.iod.aulaiod.R;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        Log.i(this.getClass().getName(), "Dentro do onCreate");
    }

    public void botaoCadastroClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoCadastroClick");
        Intent intencao = new Intent();
        intencao.setClass(getApplicationContext(), TelaCadastro.class);
        startActivity(intencao);
    }

    public void botaoLoginClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoLoginClick");
        Intent intencao = new Intent(getApplicationContext(),TelaLogin.class);
        startActivity(intencao);
    }

    public void botaoContadorClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoContadorClick");
        Intent intencao = new Intent();

        intencao.setAction(Intent.ACTION_MAIN); //<-- intent implicito
        intencao.addCategory(Intent.CATEGORY_APP_CALCULATOR);

        if (intencao.resolveActivity(getPackageManager()) != null) {
            Log.i(getClass().getName(), "Conseguimos resolver o intent implicito");
            startActivity(intencao);
        } else {
            Log.w(getClass().getName(), "Não conseguimos resolver o intent implicito");
        }
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