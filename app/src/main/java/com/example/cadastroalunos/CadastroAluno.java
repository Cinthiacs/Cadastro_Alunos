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
// import com.example.ac2mb.api.AlunoService;
// import com.example.ac2mb.api.ViaCepService;
// import com.example.ac2mb.model.Aluno;
// import com.example.ac2mb.model.Endereco;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;



public class CadastroAluno extends AppCompatActivity {
    private EditText ra, nome, cep, logradouro, complemento, bairro, cidade, uf;
    private Button btnBuscarCep, btnSalvar;

    //private ViaCepService viaCepService;
    //private AlunoService alunoService;
    //private Retrofit retrofitViaCep, retrofitMockApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_aluno);

        ra = (EditText)findViewById(R.id.ra);
        nome = findViewById(R.id.nome);
        cep = findViewById(R.id.cep);
        logradouro = findViewById(R.id.logradouro);
        complemento = findViewById(R.id.complemento);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        uf = findViewById(R.id.uf);

        btnBuscarCep = findViewById(R.id.btnBuscarCep);
        btnSalvar = findViewById(R.id.btnSalvar);






    }
}