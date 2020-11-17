package com.umc.iod.aulaiod.repository.remoto;

import com.umc.iod.aulaiod.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostagemService {

    @GET("posts")
    public Call<List<Postagem>> listarPosts();

}
