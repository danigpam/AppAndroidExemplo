package com.umc.iod.aulaiod.repository.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.umc.iod.aulaiod.model.Usuario;

@Dao
public interface UsuarioDAO {

    @Insert
    public long inserir(Usuario usuario);

    @Query("SELECT count(id)>0 FROM usuario WHERE email = :email")
    public Boolean verificarEmailExistente(String email);
}
