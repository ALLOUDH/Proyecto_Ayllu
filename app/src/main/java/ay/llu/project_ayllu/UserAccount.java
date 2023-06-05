package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UserAccount extends AppCompatActivity {

    TextView edtCarreraUA;
    EditText edtNombreUA,edtApellidoUA,edtUbicacionUA,edtCorreoUA,edtContraSU,edtCelularUA;
    Spinner spnCarrerasUA;
    Button btnGuardarUA,btnEliminarUA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        edtNombreUA = findViewById(R.id.edt_Nombres_UserAccount);
        edtApellidoUA = findViewById(R.id.edt_Apellidos_UserAccount);
        edtUbicacionUA = findViewById(R.id.edt_Ubicación_UserAccount);
        edtCelularUA = findViewById(R.id.edt_NroCelular_UserAccount);
        edtCarreraUA = findViewById(R.id.txt_CarreraPro_UserAccount);
        edtCorreoUA = findViewById(R.id.edt_Email_UserAccount);
        edtContraSU = findViewById(R.id.edt_Pass_UserAccount);
        spnCarrerasUA = findViewById(R.id.Spinner_UserAccount);
        btnGuardarUA = findViewById(R.id.btn_guardar_cambios_UserAccount);
        btnEliminarUA = findViewById(R.id.btn_eliminar_cuenta_UserAccount);
        Spinner_UserAccount();
    }

    private void Spinner_UserAccount() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Carreras_Profesionales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCarrerasUA.setAdapter(adapter);
    }

    public void llamarEliminar(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(UserAccount.this);
        alerta.setMessage("¿Estas seguro que desea eliminar cuenta?")
                .setCancelable(false)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent report = new Intent(this, MotivoReportar.class);
//                        startActivity(report);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("ELIMINAR");
        titulo.show();
    }
}