package ay.llu.project_ayllu.RecuperarContrasena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import ay.llu.project_ayllu.Login;
import ay.llu.project_ayllu.R;

public class Ay_Contra_recup extends AppCompatActivity implements View.OnClickListener {

    EditText edtNumCelRecoverPass,edtCorreoRecoverPass;
    Button btnAceptarRecoverPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_contra_recup);
        edtNumCelRecoverPass = findViewById(R.id.edtcelrecup);
        edtCorreoRecoverPass = findViewById(R.id.edtemailrecup);
        btnAceptarRecoverPass = findViewById(R.id.btnRecuperarPass);
        btnAceptarRecoverPass.setOnClickListener(this);
//        SmsManager smsManager = SmsManager.getDefault();
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String codigo_verificacion = smsManager.createAppSpecificSmsToken(crear_codigodeverificacion());
//        }
    }

    @Override
    public void onClick(View view) {
        validate();

//        crear_codigodeverificacion();
    }

    private void validate() {
        String email=edtCorreoRecoverPass.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtCorreoRecoverPass.setError("CORREO INVALIDO");
            return;
        }
        sendEmail(email);
    }
    private void sendEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailadress = email;

        auth.sendPasswordResetEmail(emailadress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Ay_Contra_recup.this, "Correo enviado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Ay_Contra_recup.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(Ay_Contra_recup.this, "Correo invalido", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Ay_Contra_recup.this, Login.class);
        startActivity(intent);
        finish();
    }
        private PendingIntent crear_codigodeverificacion(){
        return PendingIntent.getActivity(this, 1234, new Intent(this, Ay_code_verify.class), 0);
    }

}