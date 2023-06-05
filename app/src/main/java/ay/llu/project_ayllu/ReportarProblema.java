package ay.llu.project_ayllu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportarProblema extends AppCompatActivity {
    EditText edtReportarProblema;
    Button btnEnviarProblema;
    private DatabaseReference mDatabase;
    ImageButton imgReportarProblemaWhatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema);

        edtReportarProblema = findViewById(R.id.edtReportarProblema);
        btnEnviarProblema = findViewById(R.id.btnEnviarProblema);
        imgReportarProblemaWhatsapp = findViewById(R.id.imgReportarProblemaWSP);
        boolean installed = appInstaladaOrNot("com.whatsapp");

        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference();

        btnEnviarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirProblema();
            }
        });

        imgReportarProblemaWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+51"+"991788390"));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ReportarProblema.this, "Whatsapp no esta instalado en tu dispositivo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean appInstaladaOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }

    private void subirProblema() {
        String comentarioProblema = edtReportarProblema.getText().toString();
        String id = mDatabase.push().getKey();

        if(TextUtils.isEmpty(comentarioProblema)){
            Toast.makeText(this, "Ingrese el problema", Toast.LENGTH_SHORT).show();
            return;
        }
        Problema unProblema = new Problema(id,comentarioProblema);
        mDatabase.child("Problemas_Usuarios").child(id).setValue(unProblema);
        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
        edtReportarProblema.setText("");

    }
}