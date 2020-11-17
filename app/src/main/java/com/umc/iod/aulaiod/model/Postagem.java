package com.umc.iod.aulaiod.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Postagem {

    @PrimaryKey
    private long id;
    private String data;
    private String titulo;
    private String texto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
