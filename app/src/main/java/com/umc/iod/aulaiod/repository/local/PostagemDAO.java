package com.umc.iod.aulaiod.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.umc.iod.aulaiod.model.Postagem;

import java.util.List;

@Dao
public interface PostagemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserir(Postagem postagem);

    @Query("SELECT * FROM postagem")
    public LiveData<List<Postagem>> buscarTodos();
}
