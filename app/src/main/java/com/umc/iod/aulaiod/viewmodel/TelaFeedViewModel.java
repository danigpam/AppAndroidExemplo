package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.PostagemRepository;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.repository.local.LocalDatabase;
import com.umc.iod.aulaiod.util.ThreadManager;

import java.util.List;

public class TelaFeedViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;
    private PostagemRepository postagemRepository;

    private LiveData<Usuario> usuarioLogado;

    public TelaFeedViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
        postagemRepository = new PostagemRepository();
    }

    public LiveData<List<Postagem>> getPosts() {
        return postagemRepository.getListaPostagem();
    }

    public void carregarUsuarioLogado(long id) {
        usuarioLogado = usuarioRepository.pesquisarPorIdLive(id);
    }

    public LiveData<Usuario> getUsuarioLogado() {
        return usuarioLogado;
    }

    public void sincronizarUsuario() {
        Usuario usuario = usuarioLogado.getValue();
        usuario.setSincronizado(true);
        ThreadManager.getExecutor().execute(() -> {
            usuarioRepository.atualizar(usuario);
        });
    }

    public void deletarUsuario() {
        Usuario usuario = usuarioLogado.getValue();
        ThreadManager.getExecutor().execute(() -> {
            usuarioRepository.deletar(usuario);
        });
    }
}
