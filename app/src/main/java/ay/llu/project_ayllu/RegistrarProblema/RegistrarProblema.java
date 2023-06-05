package ay.llu.project_ayllu.RegistrarProblema;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import ay.llu.project_ayllu.Ay_up_images;
import ay.llu.project_ayllu.MapsProblema.SeleccionarUbicacionProblema;
import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.SignUp;

public class RegistrarProblema extends AppCompatActivity {
    Spinner spnCategoria;
    EditText edtTituloRegistrarProblema, edtDescripcionRegistrarProblema;
    Button btnRegistrarProblema;
    String fechaActual;

    TextView txtLat,txtLong;
    String lati,longi;
    double mlat=0, mLong=0;
    ImageButton btnSeleccionarUbicacion, btnAñadirImagenes, btnAñadirVideos;
    private DatabaseReference AylluDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_problema);
        Intent intent = getIntent();

        edtTituloRegistrarProblema = findViewById(R.id.edtTituloRegistrarProblema);
        edtDescripcionRegistrarProblema = findViewById(R.id.edtDescripcionRegistrarProblema);
        spnCategoria = findViewById(R.id.spnCategoria);
        btnRegistrarProblema = findViewById(R.id.btnRegistrarProblema);
        btnSeleccionarUbicacion = findViewById(R.id.btnSeleccionarUbicacion);
        btnAñadirImagenes = findViewById(R.id.btnAñadirImagenes);
        btnAñadirVideos = findViewById(R.id.btnAñadirVideos);

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        fechaActual = day + "/" + (month + 1) + "/" + year;

//        txtLat = findViewById(R.id.txtLatitud);
//        txtLong = findViewById(R.id.txtLongitud);
        LatLng latLng = intent.getParcelableExtra("lugar");
        mlat = latLng.latitude;
        mLong = latLng.longitude;
        lati = String.valueOf(mlat);
        longi = String.valueOf(mLong);
//        txtLat.setText(lati);
//        txtLong.setText(longi);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        AylluDatabase = db.getReference();
        btnRegistrarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProblema();
            }
        });
        btnSeleccionarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarProblema.this, SeleccionarUbicacionProblema.class);
                startActivity(intent);
            }
        });
        btnAñadirImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        btnAñadirVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent,1);
            }
        });

        String [] categorias ={"Contaminación", "Delincuencia", "Desigualdad","Pobreza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        spnCategoria.setAdapter(adapter);

    }
    public void llamaranadirimagen(View view) {
        Intent image = new Intent(this, Ay_up_images.class);
        startActivity(image);
    }
    private void registrarProblema() {
        String id = AylluDatabase.push().getKey();
        String categoria = spnCategoria.getSelectedItem().toString();
        String tituloRegistrarProblema = edtTituloRegistrarProblema.getText().toString();
        String descripcionRegistrarProblema = edtDescripcionRegistrarProblema.getText().toString();
        String lat = lati;
        String lon = longi;
        String fecha = fechaActual;

        if(TextUtils.isEmpty(tituloRegistrarProblema)||TextUtils.isEmpty(descripcionRegistrarProblema)){
            Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (categoria.equals("Contaminación")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema,lat,lon,fecha);
            AylluDatabase.child("Problemas/Contaminación").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        if (categoria.equals("Delincuencia")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema,lat,lon,fecha);
            AylluDatabase.child("Problemas/Delincuencia").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        if (categoria.equals("Desigualdad")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema,lat,lon,fecha);
            AylluDatabase.child("Problemas/Desigualdad").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        if (categoria.equals("Pobreza")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema,lat,lon,fecha);
            AylluDatabase.child("Problemas/Pobreza").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema,lat,lon,fecha);
        AylluDatabase.child("Problemas_Recientes").child(id).setValue(unProblema);
        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
        edtTituloRegistrarProblema.setText("");
        edtDescripcionRegistrarProblema.setText("");

    }

}