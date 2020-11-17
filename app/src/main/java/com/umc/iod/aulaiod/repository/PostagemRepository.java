package com.umc.iod.aulaiod.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.repository.remoto.PostagemService;
import com.umc.iod.aulaiod.repository.remoto.RetrofitConfig;
import com.umc.iod.aulaiod.util.ThreadManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PostagemRepository {

    private PostagemService postagemService;
    private MutableLiveData<List<Postagem>> listaPostagem = new MutableLiveData<>();

    public PostagemRepository() {
        this.postagemService = new RetrofitConfig().getPostagemService();
    }

    public MutableLiveData<List<Postagem>> getListaPostagem() {
        atualizarPosts();
        return listaPostagem;
    }

    private void atualizarPosts() {
        ThreadManager.getExecutor().execute(() ->{
            try {
                Call<List<Postagem>> chamadaRemota = postagemService.listarPosts();
                Response<List<Postagem>> respostaRemota = chamadaRemota.execute();

                if (respostaRemota.isSuccessful()) {
                    List<Postagem> listaPostsApi = respostaRemota.body();
                    listaPostagem.postValue(listaPostsApi);
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
