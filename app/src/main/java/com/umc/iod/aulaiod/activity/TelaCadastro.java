package com.umc.iod.aulaiod.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()));
        viewModel = vmp.get(TelaCadastroViewModel.class);
        Log.d(this.getClass().getName(), "Associou a view model " + viewModel + " à atividade " + this);

        viewModel.getMensagemErroId().observe(this, observadorMensagemErro);
        viewModel.getUsuarioCadastrado().observe(this, observadorUsuarioCadastrado);
    }

    public void botaoCadastrarClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoCadastrarClick");

        EditText campoEmail = findViewById(R.id.campoEmail);
        EditText campoSenha = findViewById(R.id.campoSenha);

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        Usuario usuario = new Usuario(email, email);
        viewModel.validarCadastro(usuario);
        viewModel.cadastrar(usuario);
    }

    private Observer<Integer> observadorMensagemErro = new Observer<Integer>() {
        @Override
        public void onChanged(Integer mensagemErroId) {
            Log.d(getClass().getName(), "Dentro do observadorMensagemErro, houve mudança no live data");

            TextView mensagemErro = findViewById(R.id.mensagemErro);

            if (mensagemErroId != null) {
                mensagemErro.setText(getResources().getText(mensagemErroId));
                mensagemErro.setVisibility(TextView.VISIBLE);
            } else {
                mensagemErro.setText("");
                mensagemErro.setVisibility(TextView.VISIBLE);
            }
        }
    };

    private Observer<Usuario> observadorUsuarioCadastrado = new Observer<Usuario>() {
        @Override
        public void onChanged(Usuario usuario) {
            if (usuario != null) {
                Log.d(getClass().getName(), "Dentro do observadorUsuarioCadastrado, houve mudança no live data, o id é " + usuario.getId());

                Intent intencao = new Intent();
                intencao.setClass(getApplicationContext(), TelaFeed.class);
                startActivity(intencao);
            }
        }
    };
}