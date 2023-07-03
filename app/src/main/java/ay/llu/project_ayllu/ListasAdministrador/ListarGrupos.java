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

import ay.llu.project_ayllu.GrupoClase;
import ay.llu.project_ayllu.R;

public class ListarGrupos extends AppCompatActivity {
    ListView lstGrupos;
    List<GrupoClase> listaGrupos = new ArrayList<GrupoClase>();
    ArrayAdapter<GrupoClase> arrayAdapterReportes;
    ListarGruposAdapter gruposAdapter;

    private DatabaseReference AylluDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_grupos);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AylluDatabase = database.getReference();

        lstGrupos = findViewById(R.id.lstGrupos);
        listarGrupos();
    }

    private void listarGrupos() {
        AylluDatabase.child("Grupos_Conformados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaGrupos.clear();
                int n=0;
                for(DataSnapshot objSnapchot : snapshot.getChildren()){
                    GrupoClase p = objSnapchot.getValue(GrupoClase.class);
                    listaGrupos.add(p);
                    gruposAdapter = new ListarGruposAdapter(ListarGrupos.this,R.layout.recurso_adapter_listar_grupos,listaGrupos);
                }
                arrayAdapterReportes = new ArrayAdapter<GrupoClase>
                        (ListarGrupos.this, android.R.layout.simple_list_item_1,listaGrupos);
                lstGrupos.setAdapter(gruposAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}