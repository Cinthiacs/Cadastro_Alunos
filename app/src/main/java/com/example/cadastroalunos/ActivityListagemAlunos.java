package com.example.cadastroalunos;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.adapter.AlunoAdapter;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.api.AlunoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityListagemAlunos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlunoAdapter alunoAdapter;
    private AlunoService alunoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listagem_alunos);

        recyclerView = findViewById(R.id.recyclerViewAlunos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6739ffaea3a36b5a62f064bb.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        alunoService = retrofit.create(AlunoService.class);
        listarAlunos();
    }

    private void listarAlunos() {
        Call<List<Aluno>> call = alunoService.listarAlunos();
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Aluno> alunos = response.body();
                    alunoAdapter = new AlunoAdapter(alunos);
                    recyclerView.setAdapter(alunoAdapter);
                } else {
                    Toast.makeText(ActivityListagemAlunos.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Toast.makeText(ActivityListagemAlunos.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}