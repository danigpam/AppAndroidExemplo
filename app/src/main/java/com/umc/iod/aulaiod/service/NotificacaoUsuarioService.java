package com.umc.iod.aulaiod.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.umc.iod.aulaiod.activity.TelaFeed;
import com.umc.iod.aulaiod.model.Notificacao;
import com.umc.iod.aulaiod.repository.NotificacaoRepository;
import com.umc.iod.aulaiod.util.NotificacaoUtils;

import java.util.List;

import static java.lang.Thread.sleep;

public class NotificacaoUsuarioService extends IntentService {

    public NotificacaoUsuarioService() {
        super("NotificacaoUsuarioService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        NotificacaoRepository notificacaoRepository = new NotificacaoRepository(getApplication());
        NotificacaoUtils notificacaoUtils = new NotificacaoUtils(getApplicationContext());
        long idUsuario = intent.getLongExtra("usuarioId", 0);

        while (true) {

            Log.i(getClass().getName(), "Dentro do onHandleIntent, id do ususario = " + idUsuario);
            List<Notificacao> listaNotificacao = notificacaoRepository.getNotificacoesUsuario(idUsuario);

            Intent intencao = new Intent();
            intencao.setClass(this, TelaFeed.class);
            intencao.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intencao.putExtra("usuarioId", idUsuario);
            PendingIntent intencaoNotificacao = PendingIntent.getActivity(this, 0, intencao, PendingIntent.FLAG_UPDATE_CURRENT);

            if (listaNotificacao != null) {
                for (Notificacao notificacao : listaNotificacao) {
                    notificacaoUtils.enviarNotificacao(notificacao, intencaoNotificacao);
                }
            }

            try {
                sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
