package br.ifsc.edu.listviewnotassqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class NotasDAO {

    SQLiteDatabase bd;

    public NotasDAO(Context context) {

        bd = context.openOrCreateDatabase("meubd", context.MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas(" +
                "id integer primary key autoincrement," +
                "titulo varchar not null," +
                "texto varchar);");
    }

    public ArrayList<Nota> getNotas(){

        ArrayList<Nota> ArraylistadeNotas = new ArrayList<Nota>();

        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM notas", null,null);
        cursor.moveToFirst();
        //Log.d("TabelaNotas", cursor.getString(0)+cursor.getString(1));

        while(!cursor.isAfterLast())
        {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String texto = cursor.getString(cursor.getColumnIndex("texto"));

            ArraylistadeNotas.add(new Nota(titulo,texto));
            //Log.d("TabelaNotas", id + titulo + texto);

            cursor.moveToNext();
        }

        return ArraylistadeNotas;
    }

    public void inserirNota(Nota nota){
       bd.execSQL("INSERT INTO notas (titulo,texto) VALUES ('"+nota.getTitulo()+"','"+nota.getTexto()+"');");
    }
}
