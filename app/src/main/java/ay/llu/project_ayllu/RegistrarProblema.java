package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ay.llu.project_ayllu.MapsProblema.SeleccionarUbicacionProblema;

public class RegistrarProblema extends AppCompatActivity {
    Spinner spnCategoria;
    EditText edtTituloRegistrarProblema, edtDescripcionRegistrarProblema;
    Button btnRegistrarProblema; ;
    ImageButton btnSeleccionarUbicacion, btnAñadirImagenes, btn;
    private DatabaseReference AylluDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_problema);

        edtTituloRegistrarProblema = findViewById(R.id.edtTituloRegistrarProblema);
        edtDescripcionRegistrarProblema = findViewById(R.id.edtDescripcionRegistrarProblema);
        spnCategoria = findViewById(R.id.spnCategoria);
        btnRegistrarProblema = findViewById(R.id.btnRegistrarProblema);
        btnSeleccionarUbicacion = findViewById(R.id.btnSeleccionarUbicacion);
        btnAñadirImagenes = findViewById(R.id.btnAñadirImagenes);

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

        String [] categorias ={"Contaminación", "Delincuencia", "Desigualdad","Pobreza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        spnCategoria.setAdapter(adapter);

    }

    private void registrarProblema() {
        String id = AylluDatabase.push().getKey();
        String categoria = spnCategoria.getSelectedItem().toString();
        String tituloRegistrarProblema = edtTituloRegistrarProblema.getText().toString();
        String descripcionRegistrarProblema = edtDescripcionRegistrarProblema.getText().toString();

        if(TextUtils.isEmpty(tituloRegistrarProblema)||TextUtils.isEmpty(descripcionRegistrarProblema)){
            Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (categoria.equals("Contaminación")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema);
            AylluDatabase.child("Problemas/Contaminación").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        if (categoria.equals("Delincuencia")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema);
            AylluDatabase.child("Problemas/Delincuencia").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        if (categoria.equals("Desigualdad")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema);
            AylluDatabase.child("Problemas/Desigualdad").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        if (categoria.equals("Pobreza")){
            ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema);
            AylluDatabase.child("Problemas/Pobreza").child(id).setValue(unProblema);
            Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
            edtTituloRegistrarProblema.setText("");
            edtDescripcionRegistrarProblema.setText("");
        }
        ProblemaClase unProblema = new ProblemaClase(id,categoria,tituloRegistrarProblema,descripcionRegistrarProblema);
        AylluDatabase.child("Problemas_Recientes").child(id).setValue(unProblema);
        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
        edtTituloRegistrarProblema.setText("");
        edtDescripcionRegistrarProblema.setText("");

    }

}