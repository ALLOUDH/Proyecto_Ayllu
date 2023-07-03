package ay.llu.project_ayllu.ListasAdministrador;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

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
import ay.llu.project_ayllu.Usuario;

public class ListarPerfilesUsuarios extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView lstPerfilesUsuarios;

    SearchView txtBuscarUsuario;
    List<Usuario> listaPerfiles = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterPerfiles;
    PerfilesUsuariosAdapter perfilesAdapter;

    private DatabaseReference AylluDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_perfiles_usuarios);

        txtBuscarUsuario = findViewById(R.id.txtBuscarUsuario);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AylluDatabase = database.getReference();

        txtBuscarUsuario.setOnQueryTextListener(this);

        lstPerfilesUsuarios = findViewById(R.id.lstPerfilesUsuarios);
        listarPerfiles();
    }

    private void listarPerfiles() {
        AylluDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listaPerfiles.clear();
                int n=0;
                for(DataSnapshot objSnapchot : snapshot.getChildren()){
                    Usuario p = objSnapchot.getValue(Usuario.class);
                    listaPerfiles.add(p);
                    perfilesAdapter = new PerfilesUsuariosAdapter(ListarPerfilesUsuarios.this,R.layout.recurso_adapter_perfiles_usuarios,listaPerfiles);
                }
                arrayAdapterPerfiles = new ArrayAdapter<Usuario>
                        (ListarPerfilesUsuarios.this, android.R.layout.simple_list_item_1,listaPerfiles);
                lstPerfilesUsuarios.setAdapter(perfilesAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        perfilesAdapter.filtrado(s);
        return false;
    }
}