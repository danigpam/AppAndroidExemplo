package com.umc.iod.aulaiod.repository.remoto;

import com.umc.iod.aulaiod.model.Notificacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NotificacaoService {

    @GET("usuario/{usuarioId}/notificacao")
    public Call<List<Notificacao>> lerNotificacoesDoUsuario(@Path("usuarioId") long usuarioId);
}
