package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.ListarProblemas.ListarProblemasReportero;
import ay.llu.project_ayllu.MapsProblema.SeleccionarUbicacionProblema;

public class MenuReportero extends AppCompatActivity {
    ImageView imgcardRegistrarProblemaSocial,imgcardProblemasRecientesReportero,imgcardAyudaReportero,imgcardNosotrosReportero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reportero);

        imgcardRegistrarProblemaSocial = findViewById(R.id.imgcardRegistrarProblemaSocial);
        imgcardProblemasRecientesReportero = findViewById(R.id.imgcardProblemasRecientesReportero);
        imgcardAyudaReportero = findViewById(R.id.imgcardAyudaReportero);
        imgcardNosotrosReportero = findViewById(R.id.imgcardNosotrosReportero);

        String dnireportero = getIntent().getExtras().getString("dnireportero");

        imgcardRegistrarProblemaSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportero.this, SeleccionarUbicacionProblema.class);
                String idreportero = dnireportero;
                intent.putExtra("dnireportero", idreportero);
                startActivity(intent);
            }
        });
        imgcardProblemasRecientesReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportero.this, ListarProblemasReportero.class);
                startActivity(intent);
            }
        });
        imgcardAyudaReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportero.this, Ay_ayuda.class);
                startActivity(intent);
            }
        });
        imgcardNosotrosReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportero.this, AcercaNosotros.class);
                startActivity(intent);
            }
        });

    }
}