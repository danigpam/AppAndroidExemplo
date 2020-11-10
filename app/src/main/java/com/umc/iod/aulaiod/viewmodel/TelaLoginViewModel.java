package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;

public class TelaLoginViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;


    public TelaLoginViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }

    public LiveData<Usuario> autenticarUsuario(Usuario usuario) {
        Log.i(getClass().getName(), "Dentro do autenticarUsuario - " + usuario.getEmail() + " " + usuario.getSenha());
        LiveData<Usuario> usuarioLogado = usuarioRepository.buscaPorEmailSenha(usuario);
        return usuarioLogado;
    }
}
