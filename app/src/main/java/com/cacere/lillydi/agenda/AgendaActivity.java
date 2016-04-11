package com.cacere.lillydi.agenda;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cacere.lillydi.agenda.dao.AlunoDAO;
import com.cacere.lillydi.agenda.modelo.Aluno;

import java.util.List;
import java.util.jar.Manifest;

public class AgendaActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.lista_alunos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(position);

                Intent vaiParaFormulario = new Intent(AgendaActivity.this, Formulario.class);
                vaiParaFormulario.putExtra("aluno", aluno);
                startActivity(vaiParaFormulario);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent vaiPraFormulario = new Intent(AgendaActivity.this, Formulario.class);
                startActivity(vaiPraFormulario);
            }
        });

        registerForContextMenu(lista);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getAlunos();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1, alunos);

        lista.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) lista.getItemAtPosition(info.position);

        MenuItem discar = menu.add("Ligar para Aluno");
        discar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(ActivityCompat.checkSelfPermission(AgendaActivity.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AgendaActivity.this,
                            new String[]{android.Manifest.permission.CALL_PHONE}, 123);

                }

                String paraDiscar = "tel:"+aluno.getTelefone();
                Intent intentDiscar = new Intent(Intent.ACTION_CALL);
                intentDiscar.setData(Uri.parse(paraDiscar));
                startActivity(intentDiscar);
                return false;
            }
        });

        MenuItem site = menu.add("Enviar Email");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String mailto =  aluno.getEmail();
        if(!mailto.startsWith("mailto:")){
            mailto = "mailto:" + mailto;
        }
        intentSite.setData(Uri.parse(mailto));
        site.setIntent(intentSite);

        MenuItem sms = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        String smsString = "sms:" + aluno.getTelefone();
        intentSMS.setData(Uri.parse(smsString));
        sms.setIntent(intentSMS);

        MenuItem map = menu.add("Ver no Mapa");
        Intent intentMap = new Intent(Intent.ACTION_VIEW);
        String mapString = "geo:0,0?q=" + aluno.getEndereco();
        intentMap.setData(Uri.parse(mapString));
        map.setIntent(intentMap);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDAO dao = new AlunoDAO(AgendaActivity.this);
                dao.delete(aluno);
                dao.close();
                carregarLista();

                return false;
            }
        });
    }
}
