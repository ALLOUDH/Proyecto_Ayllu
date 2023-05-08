package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarProblema extends AppCompatActivity {
    Spinner spnCategoria;
    EditText edtTituloRegistrarProblema, edtDescripcionRegistrarProblema;
    Button btnRegistrarProblema;
    private DatabaseReference AylluDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_problema);

        edtTituloRegistrarProblema = findViewById(R.id.edtTituloRegistrarProblema);
        edtDescripcionRegistrarProblema = findViewById(R.id.edtDescripcionRegistrarProblema);
        spnCategoria = findViewById(R.id.spnCategoria);
        btnRegistrarProblema = findViewById(R.id.btnRegistrarProblema);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        AylluDatabase = db.getReference();
        btnRegistrarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProblema();
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