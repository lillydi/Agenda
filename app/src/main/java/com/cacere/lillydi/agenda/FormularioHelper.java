package com.cacere.lillydi.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.cacere.lillydi.agenda.modelo.Aluno;

/**
 * Created by lillydi on 04/04/2016.
 */
public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEmail;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final RatingBar campoNota;
    private final ImageView campoFoto;

    Aluno aluno;

    public FormularioHelper(Formulario activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();
    }

    public Aluno getAluno() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setCaminhoFoto((String) campoFoto.getTag());

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoEmail.setText(aluno.getEmail());
        campoEndereco.setText(aluno.getEndereco());
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoNota.setProgress(aluno.getNota().intValue());
        carregaFoto(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaFoto(String caminhoFoto) {
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapRed = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapRed);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}

