package ay.llu.project_ayllu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpReportero extends AppCompatActivity {
    EditText edtNombresReporteros, edtDNIReportero,edtContraseñaReportero, edtTelefonoReportero, edtProvinciaReportero,
            edtDistritoReportero;
    Spinner spnDepartamento;
    Button btnRegistrarSignUpReportero;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference AylluDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_reportero);

        edtNombresReporteros = findViewById(R.id.edt_Nombres_SignUpReportero);
        edtDNIReportero = findViewById(R.id.edt_DNI_SignUpReportero);
        edtContraseñaReportero = findViewById(R.id.edt_Contraseña_SignUpReportero);
        edtTelefonoReportero = findViewById(R.id.edt_NroCelular_SignUpReportero);
        btnRegistrarSignUpReportero = findViewById(R.id.btn_Registrar_SignUpReportero);
        spnDepartamento = findViewById(R.id.spnDepartamento);
        edtProvinciaReportero = findViewById(R.id.edtProvinciaReportero);
        edtDistritoReportero = findViewById(R.id.edtDistritoReportero);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        AylluDatabase = db.getReference();

        String [] departamentos ={"Amazonas", "Áncash", "Apurímac","Arequipa","Ayacucho","Cajamarca","Callao",
                                    "Cusco","Huancavelica","Huánuco","Ica","Junín","La Libertad",
                                    "Lambayeque","Lima","Loreto","Madre de Dios","Moquegua","Pasco",
                                    "Piura","Puno","San Martín","Tacna","Tumbes","Ucayali"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departamentos);
        spnDepartamento.setAdapter(adapter);

        btnRegistrarSignUpReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarReportero();
            }
        });

    }

    private void registrarReportero() {
        String dniReportero = edtDNIReportero.getText().toString();
        String nombres = edtNombresReporteros.getText().toString();
        String contraseñaReportero = edtContraseñaReportero.getText().toString();
        String telefono = edtTelefonoReportero.getText().toString();
        String Departamento = spnDepartamento.getSelectedItem().toString();
        String Provincia = edtProvinciaReportero.getText().toString();
        String Distrito = edtDistritoReportero.getText().toString();

        if(TextUtils.isEmpty(nombres)||TextUtils.isEmpty(dniReportero)||TextUtils.isEmpty(contraseñaReportero)||TextUtils.isEmpty(telefono)||TextUtils.isEmpty(Departamento)||TextUtils.isEmpty(Provincia)||TextUtils.isEmpty(Distrito)){
            Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ReporteroClase reportero = new ReporteroClase(dniReportero,nombres,contraseñaReportero,telefono,Departamento,Provincia,Distrito);
        AylluDatabase.child("Reporteros").child(dniReportero).setValue(reportero);

        Toast.makeText(SignUpReportero.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        edtNombresReporteros.setText("");
        edtDNIReportero.setText("");
        edtContraseñaReportero.setText("");
        edtTelefonoReportero.setText("");
        edtProvinciaReportero.setText("");
        edtDistritoReportero.setText("");
    }
}