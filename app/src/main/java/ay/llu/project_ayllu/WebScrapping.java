package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebScrapping extends AppCompatActivity {

    Button btn_nuevo, btn_registrar;
    EditText edt_buscar_problema;
    TextView txt_titulo,txt_descripcion;
    ImageView img_problema;
    LinearLayout lyt_problema_info;
    Boolean invisible = true;

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
        txt_titulo.setTextColor(getColor(R.color.white));
        txt_descripcion.setTextColor(getColor(R.color.white));
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
    }
}