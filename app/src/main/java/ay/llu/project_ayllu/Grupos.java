package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ay.llu.project_ayllu.chat.ChatGrupo;

public class Grupos extends AppCompatActivity implements View.OnClickListener {

    TextView txtvcategoria_titulo;
    TextView txtvtitulo_problema;
    TextView txtvuser_1,txtvuser_2,txtvuser_3,txtvuser_4,txtvuser_5,txtvuser_6;
    TextView txtvcarrera_user1,txtvcarrera_user2,txtvcarrera_user3,txtvcarrera_user4,txtvcarrera_user5,txtvcarrera_user6;
    Button btn_unirme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        txtvcategoria_titulo = findViewById(R.id.txtv_grupo_titulo_categoria);
        txtvtitulo_problema = findViewById(R.id.txtv_grupo_nombre_problema);
        txtvuser_1 = findViewById(R.id.txt_grupo_user1_name);
        txtvuser_2 = findViewById(R.id.txtv_grupo_user2_carrera);
        txtvuser_3 = findViewById(R.id.txtv_grupo_user3_carrera);
        txtvuser_4 = findViewById(R.id.txtv_grupo_user4_carrera);
        txtvuser_5 = findViewById(R.id.txtv_grupo_user5_carrera);
        txtvuser_6 = findViewById(R.id.txtv_grupo_user6_carrera);
        txtvcarrera_user1 = findViewById(R.id.txtv_grupo_user1_carrera);
        txtvcarrera_user2 = findViewById(R.id.txtv_grupo_user2_carrera);
        txtvcarrera_user3 = findViewById(R.id.txtv_grupo_user3_carrera);
        txtvcarrera_user4 = findViewById(R.id.txtv_grupo_user4_carrera);
        txtvcarrera_user5 = findViewById(R.id.txtv_grupo_user5_carrera);
        txtvcarrera_user6 = findViewById(R.id.txtv_grupo_user6_carrera);
        btn_unirme = findViewById(R.id.btn_unirme_grupo);
        btn_unirme.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent call_chatgroup = new Intent(this, ChatGrupo.class);
        startActivity(call_chatgroup);
    }
    public void ver_perfiluser1(View view) {
        Intent call_userprofile = new Intent(this, UserProfiles.class);
        startActivity(call_userprofile);
    }
    public void ver_perfiluser2(View view) {
        Intent call_userprofile = new Intent(this, UserProfiles.class);
        startActivity(call_userprofile);
    }
    public void ver_perfiluser3(View view) {
        Intent call_userprofile = new Intent(this, UserProfiles.class);
        startActivity(call_userprofile);
    }
    public void ver_perfiluser4(View view) {
        Intent call_userprofile = new Intent(this, UserProfiles.class);
        startActivity(call_userprofile);
    }
    public void ver_perfiluser5(View view) {
        Intent call_userprofile = new Intent(this, UserProfiles.class);
        startActivity(call_userprofile);
    }
    public void ver_perfiluser6(View view) {
        Intent call_userprofile = new Intent(this, UserProfiles.class);
        startActivity(call_userprofile);
    }
}