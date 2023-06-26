package ay.llu.project_ayllu.InformacionProblema;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ProblemaInformacion extends AppCompatActivity {

    TextView txtTituloMostrarProblema,txtFechaMostrarProblema,txtDescripcionMostrarProblema,txtUbicacionMostrarProblema;
    String titulo, descripcion, latitud, longitud, fecha;
    ImageView imgMostrarProblema,imgUbicacionMostrarProblema;

    VideoView videoProblema;
    Button btnVerGrupo;

    DatabaseReference AylluDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_informacion);

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
        btnVerGrupo = findViewById(R.id.btnVerGrupo);

        txtTituloMostrarProblema.setText(titulo);
        txtFechaMostrarProblema.setText(fecha);
        txtDescripcionMostrarProblema.setText(descripcion);

        imgUbicacionMostrarProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProblemaInformacion.this, MostrarUbicacionProblema.class);
                i.putExtra("lati",latitud);
                i.putExtra("longi",longitud);
                Toast.makeText(ProblemaInformacion.this, "Problema Localizado", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        btnVerGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProblemaInformacion.this, ay.llu.project_ayllu.Grupos.class);
                intent.putExtra("idproblema",idproblema);
                intent.putExtra("tituloGrupo",titulo);
                startActivity(intent);
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
                    MediaController mediaController = new MediaController(ProblemaInformacion.this);
                    videoProblema.setMediaController(mediaController);
                    mediaController.setAnchorView(videoProblema);
                    //vidVideoOferta.start();
                    if(urlfoto.isEmpty())
                    {
                        Toast.makeText(ProblemaInformacion.this,"No se encontro una foto",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(ProblemaInformacion.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }
                else {
                    Toast.makeText(ProblemaInformacion.this,"Error al cargar información",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
}