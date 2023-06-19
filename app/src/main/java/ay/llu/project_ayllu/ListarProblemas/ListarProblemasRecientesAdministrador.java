package ay.llu.project_ayllu.ListarProblemas;

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
import ay.llu.project_ayllu.RegistrarProblema.ProblemaClase;

public class ListarProblemasRecientesAdministrador extends AppCompatActivity {

    ListView lstProblemasAdministrador;
    List<ProblemaClase> listaProblemas = new ArrayList<ProblemaClase>();
    ArrayAdapter<ProblemaClase> arrayAdapterProblemas;
    ProblemaAdapterAdministradorR problemaAdapterA;

    private DatabaseReference AylluDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_problemas_recientes_administrador);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AylluDatabase = database.getReference();

        lstProblemasAdministrador = findViewById(R.id.lstProblemasUsuarioAdministrador);
        listarProblemas();
    }

    private void listarProblemas() {
        AylluDatabase.child("Problemas_Recientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaProblemas.clear();
                int n=0;
                for(DataSnapshot objSnapchot : snapshot.getChildren()){
                    ProblemaClase p = objSnapchot.getValue(ProblemaClase.class);
                    listaProblemas.add(p);
                    problemaAdapterA = new ProblemaAdapterAdministradorR(ListarProblemasRecientesAdministrador.this,R.layout.recurso_adapter_problema,listaProblemas);
                }
                arrayAdapterProblemas = new ArrayAdapter<ProblemaClase>
                        (ListarProblemasRecientesAdministrador.this, android.R.layout.simple_list_item_1,listaProblemas);
                lstProblemasAdministrador.setAdapter(problemaAdapterA);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}