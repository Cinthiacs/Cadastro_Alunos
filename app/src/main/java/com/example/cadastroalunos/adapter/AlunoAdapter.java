package com.example.cadastroalunos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_aluno, parent, false);
        return new AlunoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.tvRa.setText("RA: " + aluno.getRa());
        holder.tvNome.setText("Nome: " + aluno.getNome());
        holder.tvEndereco.setText("Endereço: " + aluno.getLogradouro() + ", " + aluno.getComplemento() + " - " + aluno.getBairro() + ", " + aluno.getCidade() + " - " + aluno.getUf());
        holder.tvCep.setText("CEP: " + aluno.getCep());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView tvRa, tvNome, tvEndereco, tvCep;

        AlunoViewHolder(View itemView) {
            super(itemView);
            tvRa = itemView.findViewById(R.id.item_ra);
            tvNome = itemView.findViewById(R.id.item_nome);
            tvEndereco = itemView.findViewById(R.id.item_endereco);
            tvCep = itemView.findViewById(R.id.item_cep);
        }
    }
}