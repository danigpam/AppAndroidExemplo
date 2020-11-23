package com.umc.iod.aulaiod.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;

import com.umc.iod.aulaiod.model.Notificacao;

public class NotificacaoUtils extends ContextWrapper {

    private NotificationManager notificationManager;
    private static final String CANAL_NOTIFICACAO_ID = "com.umc.iod.aulaiod";
    private static final String CANAL_NOTIFICACAO_NOME ="Notificações do Meu App";

    public NotificacaoUtils(Context base) {
        super(base);
        criarCanalDeNotificacao();
    }

    private void criarCanalDeNotificacao() {
        NotificationChannel canal = new NotificationChannel(CANAL_NOTIFICACAO_ID, CANAL_NOTIFICACAO_NOME, NotificationManager.IMPORTANCE_HIGH);
        canal.enableLights(true);
        canal.enableVibration(true);
        canal.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        NotificationManager manager = getNotificationManager();
        manager.createNotificationChannel(canal);
    }

    private NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public void enviarNotificacao(Notificacao notificacao, PendingIntent intent) {
        Notification androidNotification = new Notification
                .Builder(getApplicationContext(), CANAL_NOTIFICACAO_ID)
                .setContentTitle(notificacao.getTitulo())
                .setContentText(notificacao.getTexto())
                .setContentIntent(intent)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true)
                .build();

        getNotificationManager().notify((int) notificacao.getId(), androidNotification);
    }
}
