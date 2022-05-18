package br.ifsc.edu.listviewnotassqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNota extends AppCompatActivity {

    EditText texto;
    EditText titulo;

    private NotasController notasController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nota);

        this.titulo = findViewById(R.id.titulo);
        this.texto = findViewById(R.id.texto);

        this.notasController = new NotasController(AddNota.this);
    }

    public void adicionaNota(View view) {

        if(!this.titulo.equals("") || !this.texto.equals(""))
        {
            Nota nota = new Nota(this.titulo.getText().toString(), texto.getText().toString());
            this.notasController.salvarNota(nota);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        else
            Toast.makeText(getApplicationContext(), "Não é possivel criar a nota!", Toast.LENGTH_SHORT).show();
    }
}
