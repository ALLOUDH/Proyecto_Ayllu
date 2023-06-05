package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.ListarProblemas.ListarProblemas;

public class MainActivity extends AppCompatActivity {
    ImageView imgcardProblemasRecientes,imgcardCategorias,
              imgcardPerfil,imgcardConfiguracion,imgcardAyuda,
              imgcardNosotros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgcardProblemasRecientes = findViewById(R.id.imgcardProblemasRecientes);
        imgcardCategorias = findViewById(R.id.imgcardCategorias);
        imgcardPerfil = findViewById(R.id.imgcardConfiguracion);
        imgcardConfiguracion = findViewById(R.id.imgcardConfiguracion);
        imgcardAyuda = findViewById(R.id.imgcardAyuda);
        imgcardNosotros = findViewById(R.id.imgcardNosotros);

        imgcardProblemasRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarProblemas.class);
                startActivity(intent);
            }
        });
        imgcardCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MostrarCategorias.class);
                startActivity(intent);
            }
        });
        imgcardPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfiles.class);
                startActivity(intent);
            }
        });
        imgcardConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgcardAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgcardNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}