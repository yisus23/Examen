package mx.edu.itchetumal.dadm.inicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {





    Context contexto;

    public Sqlite (Context contexto, String dbPais, Object o, int i){
        super(contexto, "Pais", null, 1);
        this.contexto=contexto;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pais(codigo integer primary key autoincrement, nombre text, direccion text, foto text)");
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists pais");
        db.execSQL("create table pais(codigo integer primary key autoincrement, nombre text, direccion text, foto text)");
    }

}
