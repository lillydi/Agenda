package com.cacere.lillydi.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cacere.lillydi.agenda.dao.AlunoDAO;
import com.cacere.lillydi.agenda.modelo.Aluno;

public class Formulario extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agenda, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_ok:
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId() != null){
                    dao.update(aluno);
                    Toast.makeText(Formulario.this, "( ͡° ͜ʖ ͡°) - " + aluno.getNome() + " atualizado!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dao.insert(aluno);
                    Toast.makeText(Formulario.this, "( ͡° ͜ʖ ͡°) - " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                }
                dao.close();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
