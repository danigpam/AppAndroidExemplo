package com.umc.iod.aulaiod.repository;

import android.util.Log;

import com.umc.iod.aulaiod.model.Usuario;

public class UsuarioRepository {

    public Boolean verificaEmailExistente(String email) {
        Log.d(getClass().getName(), "Dentro do verificaEmailExistente");
        Boolean existe = "a@email.com".equals(email);
        return existe;
    }

    public Usuario cadastrar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do cadastrar ");

        if (!verificaEmailExistente(usuario.getEmail())) {
            Log.d(getClass().getName(), "Cadastrou o usuario de email " + usuario.getEmail());
            usuario.setId(1);
            return usuario;
        }
        Log.d(getClass().getName(), "Não cadastrou o usuario de email " + usuario.getEmail());
        return null;
    }
}
