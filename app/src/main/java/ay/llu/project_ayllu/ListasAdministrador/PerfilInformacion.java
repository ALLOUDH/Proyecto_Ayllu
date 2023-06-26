package ay.llu.project_ayllu.ListasAdministrador;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import ay.llu.project_ayllu.ElegirRol;
import ay.llu.project_ayllu.InformacionProblema.ProblemaInformacionAdministrador;
import ay.llu.project_ayllu.ListarProblemas.ListarProblemasRecientesAdministrador;
import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.UserAccount;
import ay.llu.project_ayllu.Usuario;

public class PerfilInformacion extends AppCompatActivity {
    TextView txtNombreMostrarPerfil,txtApellidoMostrarPerfil,txtCelularMostrarPerfil,
            txtCarreraProfMostrarPerfil,txtCorreoMostrarPerfil;

    Button btnEliminarPerfil;
    DatabaseReference Ayllu;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_informacion);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        Ayllu = FirebaseDatabase.getInstance().getReference("Usuarios");

        txtNombreMostrarPerfil= findViewById(R.id.txtNombreMostrarPerfil);
        txtApellidoMostrarPerfil = findViewById(R.id.txtApellidoMostrarPerfil);
        txtCelularMostrarPerfil = findViewById(R.id.txtCelularMostrarPerfil);
        txtCarreraProfMostrarPerfil = findViewById(R.id.txtCarreraProfMostrarPerfil);
        txtCorreoMostrarPerfil = findViewById(R.id.txtCorreoMostrarPerfil);
        btnEliminarPerfil = findViewById(R.id.btnEliminarPerfil);

        String iduser = getIntent().getStringExtra("iduser");
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

        btnEliminarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(PerfilInformacion.this);
                alerta.setMessage("¿Estas seguro que desea eliminar el problema reportado?")
                        .setCancelable(false)
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                eliminar_perfil();
                            }

                            private void eliminar_perfil() {
                                Ayllu.child(iduser).removeValue();
                                Intent call_roles = new Intent(PerfilInformacion.this, ListarPerfilesUsuarios.class);
                                startActivity(call_roles);
                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Ayllu.child(iduser).removeValue();
                                            Intent call_roles = new Intent(PerfilInformacion.this, ListarPerfilesUsuarios.class);
                                            startActivity(call_roles);
                                            Toast.makeText(PerfilInformacion.this,"Su cuenta ha sido elimada!", Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("ELIMINAR");
                titulo.show();
            }
        });
    }
}