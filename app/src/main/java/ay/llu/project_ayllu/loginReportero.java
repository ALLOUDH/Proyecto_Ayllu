package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginReportero extends AppCompatActivity {
    Button btnIngresarReportero, btnRegistrarseReportero;
    EditText edtDNIReportero, edtContraseñaReportero;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference AylluDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reportero);

        edtDNIReportero = findViewById(R.id.edtDNIReportero);
        edtContraseñaReportero = findViewById(R.id.edtContraseñaReportero);
        btnIngresarReportero = findViewById(R.id.btnIngresarReportero);
        btnRegistrarseReportero = findViewById(R.id.btnRegistrarseReportero);
        edtDNIReportero.setTextColor(getColor(R.color.purple_text));
        edtContraseñaReportero.setTextColor(getColor(R.color.purple_text));

        btnIngresarReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference AylluDatabase = FirebaseDatabase.getInstance().getReference();
                String dni = edtDNIReportero.getText().toString().trim();
                String contra = edtContraseñaReportero.getText().toString().trim();

                if(TextUtils.isEmpty(dni) || TextUtils.isEmpty(contra)){
                    Toast.makeText(loginReportero.this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    AylluDatabase.child("Reporteros").addListenerForSingleValueEvent(new ValueEventListener(){
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(dni)){
                                String getPassword = snapshot.child(dni).child("contraseña").getValue(String.class);
                                if(getPassword.equals(contra)){
                                    Intent i = new Intent(loginReportero.this, MenuReportero.class);
                                    String idreportero = dni;
                                    i.putExtra("dnireportero", idreportero);
                                    Toast.makeText(loginReportero.this, idreportero, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(loginReportero.this,"Bienvenido!!",Toast.LENGTH_SHORT).show();
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                }

                                else {
                                    Toast.makeText(loginReportero.this,"Error al iniciar sesión",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(loginReportero.this,"Datos incorrectos",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        btnRegistrarseReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginReportero.this, SignUpReportero.class);
                startActivity(intent);

            }
        });
    }

    private void iniciarSesionReportero() {

    }
}