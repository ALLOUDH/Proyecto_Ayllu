package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
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
            btnIniciar.setOnClickListener(this);
        }

        public void IniciarSesion(View view) {
            String usuario = edtName.getText().toString();
            String password = edtPass.getText().toString();
            boolean verificado = false;
        }
    @Override
    public void onClick(View view) {
        Intent call_menu = new Intent(this, profile_user.class);
        startActivity(call_menu);
    }
}