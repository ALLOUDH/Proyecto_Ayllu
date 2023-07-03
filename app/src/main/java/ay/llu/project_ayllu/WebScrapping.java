package ay.llu.project_ayllu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import ay.llu.project_ayllu.RegistrarProblema.ProblemaClase;

public class WebScrapping extends AppCompatActivity {

    Button btn_nuevo, btn_registrar;
    EditText edt_buscar_problema;
    TextView txt_titulo,txt_descripcion;
    ImageView img_problema;
    LinearLayout lyt_problema_info;
    Boolean invisible = true;

    String fechaActual, horaActual;

    private DatabaseReference AylluDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_scrapping);
        btn_nuevo = findViewById(R.id.btn_buscar_wbs);
        btn_registrar = findViewById(R.id.btn_registrar_wbs);
        edt_buscar_problema = findViewById(R.id.edt_buscar_problema);
        txt_titulo = findViewById(R.id.txt_titulo_wbs);
        txt_descripcion = findViewById(R.id.txt_descripcion_wbs);
        img_problema = findViewById(R.id.img_problema_wbs);
        lyt_problema_info = findViewById(R.id.lyt_problema_wbs);
        txt_titulo.setTextColor(getColor(R.color.purple_text));
        txt_descripcion.setTextColor(getColor(R.color.purple_text));

        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        AylluDatabase = db.getReference();

        //Capturar fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        fechaActual = day + "/" + (month + 1) + "/" + year;
        //Capturar hora actual
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        horaActual = hora+":"+minutos;
    }

    public void call_buscar(View view) {
        new ScrapingWeb().execute();
    }
    public class ScrapingWeb extends AsyncTask<Void,Void, String> {
        String titulo, descripcion;
        String busqueda = edt_buscar_problema.getText().toString();

        @Override
        protected String doInBackground(Void... voids) {
            try{
                Document web = Jsoup.connect("https://es.wikipedia.org/wiki/"+busqueda).get();
                Elements elements = web.getElementsByClass("mw-page-title-main" );
                Elements elements1 = web.getElementsByClass("quote");
                Element elements2 = web.getElementsByClass("thumbborder").first();
                String imageUrl = elements2.absUrl("src");
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                titulo=elements.text();
                descripcion=elements1.text();
                img_problema.setImageBitmap(bitmap);

            }catch (Exception e){
                Log.i("errorLog->",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            txt_titulo.setText(titulo);
            txt_descripcion.setText(descripcion);
            super.onPostExecute(aVoid);
        }
    }

    public void call_registrar(View view) {
        String id = AylluDatabase.push().getKey();
        String categoria = edt_buscar_problema.getText().toString();
        String tituloRegistrarProblema = txt_titulo.getText().toString();
        String descripcionRegistrarProblema = txt_descripcion.getText().toString();
        String fecha = fechaActual;
        String hora = horaActual;

        ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema,"","",fecha,hora,"","","");

        if(TextUtils.isEmpty(tituloRegistrarProblema)||TextUtils.isEmpty(descripcionRegistrarProblema)){
            Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AylluDatabase.child("Problemas_Recientes").child(id).setValue(unProblema);
        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Ay_up_images.class);
        String idproblema = id;
        String categ = categoria;
        i.putExtra("idproblema",idproblema);
        i.putExtra("idcategoria",categ);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        txt_titulo.setText("");
        txt_descripcion.setText("");

        if (categoria.equals("Contaminación")){
            AylluDatabase.child("Problemas/Contaminación").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            txt_titulo.setText("");
            txt_descripcion.setText("");
        }
        if (categoria.equals("Delincuencia")){
            AylluDatabase.child("Problemas/Delincuencia").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            txt_titulo.setText("");
            txt_descripcion.setText("");
        }
        if (categoria.equals("Desigualdad")){
            AylluDatabase.child("Problemas/Desigualdad").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            txt_titulo.setText("");
            txt_descripcion.setText("");
        }
        if (categoria.equals("Pobreza")){
            AylluDatabase.child("Problemas/Pobreza").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            txt_titulo.setText("");
            txt_descripcion.setText("");
        }
    }
}