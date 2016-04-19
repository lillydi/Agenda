package com.cacere.lillydi.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.cacere.lillydi.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lillydi on 04/04/2016.
 */
public class AlunoDAO extends SQLiteOpenHelper {


    public AlunoDAO(Context context) {

        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL, " +
                "email TEXT, " +
                "telefone TEXT, " +
                "endereco TEXT, " +
                "nota REAL, " +
                "caminhoFoto TEXT" +
                ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql;
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Alunos ADD COLUMN caminhoFoto TEXT;";
                db.execSQL(sql);
        }
    }

    public void insert(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = montarDados(aluno);

        db.insert("Alunos", null, dados);
    }

    public List<Aluno> getAlunos() {

        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("id"))));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            alunos.add(aluno);
        }
        cursor.close();

        return alunos;
    }

    public void delete(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {
                aluno.getId().toString()
        };
        db.delete("Alunos", "id = ?", params);
    }

    public void update(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = montarDados(aluno);
        String[] params = {aluno.getId().toString()};
        db.update("Alunos", dados, "id = ?", params);

    }

    @NonNull
    private ContentValues montarDados(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("email", aluno.getEmail());
        dados.put("telefone", aluno.getTelefone());
        dados.put("endereco", aluno.getEndereco());
        dados.put("nota", aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());
        return dados;
    }

    public boolean isAluno(String telefone){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT *FROM Alunos WHERE telefone = ?";
        String[] params = {
                telefone
        };
        Cursor cursor = db.rawQuery(sql, params);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
