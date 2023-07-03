/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu.RecuperarContrasena;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.R;

public class Ay_new_pass extends AppCompatActivity implements View.OnClickListener {

    EditText edtnewpass, edtnewpass2;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_new_pass);
        edtnewpass=findViewById(R.id.edtnewpass);
        edtnewpass2=findViewById(R.id.edtnewpass2);
        btnguardar=findViewById(R.id.btnGuardarPass);
        btnguardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}