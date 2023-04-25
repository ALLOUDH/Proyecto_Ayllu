package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
        EditText edtName, edtPass;

        TextView txtOlvidaste;

        Button btnIniciar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            edtName=findViewById(R.id.edtUserName);
            edtPass=findViewById(R.id.edtPassword);
            txtOlvidaste=findViewById(R.id.txtOlvidaste);
            btnIniciar=findViewById(R.id.btnIniciar);
        }

        public void IniciarSesion(View view) {
            String usuario = edtName.getText().toString();
            String password = edtPass.getText().toString();
            boolean verificado = false;
        }

    public void llamar_signup_login(View view) {
        Intent call_sign_up_login = new Intent(this, SignUp.class);
        startActivity(call_sign_up_login);
    }

    public void llamar_menu_login(View view) {
        Intent call_menu = new Intent(this, profile_user.class);
        startActivity(call_menu);
    }
    public void llamar_recover_pass_login(View view) {
        Intent call_recover_pass = new Intent(this, RecoverPass.class);
        startActivity(call_recover_pass);
    }
}