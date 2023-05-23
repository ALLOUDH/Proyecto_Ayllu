package ay.llu.project_ayllu.InformacionProblema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.R;

public class ProblemaInformacion extends AppCompatActivity {

    TextView txtTituloMostrarProblema,txtFechaMostrarProblema,txtDescripcionMostrarProblema;
    String titulo, descripcion, latitud, longitud, fecha;
    ImageView imgUbicacionMostrarProblema;
    Button btnVerGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_informacion);

        txtTituloMostrarProblema = findViewById(R.id.txtTituloMostrarProblema);
        txtDescripcionMostrarProblema = findViewById(R.id.txtDescripcionMostrarProblema);
        txtFechaMostrarProblema = findViewById(R.id.txtFechaMostrarProblema);

        titulo= getIntent().getStringExtra("titulo");
        descripcion = getIntent().getStringExtra("descripcion");
        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");
        fecha = getIntent().getStringExtra("fecha");
        imgUbicacionMostrarProblema=findViewById(R.id.imgUbicacionMostrarProblema);
        btnVerGrupo = findViewById(R.id.btnVerGrupo);

        txtTituloMostrarProblema.setText(titulo);
        txtFechaMostrarProblema.setText(fecha);
        txtDescripcionMostrarProblema.setText(descripcion);

        imgUbicacionMostrarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProblemaInformacion.this, MostrarUbicacionProblema.class);
                i.putExtra("lati",latitud);
                i.putExtra("longi",longitud);
                Toast.makeText(ProblemaInformacion.this, "Problema Localizado", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        btnVerGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProblemaInformacion.this, ay.llu.project_ayllu.Grupos.class);
                startActivity(intent);
            }
        });

    }
}