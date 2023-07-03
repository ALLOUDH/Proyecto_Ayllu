/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ay.llu.project_ayllu.ListasAdministrador.ReportarClass;

public class ReportarProblema extends AppCompatActivity implements View.OnClickListener {
    EditText edt_descr;
    TextView preguntasFrecuentes;
    Button btn_enviar;
    ImageView btn_wsp;
    FirebaseDatabase database;
    private DatabaseReference dbreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema);
        edt_descr = findViewById(R.id.edtReportarProblema);
        preguntasFrecuentes = findViewById(R.id.preguntasFrecuentes);
        btn_enviar = findViewById(R.id.btnEnviar);
        btn_wsp = findViewById(R.id.btn_wsp);
        btn_enviar.setOnClickListener(this);
        dbreference = FirebaseDatabase.getInstance().getReference();
        edt_descr.setTextColor(Color.WHITE);

        preguntasFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportarProblema.this, PreguntasFrecuentes.class);
                startActivity(intent);
            }
        });

        btn_wsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+"+51"+"959933665"));
                startActivity(intent);
            }
        });
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
}