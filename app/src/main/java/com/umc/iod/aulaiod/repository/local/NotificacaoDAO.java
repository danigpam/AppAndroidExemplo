package com.umc.iod.aulaiod.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.umc.iod.aulaiod.model.Notificacao;

import java.util.List;

@Dao
public interface NotificacaoDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void inserir(Notificacao notificacao);

    @Query("SELECT * FROM notificacao")
    public LiveData<List<Notificacao>> listarNotificacoes();
}
