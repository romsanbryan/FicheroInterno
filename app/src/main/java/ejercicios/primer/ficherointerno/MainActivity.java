package ejercicios.primer.ficherointerno;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Aplicacion que recoge datos de un campo de texto y lo guarda en memoria interna
 *
 * @author romsanbryan
 */
public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        enviar = findViewById(R.id.enviar);
    }

    /**
     * Accion de boton que permite guardar datos en la carpeta por defecto
     * @param v
     */
    public void enviar(View v){
        try {
            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("fichero.txt", Context.MODE_PRIVATE));
            fout.write(text.getText().toString());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        text.setText("");
    }

    /**
     * Accion de boton que permite leer datos de un fichero en la carpeta por defecto
     * @param v
     */
    public void leer(View v){
        enviar.setEnabled(true);
        String texto = "";
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("fichero.txt")));
            texto = texto + fin.readLine().toString();
            text.setText(texto);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Accion de boton que permite leer datos de un fichero en la carpeta /res/raw
     * @param v
     */
    public void leer_raw(View v){
        String texto = "";
        enviar.setEnabled(false);
        try {
            InputStream fraw = getResources().openRawResource(R.raw.fichero_raw);
            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            texto = brin.readLine().toString();
            text.setText(texto);
            fraw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
