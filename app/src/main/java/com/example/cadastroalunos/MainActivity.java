package com.example.cadastroalunos;
import static com.example.cadastroalunos.R.id.btn_abre_tela_cadastro_alunos;
import static com.example.cadastroalunos.R.id.btn_abre_tela_lista_alunos;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_abre_tela_cadastro_alunos, btn_abre_tela_lista_alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_abre_tela_cadastro_alunos = (Button)findViewById(R.id.btn_abre_tela_cadastro_alunos);
        btn_abre_tela_lista_alunos = (Button)findViewById(R.id.btn_abre_tela_lista_alunos);

        btn_abre_tela_cadastro_alunos.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this,CadastroAluno.class);
            startActivity(intent);
        });

        btn_abre_tela_lista_alunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityListagemAlunos.class);
                startActivity(intent);
            }
        });
    }
}
