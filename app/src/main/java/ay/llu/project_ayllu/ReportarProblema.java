package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ReportarProblema extends AppCompatActivity implements View.OnClickListener {
    EditText edt_descr;
    Button btn_enviar;
    ImageView btn_wsp;
    FirebaseDatabase database;
    DatabaseReference dbreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema);
        edt_descr = findViewById(R.id.edtReportarProblema);
        btn_enviar = findViewById(R.id.btnEnviar);
        btn_wsp = findViewById(R.id.btn_wsp);
        btn_enviar.setOnClickListener(this);
        dbreference = FirebaseDatabase.getInstance().getReference();
        edt_descr.setTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View view) {
            registrarReporteProblema();
    }

    private void registrarReporteProblema() {
        String id = dbreference.push().getKey();
        String reporte_problema = edt_descr.getText().toString();
        if (reporte_problema.isEmpty()){
            Toast.makeText(ReportarProblema.this, "Ingrese su reporte", Toast.LENGTH_SHORT).show();
        }
        ReportarClass reportes = new ReportarClass(id,reporte_problema);
        dbreference.child("Reportes_Problemas").child(id).setValue(reportes);
        Toast.makeText(ReportarProblema.this, "Problema registrado exitosamente!",Toast.LENGTH_SHORT).show();
        edt_descr.setText("");
    }

    public void abrir_wsp(View view) {
    }
}