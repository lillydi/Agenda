package com.cacere.lillydi.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button botaoSalvar = (Button) findViewById(R.id.formulario_botaoSalvar);

        if (botaoSalvar != null) {
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Formulario.this, "( ͡° ͜ʖ ͡°)", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
