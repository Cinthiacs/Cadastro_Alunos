package com.example.cadastroalunos.api;
import com.example.cadastroalunos.model.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface  AlunoService {
    @POST("aluno")
    Call<Aluno> salvarAluno(@Body Aluno aluno);

    @GET("aluno")
    Call<List<Aluno>> listarAlunos();
}

