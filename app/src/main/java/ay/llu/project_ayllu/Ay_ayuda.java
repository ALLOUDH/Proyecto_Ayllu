package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ay.llu.project_ayllu.RecuperarContrasena.Ay_Contra_recup;

public class Ay_ayuda extends AppCompatActivity {

        ImageView imgcontacto,imgterm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_ayuda);
        imgcontacto=findViewById(R.id.imgcontacto);
        imgterm=findViewById(R.id.imgcterm);

    }
    public void llamar_contacto(View view) {
        Intent call_recover_pass = new Intent(this, ReportarProblema.class);
        startActivity(call_recover_pass);
    }
    public void llamar_term(View view) {
        Intent call_recover_pass = new Intent(this, TermsCondition.class);
        startActivity(call_recover_pass);
    }
}