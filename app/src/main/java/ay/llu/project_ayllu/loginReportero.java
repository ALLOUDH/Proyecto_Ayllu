package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class loginReportero extends AppCompatActivity {
    Button btnIngresarReportero, btnRegistrarseReportero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reportero);

        btnIngresarReportero = findViewById(R.id.btnIngresarReportero);
        btnRegistrarseReportero = findViewById(R.id.btnRegistrarseReportero);

        btnIngresarReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginReportero.this, MenuReportero.class);
                startActivity(intent);
            }
        });
        btnRegistrarseReportero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginReportero.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}