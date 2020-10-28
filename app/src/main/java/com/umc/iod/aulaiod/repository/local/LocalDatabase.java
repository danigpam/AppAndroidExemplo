package com.umc.iod.aulaiod.repository.local;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.umc.iod.aulaiod.model.Usuario;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();
    private static LocalDatabase instancia;

    public static LocalDatabase getInstance(Application context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(context, LocalDatabase.class, "local_database").build();
        }
        return instancia;
    }
}
