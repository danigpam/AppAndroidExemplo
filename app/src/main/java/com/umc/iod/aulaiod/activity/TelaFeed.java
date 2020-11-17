package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.viewmodel.TelaFeedViewModel;

import java.util.List;

public class TelaFeed extends AppCompatActivity {

    private TelaFeedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feed);

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()));
        viewModel = vmp.get(TelaFeedViewModel.class);

        long id = getIntent().getLongExtra("usuarioId", 0);
        Log.i(getClass().getName(), "Dentro do onCreate, recebemos o id do usuario = " + id);

        viewModel.carregarUsuarioLogado(id);
        viewModel.getUsuarioLogado().observe(this, observadorUsuarioLogado);
        viewModel.getPosts().observe(this, observadorPosts);
    }

    public void avisoEmailNaoValidadoClick(View view) {
        Log.i(getClass().getName(), "Dentro do avisoEmailNaoValidadoClick, vai sincronizar");
        viewModel.sincronizarUsuario();
    }

    private Observer<Usuario> observadorUsuarioLogado = new Observer<Usuario>() {
        @Override
        public void onChanged(Usuario usuario) {
            if (usuario == null) {
                Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - usuario é NULL");
                Intent intencao = new Intent();
                intencao.setClass(getApplicationContext(), TelaLogin.class);
                startActivity(intencao);
            } else {
                Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - o usuario não é null, o email é " + usuario.getEmail());
                if (usuario.isSincronizado()) {
                    Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - está sincronizado");
                    TextView aviso = findViewById(R.id.avisoEmailNaoValidado);
                    aviso.setVisibility(TextView.GONE);
                } else {
                    Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - nao está sincronizado");
                    TextView aviso = findViewById(R.id.avisoEmailNaoValidado);
                    aviso.setVisibility(TextView.VISIBLE);
                }
            }
        }
    };

    private Observer<List<Postagem>> observadorPosts = new Observer<List<Postagem>>() {
        @Override
        public void onChanged(List<Postagem> listaPosts) {
            if (listaPosts != null) {
                for (Postagem postagem : listaPosts) {
                    LinearLayout layout = findViewById(R.id.postsLinearLayout);

                    TextView textViewPost = new TextView(getApplicationContext());
                    textViewPost.setText(postagem.getTitulo() + "\r\n" + postagem.getTexto() + "\r\n\r\n");

                    layout.addView(textViewPost);
                }
            }
        }
    };
}