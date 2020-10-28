package com.umc.iod.aulaiod.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index(value = "email", unique = true))
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String email;
    private String senha;


    public Usuario() {
    }

    @Ignore
    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
