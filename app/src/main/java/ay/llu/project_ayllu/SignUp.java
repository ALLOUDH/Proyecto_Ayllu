package ay.llu.project_ayllu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    Spinner spinCarreras;
    EditText edtNombreSU,edtApellidoSU,edtCorreoSU,edtContraSU,edtConfirmPassSU,edtNumeroCel;
    TextView txtCarreraProf;
    Button btnRegistrar;
    DatabaseReference Ayllu;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        spinCarreras = findViewById(R.id.Spinner_SignUp);
        edtNombreSU = findViewById(R.id.edt_Nombres_SignUp);
        edtApellidoSU = findViewById(R.id.edt_Apellidos_SignUp);
        edtNumeroCel = findViewById(R.id.edt_NroCelular_SignUp);
        edtCorreoSU = findViewById(R.id.edt_Email_SignUp);
        edtContraSU = findViewById(R.id.edt_Pass_SignUp);
        edtConfirmPassSU = findViewById(R.id.edt_ConfirmPass_SignUp);
        txtCarreraProf = findViewById(R.id.txtCarreraPro_SignUp);
        btnRegistrar = findViewById(R.id.btn_Registrar_SignUp);
        btnRegistrar.setOnClickListener(this);
        Ayllu = FirebaseDatabase.getInstance().getReference();
        listar_carreras();
    }
    public void listar_carreras(){
        final List<Carreras> carreras = new ArrayList<>();
        Ayllu.child("Carreras_Profesionales").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        carreras.add(new Carreras(id, nombre));
                    }
                    ArrayAdapter<Carreras> arrayAdapter = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_dropdown_item_1line, carreras);
                    spinCarreras.setAdapter(arrayAdapter);
                    spinCarreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String CarreraSelect = adapterView.getItemAtPosition(i).toString();
                            txtCarreraProf.setText(CarreraSelect);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        String id = auth.getCurrentUser().getUid();
        String nombreUser = edtNombreSU.getText().toString().trim();
        String apellidoUser = edtApellidoSU.getText().toString().trim();
        String numeroCeluUser = edtNumeroCel.getText().toString().trim();
        String carreraUser = txtCarreraProf.getText().toString().trim();
        String correoUser = edtCorreoSU.getText().toString().trim();
        String contraUser = edtContraSU.getText().toString().trim();
        String confcontraUser = edtConfirmPassSU.getText().toString().trim();

        if (nombreUser.isEmpty()){
            edtNombreSU.setError("Ingrese su nombre");
        }
        if (apellidoUser.isEmpty()){
            edtApellidoSU.setError("Ingrese su Apellido");
        }
        if (numeroCeluUser.isEmpty()){
            edtNumeroCel.setError("Ingrese su Nro. de Celular");
        }
        if (correoUser.isEmpty()){
            edtCorreoSU.setError("Ingrese su correo");
        }
        if (contraUser.isEmpty()){
            edtContraSU.setError("Ingrese su contraseña");
        }
        if (confcontraUser.isEmpty()){
            edtConfirmPassSU.setError("Confirme su contraseña");
        }
        if (nombreUser.isEmpty() || apellidoUser.isEmpty() || numeroCeluUser.isEmpty() || correoUser.isEmpty() || contraUser.isEmpty() || confcontraUser.isEmpty()){
            Toast.makeText(this, "Usted necesita rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if (confcontraUser.equals(contraUser)){
                registrarUsuario(id,nombreUser,apellidoUser,correoUser,numeroCeluUser,carreraUser,contraUser);
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void registrarUsuario(String id, String  nombreUser, String apellidoUser, String correoUser, String numeroCeluUser,String carreraUser,String contraUser) {
        auth.createUserWithEmailAndPassword(correoUser,contraUser).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Usuario usuario = new Usuario(id,nombreUser,apellidoUser,correoUser,numeroCeluUser,carreraUser);
                    Ayllu.child("Usuarios").child(id).setValue(usuario);
                    Toast.makeText(SignUp.this,"Se ha creado su cuenta exitosamente!", Toast.LENGTH_SHORT).show();
                    Intent call_login = new Intent(SignUp.this, Login.class);
                    startActivity(call_login);
                }else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SignUp.this,"El usuario ya existe", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignUp.this,"Lo sentimos, no pudimos registrar su cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}