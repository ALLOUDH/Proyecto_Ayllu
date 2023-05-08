package ay.llu.project_ayllu.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

import ay.llu.project_ayllu.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatGrupo extends AppCompatActivity {

    TextView txtv_nombreproblema;
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
        txtv_nombreproblema = (TextView) findViewById(R.id.txtv_nombre_grupoproblemaCG);
        rv_mensajes = (RecyclerView) findViewById(R.id.rv_texto_CG);
        edt_escribirmensajes = (EditText) findViewById(R.id.edt_escribirmensajeCG);
        imgbtn_enviarmensajes = (ImageView) findViewById(R.id.imgbtn_enviarmensajeCG);
        imgbtn_enviarimagenes = (ImageView) findViewById(R.id.imgbtn_enviarfotosCG);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");// Sala de chat (nombre)
        storage = FirebaseStorage.getInstance();

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
}