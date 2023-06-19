package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginAdministrador extends AppCompatActivity {
    Button btnIngresarAdministrador;
    EditText edtCorreoAdministrador, edtContraseñaAdministrador;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);

        mAuth=FirebaseAuth.getInstance();
        edtCorreoAdministrador = findViewById(R.id.edtCorreoAdministrador);
        edtContraseñaAdministrador = findViewById(R.id.edtContraseñaAdministrador);
        btnIngresarAdministrador = findViewById(R.id.btnIngresarAdministrador);
        edtCorreoAdministrador.setTextColor(getColor(R.color.purple_text));
        edtContraseñaAdministrador.setTextColor(getColor(R.color.purple_text));

        btnIngresarAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionAdministrador();
            }
        });

    }

    private void iniciarSesionAdministrador() {
        String iniciarCorreoAdmin = edtCorreoAdministrador.getText().toString().trim();
        String iniciarContrasenaAdmin = edtContraseñaAdministrador.getText().toString().trim();

        if(TextUtils.isEmpty(iniciarCorreoAdmin) || TextUtils.isEmpty(iniciarContrasenaAdmin)){
            Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(iniciarCorreoAdmin,iniciarContrasenaAdmin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(loginAdministrador.this,MenuAdministrador.class));
                    Toast.makeText(loginAdministrador.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(loginAdministrador.this, "Error, correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginAdministrador.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}