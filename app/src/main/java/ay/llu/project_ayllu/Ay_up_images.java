package ay.llu.project_ayllu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Ay_up_images extends AppCompatActivity {

    private ImageView muploadButton;

    Button btnSubirImagen;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;
    private ProgressDialog mProgressDialog;
    private DatabaseReference AylluDatabase;
    Uri imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_up_images);
        mStorage = FirebaseStorage.getInstance().getReference();
        muploadButton = findViewById(R.id.btnAñadirImagenes1);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        mProgressDialog=new ProgressDialog(this);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        AylluDatabase = db.getReference();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            imgUri = data.getData();
                            muploadButton.setImageURI(imgUri);

                        }else {
                            Toast.makeText(Ay_up_images.this, "Imagen no seleccionada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        muploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);

            }
        });
        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirImagen();
            }
        });

    }

    private void subirImagen() {
        String idproblema = getIntent().getExtras().getString("idproblema");
        String dnireportero = getIntent().getExtras().getString("dnireportero");
        if(imgUri !=null)
        {
            Toast.makeText(this, "Subiendo Imagen", Toast.LENGTH_SHORT).show();

            String fileName = "fotoProblema_"+dnireportero+"_"+idproblema;

//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
//            Date now = new Date();
//            String fileName = formatter.format(now);
            mStorage = FirebaseStorage.getInstance().getReference("Fotos/" + fileName);
            mStorage.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            AylluDatabase.child("Problemas_Recientes").child(idproblema).child("idFotoProblema").setValue(uri.toString());
                            Toast.makeText(Ay_up_images.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            imgUri=null;
                            muploadButton.setImageURI(null);
                            Intent i = new Intent(Ay_up_images.this, Ay_prob_video.class);
                            String idreportero = dnireportero;
                            String idproblema1 = idproblema;
                            i.putExtra("dnireportero", idreportero);
                            i.putExtra("idproblema", idproblema1);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Ay_up_images.this,"Falla al subir",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(Ay_up_images.this,"Imagen sin seleccionar",Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && data != null && data.getData() != null){
//            imgUri = data.getData();
//            muploadButton.setImageURI(imgUri);
//        }
//        if(requestCode == 2 && resultCode == RESULT_OK){
//            Bundle extras = data.getExtras();
//            Bitmap imgBitmap = (Bitmap) extras.get("data");
//            muploadButton.setImageBitmap(imgBitmap);
//
//        }
//    }
}