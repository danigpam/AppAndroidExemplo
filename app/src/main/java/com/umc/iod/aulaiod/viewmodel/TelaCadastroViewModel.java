package com.umc.iod.aulaiod.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;

public class TelaCadastroViewModel extends ViewModel {

    private UsuarioRepository usuarioRepository;

    public TelaCadastroViewModel() {
        usuarioRepository = new UsuarioRepository();
    }

    public void cadastrar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do cadastrar ");
        usuarioRepository.cadastrar(usuario);
    }
}
