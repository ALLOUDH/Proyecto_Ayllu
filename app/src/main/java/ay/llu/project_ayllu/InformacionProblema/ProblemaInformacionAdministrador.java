package ay.llu.project_ayllu.InformacionProblema;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import ay.llu.project_ayllu.R;

public class ProblemaInformacionAdministrador extends AppCompatActivity {
    TextView txtTituloMostrarProblema,txtFechaMostrarProblema,txtDescripcionMostrarProblema,txtUbicacionMostrarProblema;
    String titulo, descripcion, latitud, longitud, fecha;
    ImageView imgMostrarProblema,imgUbicacionMostrarProblema;

    VideoView videoProblema;
    ImageView imgBorrarProblema;

    DatabaseReference AylluDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_informacion_administrador);
        txtTituloMostrarProblema = findViewById(R.id.txtTituloMostrarProblema);
        txtDescripcionMostrarProblema = findViewById(R.id.txtDescripcionMostrarProblema);
        txtFechaMostrarProblema = findViewById(R.id.txtFechaMostrarProblema);
        txtUbicacionMostrarProblema = findViewById(R.id.txtUbicacionMostrarProblema);

        String idproblema = getIntent().getExtras().getString("idproblema");
        String dnireportero = getIntent().getExtras().getString("dnireportero");
        titulo= getIntent().getStringExtra("titulo");
        descripcion = getIntent().getStringExtra("descripcion");
        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");
        fecha = getIntent().getStringExtra("fecha");
        imgUbicacionMostrarProblema=findViewById(R.id.imgUbicacionMostrarProblema);
        imgMostrarProblema=findViewById(R.id.imgMostrarProblema);
        videoProblema = findViewById(R.id.videoProblema);
        imgBorrarProblema = findViewById(R.id.imgBorrarProblema);

        txtTituloMostrarProblema.setText(titulo);
        txtFechaMostrarProblema.setText(fecha);
        txtDescripcionMostrarProblema.setText(descripcion);

        imgUbicacionMostrarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProblemaInformacionAdministrador.this, MostrarUbicacionProblema.class);
                i.putExtra("lati",latitud);
                i.putExtra("longi",longitud);
                Toast.makeText(ProblemaInformacionAdministrador.this, "Problema Localizado", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        AylluDatabase.child("Problemas_Recientes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(idproblema)){
                    String getreportero = snapshot.child(idproblema).child("idReportero").getValue(String.class);
                    String getcategoria = snapshot.child(idproblema).child("categoria").getValue(String.class);
                    String urlfoto = snapshot.child(idproblema).child("idFotoProblema").getValue(String.class);
                    String urlvideo = snapshot.child(idproblema).child("idVideoProblema").getValue(String.class);

                    String videoUrl = urlvideo;
                    Uri videoUri = Uri.parse(videoUrl);
                    videoProblema.setVideoURI(videoUri);
                    MediaController mediaController = new MediaController(ProblemaInformacionAdministrador.this);
                    videoProblema.setMediaController(mediaController);
                    mediaController.setAnchorView(videoProblema);
                    //vidVideoOferta.start();
                    if(urlfoto.isEmpty())
                    {
                        Toast.makeText(ProblemaInformacionAdministrador.this,"No se encontro una foto",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(urlfoto).fit().centerCrop().into(imgMostrarProblema);
                    }
                    AylluDatabase.child("Reporteros").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(getreportero)){
                                String departportero = snapshot.child(getreportero).child("departamento").getValue(String.class);
                                String provreportero = snapshot.child(getreportero).child("provincia").getValue(String.class);
                                txtUbicacionMostrarProblema.setText(provreportero+" - "+departportero);

                            }
                            else {
                                Toast.makeText(ProblemaInformacionAdministrador.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }
                else {
                    Toast.makeText(ProblemaInformacionAdministrador.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
}