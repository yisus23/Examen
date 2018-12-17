package mx.edu.itchetumal.dadm.inicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Sqlite extends SQLiteOpenHelper {





    Context contexto;

    public Sqlite(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
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
