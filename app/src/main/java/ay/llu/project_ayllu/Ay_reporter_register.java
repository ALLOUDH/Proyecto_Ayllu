package ay.llu.project_ayllu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ay_reporter_register extends AppCompatActivity {

    TextView txtdepartamento,txtProvincia,txtDistrito;
    Spinner spnDepartamento, spnProvincia, spnDistrito;

    String Departselec = "";

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_reporter_register);
        txtdepartamento=findViewById(R.id.txtDepartamento_reporter);
        txtProvincia=findViewById(R.id.txtProvincia_reporter);
        txtDistrito=findViewById(R.id.txtDistrito_reporter);
        spnDepartamento=findViewById(R.id.Spinner_departamento_reporter);
        spnProvincia=findViewById(R.id.Spinner_provincia_reporter);
        spnDistrito=findViewById(R.id.Spinner_distrito_reporter);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        loadCities();
    }

    public void loadCities(){
        final List<City_report> Departamento = new ArrayList<>();
        mDatabase.child("ReporterCity").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        String id = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        Departamento.add(new City_report(id, nombre));
                    }
                    ArrayAdapter<City_report> arrayAdapter = new ArrayAdapter<>(Ay_reporter_register.this, android.R.layout.simple_dropdown_item_1line, Departamento);
                    spnDepartamento.setAdapter(arrayAdapter);
                    spnDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Departselec = adapterView.getItemAtPosition(i).toString();
//                            txtdepartamento.setText("Departamento: " + Departselec);
                            String id = Departamento.get(i).getId();
                            txtdepartamento.setText("El id es: " + id);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}