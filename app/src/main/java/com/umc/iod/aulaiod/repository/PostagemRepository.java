package com.umc.iod.aulaiod.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.repository.local.LocalDatabase;
import com.umc.iod.aulaiod.repository.local.PostagemDAO;
import com.umc.iod.aulaiod.repository.remoto.PostagemService;
import com.umc.iod.aulaiod.repository.remoto.RetrofitConfig;
import com.umc.iod.aulaiod.util.ThreadManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PostagemRepository {

    private PostagemService postagemService;
    private PostagemDAO postagemDAO;

    public PostagemRepository(Application context) {
        this.postagemService = new RetrofitConfig().getPostagemService();
        this.postagemDAO = LocalDatabase.getInstance(context).postagemDAO();
    }

    public LiveData<List<Postagem>> getListaPostagem() {
        atualizarPosts();
        return postagemDAO.buscarTodos();
    }

    private void atualizarPosts() {
        Log.i(getClass().getName(), "Dentro do atualizarPosts");
        ThreadManager.getExecutor().execute(() ->{
            try {
                Call<List<Postagem>> chamadaRemota = postagemService.listarPosts();
                Response<List<Postagem>> respostaRemota = chamadaRemota.execute();

                if (respostaRemota.isSuccessful()) {
                    List<Postagem> listaPostsApi = respostaRemota.body();
                    for (Postagem postagem : listaPostsApi) {
                        postagemDAO.inserir(postagem);
                    }
                } else {
                    Log.e(getClass().getName(), "Erro na resposta da requisição com API");
                }
            } catch (IOException e) {
                Log.e(getClass().getName(), "Conexão com a API falhou: "+ e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
