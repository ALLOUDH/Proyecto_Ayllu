/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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