package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ay.llu.project_ayllu.RecuperarContrasena.Ay_Contra_recup;

public class Login extends AppCompatActivity {
    EditText edtName, edtPass;

    TextView txtOlvidaste;

    Button btnIniciar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        edtName=findViewById(R.id.edtCorreoUsuario);
        edtPass=findViewById(R.id.edtContraseñaUsuario);
        txtOlvidaste=findViewById(R.id.txtOlvidasteUsuario);
        btnIniciar=findViewById(R.id.btnIngresarUsuario);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = edtName.getText().toString().trim();
                String passUser = edtPass.getText().toString().trim();
                if (emailUser.isEmpty()&&passUser.isEmpty()){
                    Toast.makeText(Login.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();

                }else {
                    LoginUser(emailUser,passUser);
                }
            }
        });

    }

    private void LoginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this,MainActivity.class));
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Error, correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void llamar_menu_login(View view) {
        Intent call_menu = new Intent(this, UserProfiles.class);
        startActivity(call_menu);
    }
    public void llamar_signup_login(View view) {
        Intent call_recover_pass = new Intent(this, SignUp.class);
        startActivity(call_recover_pass);
    }
    public void llamar_recover_pass_login(View view) {
        Intent call_recover_pass = new Intent(this, Ay_Contra_recup.class);
        startActivity(call_recover_pass);
    }
}


