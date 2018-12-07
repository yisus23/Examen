package mx.edu.itchetumal.dadm.inicio;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnConsumirWs, btnParsear;
    EditText edtURLWS, edtRespuesta;
    String respuestaServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtener componentes de la interfaz con los que interactuara
        edtURLWS  = (EditText) findViewById(R.id.edtWS);
        edtRespuesta = (EditText)findViewById(R.id.edtRespuesta);
        btnConsumirWs = (Button)findViewById(R.id.btnConsumir);
        btnParsear = (Button)findViewById(R.id.btnParsear);

        btnParsear.setOnClickListener(this);


        //evento del boton consumir



        btnConsumirWs.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
               if (v.getId() == R.id.btnConsumir){
                   // Instantiate the RequestQueue.
                   RequestQueue queue = Volley.newRequestQueue(this);
                   String url =edtURLWS.getText().toString();
                   // Request a string response from the provided URL.
                   StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                           new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   // Display the first 500 characters of the response string.
                                   edtRespuesta.setText(" La Respuesta  es: "+ response);
                                   respuestaServidor = response;
                               }
                           },
                           new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   edtRespuesta.setText("no Funciona tu pendejada");
                               }
                           });

                   // Add the request to the RequestQueue.
                   queue.add(stringRequest);
               }else{
                   parsearJson(respuestaServidor);
               }
    }

    public void parsearJson(String respuestaJson){
        if (respuestaJson!=null){
            try{
                JSONArray arregloJSON  = new JSONArray(respuestaJson);
                edtRespuesta.setText("");
                for (int i = 0; i<respuestaJson.length();i++){
                    JSONObject a = arregloJSON.getJSONObject(i);
                    String codigo  =  a.getString("Código");
                    String nombre = a.getString("Nombre");
                    String direccion  = a.getString("Dirección");
                    String foto  = a.getString("Foto");


                    //mandar el resultado
                    edtRespuesta.setText(edtRespuesta.getText()+"\n Código: " +codigo
                                                                + "\n Nombre: " + nombre
                                                                + "\n Dirección: " + direccion
                                                            +  "\nFoto: " + foto +
                                                            "\n");



                }
            }catch (final JSONException e){
                Log.e("parsearJSON", "Json parsing error" + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Error al parsear Json" + e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }// aqui termina el metodo parsear contenido
}
