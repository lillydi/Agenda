package com.cacere.lillydi.agenda.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cacere.lillydi.agenda.AgendaActivity;
import com.cacere.lillydi.agenda.R;
import com.cacere.lillydi.agenda.modelo.Aluno;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by lillydi on 13/04/2016.
 */
public class AlunosAdapter extends BaseAdapter{
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context ctx, List<Aluno> alunos) {
        this.alunos = alunos;
        this.context = ctx;
    }

    @Override
    public int getCount() {
        return alunos == null ? 0: alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  convertView;
        if(view == null){
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(alunos.get(position).getNome());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(alunos.get(position).getTelefone());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = alunos.get(position).getCaminhoFoto();
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapRed = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapRed);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return view;
    }
}
