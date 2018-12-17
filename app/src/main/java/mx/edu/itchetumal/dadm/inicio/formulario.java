package mx.edu.itchetumal.dadm.inicio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formulario extends AppCompatActivity implements View.OnClickListener {
    private EditText edt1,edt2,edt3,edt4;
    private Button btnInsertar, btnMostrar, btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        edt1  = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        edt3 = (EditText)findViewById(R.id.edt3);
        edt4 = (EditText)findViewById(R.id.edt4);
        btnInsertar = (Button)findViewById(R.id.btnInsertar);
        btnMostrar = (Button)findViewById(R.id.btnMostrar);
        btnSalir = (Button)findViewById(R.id.btnSalir);


        btnInsertar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }
    public void alta(View v) {
        Sqlite admin = new Sqlite(this,
                "pais", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = edt1.getText().toString();
        String nombre = edt2.getText().toString();
        String direccion = edt3.getText().toString();
        String foto = edt4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("nombre", nombre);
        registro.put("direccion", direccion);
        registro.put("foto", foto);
        bd.insert("pais", null, registro);
        bd.close();
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        Toast.makeText(this, "Se cargaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }
    public void consulta(View v) {
        Sqlite admin = new Sqlite(this,
                "pais", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = edt1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,direccion,foto  from pais where codigo=" + codigo, null);
        if (fila.moveToFirst()) {
            edt2.setText(fila.getString(0));
            edt3.setText(fila.getString(1));
            edt4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con dicho dni",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }



    public void cerrar(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnInsertar){
            alta(v);
            cerrar(v);
        }

    }


}
