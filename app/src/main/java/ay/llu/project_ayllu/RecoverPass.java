package ay.llu.project_ayllu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        SmsManager smsManager = SmsManager.getDefault();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String codigo_verificacion = smsManager.createAppSpecificSmsToken(crear_codigodeverificacion());
        }
    }

    @Override
    public void onClick(View view) {
        crear_codigodeverificacion();
    }

    private PendingIntent crear_codigodeverificacion(){
        return PendingIntent.getActivity(this, 1234, new Intent(this, CodigoVerificacion.class), 0);
    }

}