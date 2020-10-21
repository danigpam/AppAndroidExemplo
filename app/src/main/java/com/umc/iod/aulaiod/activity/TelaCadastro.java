package com.umc.iod.aulaiod.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.viewmodel.TelaCadastroViewModel;

public class TelaCadastro extends AppCompatActivity {

    private TelaCadastroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        Log.i(this.getClass().getName(), "Dentro do onCreate");

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory());
        viewModel = vmp.get(TelaCadastroViewModel.class);
        Log.d(this.getClass().getName(), "Associou a view model " + viewModel + " à atividade " + this);
    }

    public void botaoCadastrarClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoCadastrarClick");

        EditText campoEmail = findViewById(R.id.campoEmail);
        EditText campoSenha = findViewById(R.id.campoSenha);

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        Usuario usuario = new Usuario(email, email);
        viewModel.cadastrar(usuario);
    }
}