package ay.llu.project_ayllu;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfiles extends AppCompatActivity {
    EditText user_descr;

    TextView txtNombre, txtApellido, txtCarrera, txtCorreo, txtCelular;

    DatabaseReference Ayllu;
    FirebaseAuth auth;
    FirebaseUser user;
    String iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profiles);
        user_descr = findViewById(R.id.edt_userdescr);
        user_descr.setTextColor(getColor(R.color.white));
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        Ayllu = FirebaseDatabase.getInstance().getReference("Usuarios");
        iduser = user.getUid();

        txtNombre = findViewById(R.id.textNombre);
        txtApellido = findViewById(R.id.textApellido);
        txtCarrera = findViewById(R.id.textCarrera);
        txtCorreo = findViewById(R.id.textCorreo);
        txtCelular = findViewById(R.id.textCelular);

        Ayllu.child(iduser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario perfil = snapshot.getValue(Usuario.class);

                String nombre = perfil.getNombre();
                String apellido = perfil.getApellido();
                String correo = perfil.getCorreo();
                String celular = perfil.getCelular();
                String carrera = perfil.getCarreraprof();

                txtNombre.setText(nombre);
                txtApellido.setText(apellido);
                txtCarrera.setText(carrera);
                txtCorreo.setText(correo);
                txtCelular.setText(celular);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}