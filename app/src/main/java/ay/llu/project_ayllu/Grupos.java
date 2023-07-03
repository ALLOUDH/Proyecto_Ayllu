/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import ay.llu.project_ayllu.chat.ChatGrupo;

public class Grupos extends AppCompatActivity {

    TextView txtvcategoria_titulo;
    TextView txtvtitulo_problema;
    TextView txtvuser_1,txtvuser_2,txtvuser_3,txtvuser_4,txtvuser_5,txtvuser_6;
    TextView txtvcarrera_user1,txtvcarrera_user2,txtvcarrera_user3,txtvcarrera_user4,txtvcarrera_user5,txtvcarrera_user6;
    Button btn_unirme;
    String fechaActual;
    DatabaseReference Ayllu;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseUser user;
    String iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        txtvtitulo_problema = findViewById(R.id.txtTituloGrupo);
        btn_unirme = findViewById(R.id.btn_unirme_grupo);

        String idproblema = getIntent().getExtras().getString("idproblema");
        String titulo= getIntent().getExtras().getString("tituloGrupo");

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseApp.initializeApp(this);
        Ayllu = FirebaseDatabase.getInstance().getReference();
        iduser = user.getUid();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        fechaActual = day + "/" + (month + 1) + "/" + year;

        txtvtitulo_problema.setText(titulo);
        btn_unirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirGrupo();
            }
        });


//        txtvuser_1 = findViewById(R.id.txt_grupo_user1_name);
//        txtvuser_2 = findViewById(R.id.txtv_grupo_user2_carrera);
//        txtvuser_3 = findViewById(R.id.txtv_grupo_user3_carrera);
//        txtvuser_4 = findViewById(R.id.txtv_grupo_user4_carrera);
//        txtvuser_5 = findViewById(R.id.txtv_grupo_user5_carrera);
//        txtvuser_6 = findViewById(R.id.txtv_grupo_user6_carrera);
//        txtvcarrera_user1 = findViewById(R.id.txtv_grupo_user1_carrera);
//        txtvcarrera_user2 = findViewById(R.id.txtv_grupo_user2_carrera);
//        txtvcarrera_user3 = findViewById(R.id.txtv_grupo_user3_carrera);
//        txtvcarrera_user4 = findViewById(R.id.txtv_grupo_user4_carrera);
//        txtvcarrera_user5 = findViewById(R.id.txtv_grupo_user5_carrera);
//        txtvcarrera_user6 = findViewById(R.id.txtv_grupo_user6_carrera);
//        btn_unirme = findViewById(R.id.btn_unirme_grupo);
//        btn_unirme.setOnClickListener(this);
    }

    private void añadirGrupo() {
        String id = Ayllu.push().getKey();
        String idproblema = getIntent().getExtras().getString("idproblema");
        String titulo= getIntent().getExtras().getString("tituloGrupo");

        GrupoClase unGrupo = new GrupoClase(id,idproblema,titulo,fechaActual,iduser,"","","");

        Ayllu.child("Grupos_Conformados").child(idproblema).setValue(unGrupo);
        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ChatGrupo.class);
        String idgrupo = id;
        String idproblem = idproblema;
        i.putExtra("idgrupo",idgrupo);
        i.putExtra("idproblema",idproblem);
        i.putExtra("tituloGrupo",titulo);
        i.putExtra("idusuario",iduser);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

//    @Override
//    public void onClick(View view) {
//        Intent call_chatgroup = new Intent(this, ChatGrupo.class);
//        startActivity(call_chatgroup);
//    }
//    public void ver_perfiluser1(View view) {
//        Intent call_userprofile = new Intent(this, UserProfiles.class);
//        startActivity(call_userprofile);
//    }
//    public void ver_perfiluser2(View view) {
//        Intent call_userprofile = new Intent(this, UserProfiles.class);
//        startActivity(call_userprofile);
//    }
//    public void ver_perfiluser3(View view) {
//        Intent call_userprofile = new Intent(this, UserProfiles.class);
//        startActivity(call_userprofile);
//    }
//    public void ver_perfiluser4(View view) {
//        Intent call_userprofile = new Intent(this, UserProfiles.class);
//        startActivity(call_userprofile);
//    }
//    public void ver_perfiluser5(View view) {
//        Intent call_userprofile = new Intent(this, UserProfiles.class);
//        startActivity(call_userprofile);
//    }
//    public void ver_perfiluser6(View view) {
//        Intent call_userprofile = new Intent(this, UserProfiles.class);
//        startActivity(call_userprofile);
//    }
}