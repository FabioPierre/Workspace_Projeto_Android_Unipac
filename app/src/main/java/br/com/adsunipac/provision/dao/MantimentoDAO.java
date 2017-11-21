package br.com.adsunipac.provision.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.adsunipac.provision.atributos.Mantimento;

/**
 * Created by TECNOLOGIA on 20/11/2017.
 */

public class MantimentoDAO extends SQLiteOpenHelper{
    public MantimentoDAO(Context context) {
        super(context, "Provision", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Mantimentos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, quantidade TEXT, estoque TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE  IF EXISTS Mantimentos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Mantimento mantimento) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoMantimento(mantimento);

        db.insert("Mantimentos", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoMantimento(Mantimento mantimento) {
        ContentValues dados = new ContentValues();
        dados.put("nome", mantimento.getNome());
        dados.put("quantidade", mantimento.getQuantidade());
        dados.put("estoque", mantimento.getEstoque());
        return dados;
    }

    public List<Mantimento> buscaMantimentos() {
        String sql = "SELECT * FROM Mantimentos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Mantimento> mantimentos = new ArrayList<Mantimento>();
        while (c.moveToNext()){
            Mantimento mantimento = new Mantimento();
            mantimento.setId(c.getLong(c.getColumnIndex("id")));
            mantimento.setNome(c.getString(c.getColumnIndex("nome")));
            mantimento.setQuantidade(c.getString(c.getColumnIndex("quantidade")));
            mantimento.setEstoque(c.getString(c.getColumnIndex("estoque")));

            mantimentos.add(mantimento);
        }
        c.close();

        return mantimentos;
    }

    public void deleta(Mantimento mantimento) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {mantimento.getId().toString()};
        db.delete("Mantimentos", "id = ?", params);
    }

    public void atera(Mantimento mantimento) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoMantimento(mantimento);

        String[] params = {mantimento.getId().toString()};
        db.update("Mantimentos", dados, "id = ?", params);
    }
}
