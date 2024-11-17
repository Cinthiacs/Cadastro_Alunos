package com.example.cadastroalunos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.api.AlunoService;
import com.example.cadastroalunos.api.ViaCepService;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Endereco;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class CadastroAluno extends AppCompatActivity {
    private EditText ra, nome, cep, logradouro, complemento, bairro, cidade, uf;
    private Button btnBuscarCep, btnSalvar;

    private ViaCepService viaCepService;
    private AlunoService alunoService;
    private Retrofit retrofitViaCep, retrofitMockApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_aluno);

        ra = (EditText) findViewById(R.id.ra);
        nome = findViewById(R.id.nome);
        cep = findViewById(R.id.cep);
        logradouro = findViewById(R.id.logradouro);
        complemento = findViewById(R.id.complemento);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        uf = findViewById(R.id.uf);

        btnBuscarCep = findViewById(R.id.btnBuscarCep);
        btnSalvar = findViewById(R.id.btnSalvar);

        retrofitViaCep = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitMockApi = new Retrofit.Builder()
                //.baseUrl("https://66567a9d9f970b3b36c585db.mockapi.io/")
                .baseUrl("https://6739ffaea3a36b5a62f064bb.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        viaCepService = retrofitViaCep.create(ViaCepService.class);
        alunoService = retrofitMockApi.create(AlunoService.class);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCep();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CadastroAlunoActivity", "onDestroy called");
    }

    private void buscarCep() {
        String cep = this.cep.getText().toString().trim();  // Corrigir aqui, usando a variável correta
        if (!cep.isEmpty() && cep.length() == 8) {  // Verifique se o CEP tem 8 caracteres
            Call<Endereco> call = viaCepService.getEndereco(cep);
            call.enqueue(new Callback<Endereco>() {
                @Override
                public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Endereco endereco = response.body();
                        logradouro.setText(endereco.getLogradouro());
                        complemento.setText(endereco.getComplemento());
                        bairro.setText(endereco.getBairro());
                        cidade.setText(endereco.getLocalidade());
                        uf.setText(endereco.getUf());
                    } else {
                        Toast.makeText(CadastroAluno.this, "CEP não encontrado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Endereco> call, Throwable t) {
                    Toast.makeText(CadastroAluno.this, "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(CadastroAluno.this, "CEP inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarAluno() {
        Log.d("salvarAluno", "Iniciando método salvarAluno");

        // Pegando os valores corretos dos campos
        String raText = ra.getText().toString().trim();
        String nomeText = nome.getText().toString().trim();
        String cepText = cep.getText().toString().trim();
        String logradouroText = logradouro.getText().toString().trim();
        String complementoText = complemento.getText().toString().trim();
        String bairroText = bairro.getText().toString().trim();
        String cidadeText = cidade.getText().toString().trim();
        String ufText = uf.getText().toString().trim();

        // Verificar se todos os campos obrigatórios estão preenchidos
        if (raText.isEmpty() || nomeText.isEmpty() || cepText.isEmpty() || logradouroText.isEmpty() ||
                bairroText.isEmpty() || cidadeText.isEmpty() || ufText.isEmpty()) {
            Toast.makeText(CadastroAluno.this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertendo o RA para inteiro
        int raInt = Integer.parseInt(raText); // Isso já converte a String para int

        // Criando o objeto Aluno
        Aluno aluno = new Aluno(raInt, nomeText, cepText, logradouroText, complementoText, bairroText, cidadeText, ufText);
        Log.d("salvarAluno", "Aluno criado: " + aluno);

        // Chamando a API para salvar o aluno
        Call<Aluno> call = alunoService.salvarAluno(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    Log.d("salvarAluno", "Aluno salvo com sucesso");
                    Toast.makeText(CadastroAluno.this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();
                    // Limpar os campos após salvar
                    ra.setText(""); // Aqui você está limpando o campo, o que é correto
                    nome.setText("");
                    cep.setText("");
                    logradouro.setText("");
                    complemento.setText("");
                    bairro.setText("");
                    cidade.setText("");
                    uf.setText("");
                } else {
                    Log.e("salvarAluno", "Erro ao salvar aluno: " + response.message());
                    Toast.makeText(CadastroAluno.this, "Erro ao salvar aluno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Log.e("salvarAluno", "Erro ao salvar aluno: ", t);
                Toast.makeText(CadastroAluno.this, "Erro ao salvar aluno", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
