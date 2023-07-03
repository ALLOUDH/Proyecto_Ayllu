/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.ListarProblemas.ListarProblemasContaminacion;
import ay.llu.project_ayllu.ListarProblemas.ListarProblemasDelincuencia;
import ay.llu.project_ayllu.ListarProblemas.ListarProblemasDesigualdad;
import ay.llu.project_ayllu.ListarProblemas.ListarProblemasPobreza;

public class MostrarCategorias extends AppCompatActivity {
    Button btnContaminacion, btnDelincuencia, btnDesigualdad, btnPobreza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_categorias);

        btnContaminacion = findViewById(R.id.btnContaminacion);
        btnDelincuencia = findViewById(R.id.btnDelincuencia);
        btnDesigualdad = findViewById(R.id.btnDesigualdad);
        btnPobreza = findViewById(R.id.btnPobreza);

        btnContaminacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarCategorias.this, ListarProblemasContaminacion.class);
                startActivity(intent);
            }
        });
        btnDelincuencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarCategorias.this, ListarProblemasDelincuencia.class);
                startActivity(intent);
            }
        });
        btnDesigualdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarCategorias.this, ListarProblemasDesigualdad.class);
                startActivity(intent);
            }
        });
        btnPobreza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarCategorias.this, ListarProblemasPobreza.class);
                startActivity(intent);
            }
        });
    }
}