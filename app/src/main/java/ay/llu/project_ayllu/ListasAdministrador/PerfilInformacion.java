package ay.llu.project_ayllu.ListasAdministrador;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.R;

public class PerfilInformacion extends AppCompatActivity {
    TextView txtNombreMostrarPerfil,txtApellidoMostrarPerfil,txtCelularMostrarPerfil,
            txtCarreraProfMostrarPerfil,txtCorreoMostrarPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_informacion);

        txtNombreMostrarPerfil= findViewById(R.id.txtNombreMostrarPerfil);
        txtApellidoMostrarPerfil = findViewById(R.id.txtApellidoMostrarPerfil);
        txtCelularMostrarPerfil = findViewById(R.id.txtCelularMostrarPerfil);
        txtCarreraProfMostrarPerfil = findViewById(R.id.txtCarreraProfMostrarPerfil);
        txtCorreoMostrarPerfil = findViewById(R.id.txtCorreoMostrarPerfil);

        String nombre = getIntent().getExtras().getString("nombre");
        String apellido = getIntent().getExtras().getString("apellido");
        String celular = getIntent().getExtras().getString("celular");
        String carrera = getIntent().getExtras().getString("carreraprof");
        String correo = getIntent().getExtras().getString("correo");

        txtNombreMostrarPerfil.setText(nombre);
        txtApellidoMostrarPerfil.setText(apellido);
        txtCelularMostrarPerfil.setText(celular);
        txtCarreraProfMostrarPerfil.setText(carrera);
        txtCorreoMostrarPerfil.setText(correo);
    }
}