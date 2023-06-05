package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
        EditText edtName, edtPass;

        TextView txtOlvidaste;

        Button btnIniciar, btnRegistrarseUsuario;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            edtName=findViewById(R.id.edtCorreoUsuario);
            edtPass=findViewById(R.id.edtContraseñaUsuario);
            txtOlvidaste=findViewById(R.id.txtOlvidasteUsuario);
            btnIniciar=findViewById(R.id.btnIngresarUsuario);
            btnRegistrarseUsuario = findViewById(R.id.btnRegistrarseUsuario);

            btnIniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            btnRegistrarseUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, SignUp.class);
                    startActivity(intent);
                }
            });
        }

        public void IniciarSesion(View view) {
            String usuario = edtName.getText().toString();
            String password = edtPass.getText().toString();
            boolean verificado = false;
        }

        public void llamar_recover_pass_login(View view) {
            Intent call_recover_pass = new Intent(this, RecoverPass.class);
            startActivity(call_recover_pass);
        }
}