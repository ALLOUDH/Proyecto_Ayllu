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

public class ListarProblemasDesigualdad extends AppCompatActivity {
    ListView lstProblemasDesigualdad;
    List<ProblemaClase> listaProblemas = new ArrayList<ProblemaClase>();
    ArrayAdapter<ProblemaClase> arrayAdapterProblemas;
    ProblemaAdapter problemaAdapter;

    private DatabaseReference AylluDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_problemas_desigualdad);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AylluDatabase = database.getReference();

        lstProblemasDesigualdad = findViewById(R.id.lstProblemasDesigualdad);
        listarProblemasDesigualdad();
    }

    private void listarProblemasDesigualdad() {
        AylluDatabase.child("Problemas/Desigualdad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaProblemas.clear();
                int n=0;
                for(DataSnapshot objSnapchot : snapshot.getChildren()){
                    ProblemaClase p = objSnapchot.getValue(ProblemaClase.class);
                    listaProblemas.add(p);
                    problemaAdapter = new ProblemaAdapter(ListarProblemasDesigualdad.this,R.layout.recurso_adapter_problema_categorias,listaProblemas);
                }
                arrayAdapterProblemas = new ArrayAdapter<ProblemaClase>
                        (ListarProblemasDesigualdad.this, android.R.layout.simple_list_item_1,listaProblemas);
                lstProblemasDesigualdad.setAdapter(problemaAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}