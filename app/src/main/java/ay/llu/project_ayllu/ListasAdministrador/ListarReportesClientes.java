/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu.ListasAdministrador;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ay.llu.project_ayllu.R;

public class ListarReportesClientes extends AppCompatActivity {

    ListView lstReporteCliente;
    List<ReportarClass> listaReportes = new ArrayList<ReportarClass>();
    ArrayAdapter<ReportarClass> arrayAdapterReportes;
    ReporteClienteAdapter reporteAdapter;

    private DatabaseReference AylluDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_reportes_clientes);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AylluDatabase = database.getReference();

        lstReporteCliente = findViewById(R.id.lstReporteCliente);
        listarReportes();
    }

    private void listarReportes() {
        AylluDatabase.child("Reportes_Problemas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaReportes.clear();
                int n=0;
                for(DataSnapshot objSnapchot : snapshot.getChildren()){
                    ReportarClass p = objSnapchot.getValue(ReportarClass.class);
                    listaReportes.add(p);
                    reporteAdapter = new ReporteClienteAdapter(ListarReportesClientes.this,R.layout.recurso_adapter_reportes_clientes,listaReportes);
                }
                arrayAdapterReportes = new ArrayAdapter<ReportarClass>
                        (ListarReportesClientes.this, android.R.layout.simple_list_item_1,listaReportes);
                lstReporteCliente.setAdapter(reporteAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}