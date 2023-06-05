package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.ListarProblemas.ListarProblemas;

public class ElegirRol extends AppCompatActivity {
    ImageView imgcardUsuario,imgcardReportero,imgcardAdministrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_rol);

        imgcardUsuario = findViewById(R.id.imgcardUsuario);
        imgcardReportero = findViewById(R.id.imgcardReportero);
        imgcardAdministrador = findViewById(R.id.imgcardAdministrador);

        imgcardUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElegirRol.this, Login.class);
                startActivity(intent);
            }
        });

        imgcardReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElegirRol.this, loginReportero.class);
                startActivity(intent);
            }
        });
        imgcardAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElegirRol.this, loginAdministrador.class);
                startActivity(intent);
            }
        });
    }
}