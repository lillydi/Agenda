package com.cacere.lillydi.agenda.converter;

import com.cacere.lillydi.agenda.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by lillydi on 27/04/16.
 */
public class AlunoConverter {
    public String converterParaJSON(List<Aluno> lista) {
        JSONStringer js = new JSONStringer();
        try {
            js.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno : lista){
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }
            js.endArray();
            js.endObject();
            js.endArray();
            js.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
