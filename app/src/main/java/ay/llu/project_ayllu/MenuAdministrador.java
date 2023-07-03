/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.ListarProblemas.ListarProblemasRecientesAdministrador;
import ay.llu.project_ayllu.ListarProblemas.ListarProblemasValoradosAdministrdor;
import ay.llu.project_ayllu.ListasAdministrador.ListarGrupos;
import ay.llu.project_ayllu.ListasAdministrador.ListarPerfilesUsuarios;
import ay.llu.project_ayllu.ListasAdministrador.ListarReportesClientes;

public class MenuAdministrador extends AppCompatActivity {
    ImageView imgcardProblemasRecientesAdministrador,imgcardProblemasValorados,imgcardPerfiles,imgcardGruposConformados,imgcardSoporteCliente,imgcardWebScraping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);

        imgcardProblemasRecientesAdministrador = findViewById(R.id.imgcardProblemasRecientesAdministrador);
        imgcardProblemasValorados = findViewById(R.id.imgcardProblemasValorados);
        imgcardPerfiles = findViewById(R.id.imgcardPerfiles);
        imgcardGruposConformados = findViewById(R.id.imgcardGruposConformados);
        imgcardSoporteCliente=findViewById(R.id.imgcardSoporteCliente);
        imgcardWebScraping = findViewById(R.id.imgcardWebScraping);

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
                Intent intent = new Intent(MenuAdministrador.this, ListarProblemasValoradosAdministrdor.class);
                startActivity(intent);
            }
        });
        imgcardPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAdministrador.this, ListarPerfilesUsuarios.class);
                startActivity(intent);
            }
        });
        imgcardGruposConformados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAdministrador.this, ListarGrupos.class);
                startActivity(intent);
            }
        });
        imgcardSoporteCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAdministrador.this, ListarReportesClientes.class);
                startActivity(intent);
            }
        });
        imgcardWebScraping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdministrador.this, WebScrapping.class);
                startActivity(intent);
            }
        });
    }
}