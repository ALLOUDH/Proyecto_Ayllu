package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    Spinner spinCarreras;
    EditText edtNombreSU,edtApellidoSU,edtCorreoSU,edtContraSU,edtConfirmPassSU;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spinCarreras = findViewById(R.id.Spinner_SignUp);
        edtNombreSU = findViewById(R.id.edt_Nombres_SignUp);
        edtApellidoSU = findViewById(R.id.edt_Apellidos_SignUp);
        edtCorreoSU = findViewById(R.id.edt_Email_SignUp);
        edtContraSU = findViewById(R.id.edt_Pass_SignUp);
        edtConfirmPassSU = findViewById(R.id.edt_ConfirmPass_SignUp);
        btnRegistrar = findViewById(R.id.btn_Registrar_SignUp);
        btnRegistrar.setOnClickListener(this);
        Spinner_Signup();
    }
    private void Spinner_Signup() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Carreras_Profesionales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCarreras.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent call_login = new Intent(this, Login.class);
        startActivity(call_login);
    }
}