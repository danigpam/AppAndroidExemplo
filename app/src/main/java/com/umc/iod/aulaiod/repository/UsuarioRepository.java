package com.umc.iod.aulaiod.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.local.LocalDatabase;
import com.umc.iod.aulaiod.repository.local.UsuarioDAO;

public class UsuarioRepository {

    private LocalDatabase database;
    private UsuarioDAO usuarioDAO;

    public UsuarioRepository(Application context) {
        database = LocalDatabase.getInstance(context);
        usuarioDAO = database.usuarioDAO();
    }

    public Boolean verificaEmailExistente(String email) {
        Log.d(getClass().getName(), "Dentro do verificaEmailExistente");
        Boolean existe = usuarioDAO.verificarEmailExistente(email);
        return existe;
    }

    public Usuario cadastrar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do cadastrar ");

        if (!verificaEmailExistente(usuario.getEmail())) {
            Log.d(getClass().getName(), "Cadastrou o usuario de email " + usuario.getEmail());
            long id = usuarioDAO.inserir(usuario);
            usuario.setId(id);
            return usuario;
        }
        Log.d(getClass().getName(), "Não cadastrou o usuario de email " + usuario.getEmail());
        return null;
    }

    public void atualizar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do atualizar ");
        usuarioDAO.atualizar(usuario);
    }

    public LiveData<Usuario> pesquisarPorIdLive(long id) {
        return usuarioDAO.consultarPorIdLive(id);
    }

    public void deletar(Usuario usuario) {
        usuarioDAO.deletar(usuario);
    }

    public LiveData<Usuario> buscaPorEmailSenha(Usuario usuario) {
        return usuarioDAO.buscaPorEmailSenha(usuario.getEmail(), usuario.getSenha());
    }
}
