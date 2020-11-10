package com.umc.iod.aulaiod.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.umc.iod.aulaiod.model.Usuario;

@Dao
public interface UsuarioDAO {

    @Insert
    public long inserir(Usuario usuario);

    @Update
    public void atualizar(Usuario usuario);

    @Delete
    public void deletar(Usuario usuario);

    @Query("SELECT count(id)>0 FROM usuario WHERE email = :email")
    public Boolean verificarEmailExistente(String email);

    @Query("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
    public LiveData<Usuario> buscaPorEmailSenha(String email, String senha);

    @Query("SELECT * FROM usuario WHERE id = :id")
    public LiveData<Usuario> consultarPorIdLive(long id);
}
