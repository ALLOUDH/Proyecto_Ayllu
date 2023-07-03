/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu.ReportarGroup;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ay.llu.project_ayllu.R;

public class MotivoReportar extends AppCompatActivity {

    Spinner spinReport;
    TextView txtInclum;
    EditText edtMotivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivo_reportar);
        edtMotivo = findViewById(R.id.edtMotivo);
        spinReport = findViewById(R.id.SpinnerReportar);
        txtInclum =findViewById(R.id.txtIncumplimiento);
        SpinnerReportar();

    }

    private void SpinnerReportar() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.MotivodeReportar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinReport.setAdapter(adapter);
    }
}