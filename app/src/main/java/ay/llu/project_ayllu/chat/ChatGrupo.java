/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/

package ay.llu.project_ayllu.chat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import ay.llu.project_ayllu.Grupos;
import ay.llu.project_ayllu.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatGrupo extends AppCompatActivity {


    ImageView imgbtn_call,imgbtn_navigation;
    TextView txtTituloProblemaChat;
    private CircleImageView civ_fotouser;
    private TextView txtv_nombreuser;
    private RecyclerView rv_mensajes;
    private EditText edt_escribirmensajes;
    private ImageView imgbtn_enviarmensajes,imgbtn_enviarimagenes;
    private AdaptadorMensajes adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_SEND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_grupo);
        civ_fotouser = (CircleImageView) findViewById(R.id.civ_fotouserCG);
        txtv_nombreuser = (TextView) findViewById(R.id.txtv_nombreuserCG);
        rv_mensajes = (RecyclerView) findViewById(R.id.rv_texto_CG);
        txtTituloProblemaChat = findViewById(R.id.txtTituloProblemaChat);
        edt_escribirmensajes = (EditText) findViewById(R.id.edt_escribirmensajeCG);
        imgbtn_enviarmensajes = (ImageView) findViewById(R.id.imgbtn_enviarmensajeCG);
        imgbtn_enviarimagenes = (ImageView) findViewById(R.id.imgbtn_enviarfotosCG);
        imgbtn_call = (ImageView) findViewById(R.id.imgbtn_callGC);
        imgbtn_navigation = (ImageView) findViewById(R.id.imgbtn_settings_GC);
        edt_escribirmensajes.setTextColor(getColor(R.color.white));

        String titulo= getIntent().getExtras().getString("tituloGrupo");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");// Sala de chat (nombre)
        storage = FirebaseStorage.getInstance();

        txtTituloProblemaChat.setText(titulo);
        adapter = new AdaptadorMensajes(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_mensajes.setLayoutManager(linearLayoutManager);
        rv_mensajes.setAdapter(adapter);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MensajeRecibir m = snapshot.getValue(MensajeRecibir.class);
                adapter.add_mensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setScrollbar(){
        rv_mensajes.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Uri url = data.getData();
            storageReference = storage.getReference("chat_images");//Imagenes del chat
            final StorageReference fotoReferencia = storageReference.child(url.getLastPathSegment());
            fotoReferencia.putFile(url).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    MensajeEnviar m = new MensajeEnviar("User te ha enviado una foto",url.toString(),txtv_nombreuser.getText().toString(),"","2",ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }
    }
    public void enviar_mensaje(View view) {
        databaseReference.push().setValue(new MensajeEnviar(edt_escribirmensajes.getText().toString(),txtv_nombreuser.getText().toString(),"","1", ServerValue.TIMESTAMP));
        edt_escribirmensajes.setText("");
    }
    public void enviar_imagenes(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(intent,"Seleccione una Imágen"),PHOTO_SEND);
    }

    public void mostrarpopup(View v){
        PopupMenu pm = new PopupMenu(this,imgbtn_navigation);
        pm.getMenuInflater().inflate(R.menu.menupopup_chatg,pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_integrantes:
                        Toast.makeText(ChatGrupo.this, "En progreso",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_notificaciones:
                        Toast.makeText(ChatGrupo.this, "En progreso",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_reportgroup:
                        Toast.makeText(ChatGrupo.this, "En progreso",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_exitgroup:
                        llamar_grupos();
                        return true;
                }
                return false;
            }
        });
        pm.show();
    }

    public void llamar_grupos(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(ChatGrupo.this);
        alerta.setMessage("Esta seguro que quiere salir del grupo?").setCancelable(true).setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent call_grupos = new Intent(ChatGrupo.this, Grupos.class);
                startActivity(call_grupos);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Advertencia");
        titulo.show();
    }

    public void call_llamar(View view) {
            String dial = "tel:+51959933665";
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
    }
}