package es.iesnervion.atellez.repasoficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtFichero;
    String texto;
    Button btnGuardar;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFichero =(TextView) findViewById(R.id.txtView);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        editText = (EditText) findViewById(R.id.edTexto);


        btnGuardar.setOnClickListener(this);

    }

    private void escribirFichero(String texto){
        try {
            OutputStreamWriter outputStream = new OutputStreamWriter(openFileOutput("prueba_init.txt",Context.MODE_PRIVATE));
            outputStream.write(texto);
            outputStream.close();
        } catch (Exception e) {
            Log.e("Ficheros","Error al escribir en el fichero dentro de memoria interna");
        }
    }

    private String leerFichero(){
        String resultadoLectura = "";
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("prueba_init.txt")));
             resultadoLectura = fin.readLine();
            fin.close();

        } catch (Exception e) {
            Log.e("Ficheros","Error al leer el fichero dentro de la memoria interna");
        }finally {
            return  resultadoLectura;
        }
    }

    @Override
    public void onClick(View v) {
        texto = editText.getText().toString();
        escribirFichero(texto);

        txtFichero.setText(leerFichero());
    }
}
