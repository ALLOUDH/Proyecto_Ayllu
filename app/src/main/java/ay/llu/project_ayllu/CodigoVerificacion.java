package ay.llu.project_ayllu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.EditText;

public class CodigoVerificacion extends AppCompatActivity {

    EditText edtcodeverificacion;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_verificacion);
        SmsManager smsManager = SmsManager.getDefault();
        String codigo_verificacion = smsManager.createAppSpecificSmsToken(crear_codigodeverificacion());

        edtcodeverificacion.setText(codigo_verificacion);

        Log.i("Este es tu codigo", "sms codigo "+codigo_verificacion);
    }

    private PendingIntent crear_codigodeverificacion(){
        return PendingIntent.getActivity(this, 1234, new Intent(this, CodigoVerificacion.class), 0);
    }
}