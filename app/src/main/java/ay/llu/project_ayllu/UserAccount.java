package ay.llu.project_ayllu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserAccount extends AppCompatActivity {

    TextView txtvCarreraUA,txtvNombreUA,txtvApellidoUA,edtCorreoUA;
    EditText edtContraSU,txtvCelularUA;
    Spinner spnCarrerasUA;
    Button btnGuardarUA,btnEliminarUA;
    DatabaseReference Ayllu;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseUser user;
    String iduser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        Ayllu = FirebaseDatabase.getInstance().getReference("Usuarios");
        iduser = user.getUid();

        txtvNombreUA = findViewById(R.id.txtv_Nombres_UserAccount);
        txtvApellidoUA = findViewById(R.id.txtv_Apellidos_UserAccount);
        txtvCelularUA = findViewById(R.id.txtv_NroCelular_UserAccount);
        txtvCarreraUA = findViewById(R.id.txt_CarreraPro_UserAccount);
        edtCorreoUA = findViewById(R.id.edt_Email_UserAccount);
        btnGuardarUA = findViewById(R.id.btn_guardar_cambios_UserAccount);
        btnEliminarUA = findViewById(R.id.btn_eliminar_cuenta_UserAccount);
        txtvNombreUA.setTextColor(getColor(R.color.purple_text));
        txtvApellidoUA.setTextColor(getColor(R.color.purple_text));
        edtCorreoUA.setTextColor(getColor(R.color.purple_text));
        txtvCelularUA.setTextColor(getColor(R.color.purple_text));
        txtvCarreraUA.setTextColor(getColor(R.color.purple_text));

        Ayllu.child(iduser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario perfil = snapshot.getValue(Usuario.class);

                String nombre = perfil.getNombre();
                String apellido = perfil.getApellido();
                String correo = perfil.getCorreo();
                String celular = perfil.getCelular();
                String carrera = perfil.getCarreraprof();

                txtvNombreUA.setText(nombre);
                txtvApellidoUA.setText(apellido);
                edtCorreoUA.setText(correo);
                txtvCelularUA.setText(celular);
                txtvCarreraUA.setText(carrera);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void llamarEliminar(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(UserAccount.this);
        alerta.setMessage("¿Estas seguro que desea eliminar su cuenta?")
                .setCancelable(false)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminar_cuenta_UA();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("ELIMINAR");
        titulo.show();
    }

    private void actualizar_datos_UA(){
        Usuario usuario = new Usuario();
        usuario.setNombre(txtvNombreUA.getText().toString().trim());
        usuario.setApellido(txtvApellidoUA.getText().toString());
        usuario.setCarreraprof(txtvCarreraUA.getText().toString());
        usuario.setCelular(txtvCelularUA.getText().toString().trim());
        usuario.setCorreo(edtCorreoUA.getText().toString().trim());

        Ayllu.child(iduser).setValue(usuario);
        Toast.makeText(UserAccount.this,"Se han actualizado sus datos correctamente!", Toast.LENGTH_SHORT).show();

    }

    public void llamar_guardar_datos_UA(View view) {
        AlertDialog.Builder alerta_guardar_cambios = new AlertDialog.Builder(UserAccount.this);
        alerta_guardar_cambios.setMessage("¿Estas seguro que desea actualizar sus datos?")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        actualizar_datos_UA();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta_guardar_cambios.create();
        titulo.setTitle("ADVERTENCIA");
        titulo.show();
    }

    public void eliminar_cuenta_UA(){

        Ayllu.child(iduser).removeValue();
        Intent call_roles = new Intent(UserAccount.this, ElegirRol.class);
        startActivity(call_roles);

        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Ayllu.child(iduser).removeValue();
                    Intent call_roles = new Intent(UserAccount.this, ElegirRol.class);
                    startActivity(call_roles);
                    Toast.makeText(UserAccount.this,"Su cuenta ha sido elimada!", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                }
            }
        });

    }
}