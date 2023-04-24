package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecoverPass extends AppCompatActivity implements View.OnClickListener {
    EditText edtNumCelRecoverPass,edtCorreoRecoverPass;
    Button btnAceptarRecoverPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pass);
        edtNumCelRecoverPass = findViewById(R.id.edt_NroCelular_RecoverPass);
        edtCorreoRecoverPass = findViewById(R.id.edt_Email_RecoverPass);
        btnAceptarRecoverPass = findViewById(R.id.btn_Aceptar_RecoverPass);
        btnAceptarRecoverPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}