package ay.llu.project_ayllu.ListarProblemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ay.llu.project_ayllu.ProblemaClase;
import ay.llu.project_ayllu.R;

public class ListarProblemasDelincuencia extends AppCompatActivity {
    ListView lstProblemasDelincuencia;
    List<ProblemaClase> listaProblemas = new ArrayList<ProblemaClase>();
    ArrayAdapter<ProblemaClase> arrayAdapterProblemas;
    ProblemaAdapter problemaAdapter;

    private DatabaseReference AylluDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_problemas_delincuencia);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AylluDatabase = database.getReference();

        lstProblemasDelincuencia = findViewById(R.id.lstProblemasDelincuencia);
        listarProblemasDelincuencia();
    }

    private void listarProblemasDelincuencia() {
        AylluDatabase.child("Problemas/Delincuencia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaProblemas.clear();
                int n=0;
                for(DataSnapshot objSnapchot : snapshot.getChildren()){
                    ProblemaClase p = objSnapchot.getValue(ProblemaClase.class);
                    listaProblemas.add(p);
                    problemaAdapter = new ProblemaAdapter(ListarProblemasDelincuencia.this,R.layout.recurso_adapter_problema,listaProblemas);
                }
                arrayAdapterProblemas = new ArrayAdapter<ProblemaClase>
                        (ListarProblemasDelincuencia.this, android.R.layout.simple_list_item_1,listaProblemas);
                lstProblemasDelincuencia.setAdapter(problemaAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}