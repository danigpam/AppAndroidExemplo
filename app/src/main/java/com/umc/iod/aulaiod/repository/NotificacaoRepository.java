package com.umc.iod.aulaiod.repository;

import android.app.Application;
import android.util.Log;

import com.umc.iod.aulaiod.model.Notificacao;
import com.umc.iod.aulaiod.repository.local.LocalDatabase;
import com.umc.iod.aulaiod.repository.local.NotificacaoDAO;
import com.umc.iod.aulaiod.repository.remoto.NotificacaoService;
import com.umc.iod.aulaiod.repository.remoto.RetrofitConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NotificacaoRepository {

    private NotificacaoDAO notificacaoDAO;
    private NotificacaoService notificacaoService;

    public NotificacaoRepository(Application context) {
        notificacaoDAO = LocalDatabase.getInstance(context).notificacaoDAO();
        notificacaoService = new RetrofitConfig().getNotificacaoService();
    }

    public List<Notificacao> getNotificacoesUsuario(long id) {

        try {
            Call<List<Notificacao>> chamadaRemota = notificacaoService.lerNotificacoesDoUsuario(id);
            Response<List<Notificacao>> respostaRemota = chamadaRemota.execute();
            List<Notificacao> listaNotificacoesUsuario = respostaRemota.body();

            List<Notificacao> notificacoesNovas = new ArrayList<>();
            for (Notificacao notificacao : listaNotificacoesUsuario) {
                try {
                    notificacaoDAO.inserir(notificacao);
                    Log.d(getClass().getName(), "Recebemos uma notificação nova!!");
                    notificacoesNovas.add(notificacao);
                }catch(Exception e) {
                }
            }
            return notificacoesNovas;

        } catch (IOException e) {
            Log.e(getClass().getName(), "Falha na comunicacao da API com erro: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
