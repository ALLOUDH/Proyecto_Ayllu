package ay.llu.project_ayllu.ListasAdministrador;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ay.llu.project_ayllu.R;

public class GrupoInformacion extends AppCompatActivity {
    TextView txtTituloGrupoInformacion,txtIDGrupoInformacion,txtFechaMostrarGrupo,
            txtNombre1,txtApellido1,txtCelular1,txtCarreraProf1,txtCorreo1,
            txtNombre2,txtApellido2,txtCelular2,txtCarreraProf2,txtCorreo2,
            txtNombre3,txtApellido3,txtCelular3,txtCarreraProf3,txtCorreo3,
            txtNombre4,txtApellido4,txtCelular4,txtCarreraProf4,txtCorreo4;

    String getnombre1,getapellido1,getcelular1,getcarrera1,getcorreo1,
            getnombre2,getapellido2,getcelular2,getcarrera2,getcorreo2,
            getnombre3,getapellido3,getcelular3,getcarrera3,getcorreo3,
            getnombre4,getapellido4,getcelular4,getcarrera4,getcorreo4;

    String titulo,fecha;
    DatabaseReference AylluDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_informacion);

        txtTituloGrupoInformacion = findViewById(R.id.txtTituloGrupoInformacion);
        txtIDGrupoInformacion = findViewById(R.id.txtIDGrupoInformacion);
        txtFechaMostrarGrupo = findViewById(R.id.txtFechaMostrarGrupo);

        txtNombre1 = findViewById(R.id.txtNombre1);
        txtApellido1 = findViewById(R.id.txtApellido1);
        txtCelular1 = findViewById(R.id.txtCelular1);
        txtCarreraProf1 = findViewById(R.id.txtCarreraProf1);
        txtCorreo1 = findViewById(R.id.txtCorreo1);

        txtNombre2 = findViewById(R.id.txtNombre2);
        txtApellido2 = findViewById(R.id.txtApellido2);
        txtCelular2 = findViewById(R.id.txtCelular2);
        txtCarreraProf2 = findViewById(R.id.txtCarreraProf2);
        txtCorreo2 = findViewById(R.id.txtCorreo2);

        txtNombre3 = findViewById(R.id.txtNombre3);
        txtApellido3 = findViewById(R.id.txtApellido3);
        txtCelular3 = findViewById(R.id.txtCelular3);
        txtCarreraProf3 = findViewById(R.id.txtCarreraProf3);
        txtCorreo3 = findViewById(R.id.txtCorreo3);

        txtNombre4 = findViewById(R.id.txtNombre4);
        txtApellido4 = findViewById(R.id.txtApellido4);
        txtCelular4 = findViewById(R.id.txtCelular4);
        txtCarreraProf4 = findViewById(R.id.txtCarreraProf4);
        txtCorreo4 = findViewById(R.id.txtCorreo4);

        titulo = getIntent().getExtras().getString("titulo");
        String idgrupo = getIntent().getExtras().getString("idgrupo");
        //Los grupos están registrados en base al id del problema (fijate la bd)
        String idproblema = getIntent().getExtras().getString("idproblema");
        fecha = getIntent().getExtras().getString("fecha");
        String iduser1 = getIntent().getExtras().getString("idusuario1");
        String iduser2 = getIntent().getExtras().getString("idusuario2");
        String iduser3 = getIntent().getExtras().getString("idusuario3");
        String iduser4 = getIntent().getExtras().getString("idusuario4");

        txtTituloGrupoInformacion.setText(titulo);
        txtIDGrupoInformacion.setText(idgrupo);
        txtFechaMostrarGrupo.setText(fecha);

        AylluDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(iduser1)){
                    getnombre1 = snapshot.child(iduser1).child("nombre").getValue(String.class);
                    getapellido1 = snapshot.child(iduser1).child("apellido").getValue(String.class);
                    getcelular1 = snapshot.child(iduser1).child("celular").getValue(String.class);
                    getcarrera1 = snapshot.child(iduser1).child("carreraprof").getValue(String.class);
                    getcorreo1 = snapshot.child(iduser1).child("correo").getValue(String.class);

                    txtNombre1.setText(getnombre1);
                    txtApellido1.setText(getapellido1);
                    txtCelular1.setText(getcelular1);
                    txtCarreraProf1.setText(getcarrera1);
                    txtCorreo1.setText(getcorreo1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        AylluDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(iduser2)){
                    getnombre2 = snapshot.child(iduser2).child("nombre").getValue(String.class);
                    getapellido2 = snapshot.child(iduser2).child("apellido").getValue(String.class);
                    getcelular2 = snapshot.child(iduser2).child("celular").getValue(String.class);
                    getcarrera2 = snapshot.child(iduser2).child("carreraprof").getValue(String.class);
                    getcorreo2 = snapshot.child(iduser2).child("correo").getValue(String.class);

                    txtNombre2.setText(getnombre2);
                    txtApellido2.setText(getapellido2);
                    txtCelular2.setText(getcelular2);
                    txtCarreraProf2.setText(getcarrera2);
                    txtCorreo2.setText(getcorreo2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        AylluDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(iduser3)){
                    getnombre3 = snapshot.child(iduser3).child("nombre").getValue(String.class);
                    getapellido3 = snapshot.child(iduser3).child("apellido").getValue(String.class);
                    getcelular3 = snapshot.child(iduser3).child("celular").getValue(String.class);
                    getcarrera3 = snapshot.child(iduser3).child("carreraprof").getValue(String.class);
                    getcorreo3 = snapshot.child(iduser3).child("correo").getValue(String.class);

                    txtNombre3.setText(getnombre3);
                    txtApellido3.setText(getapellido3);
                    txtCelular3.setText(getcelular3);
                    txtCarreraProf3.setText(getcarrera3);
                    txtCorreo3.setText(getcorreo3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        AylluDatabase.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(iduser4)){
                    getnombre4 = snapshot.child(iduser4).child("nombre").getValue(String.class);
                    getapellido4 = snapshot.child(iduser4).child("apellido").getValue(String.class);
                    getcelular4 = snapshot.child(iduser4).child("celular").getValue(String.class);
                    getcarrera4 = snapshot.child(iduser4).child("carreraprof").getValue(String.class);
                    getcorreo4 = snapshot.child(iduser4).child("correo").getValue(String.class);

                    txtNombre4.setText(getnombre4);
                    txtApellido4.setText(getapellido4);
                    txtCelular4.setText(getcelular4);
                    txtCarreraProf4.setText(getcarrera4);
                    txtCorreo4.setText(getcorreo4);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}