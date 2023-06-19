package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.ListarProblemas.ListarProblemasRecientesAdministrador;

public class MenuAdministrador extends AppCompatActivity {
    ImageView imgcardProblemasRecientesAdministrador,imgcardProblemasValorados,imgcardPerfiles,imgcardGruposConformados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);

        imgcardProblemasRecientesAdministrador = findViewById(R.id.imgcardProblemasRecientesAdministrador);
        imgcardProblemasValorados = findViewById(R.id.imgcardProblemasValorados);
        imgcardPerfiles = findViewById(R.id.imgcardPerfiles);
        imgcardGruposConformados = findViewById(R.id.imgcardGruposConformados);

        imgcardProblemasRecientesAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAdministrador.this, ListarProblemasRecientesAdministrador.class);
                startActivity(intent);
            }
        });
        imgcardProblemasValorados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgcardPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgcardGruposConformados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}