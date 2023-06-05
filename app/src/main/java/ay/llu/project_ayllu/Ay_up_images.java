package ay.llu.project_ayllu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Ay_up_images extends AppCompatActivity {

    private ImageView muploadButton;

    Button btnSubirImagen;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;
    private ProgressDialog mProgressDialog;
    Uri imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_up_images);
        mStorage = FirebaseStorage.getInstance().getReference();
        muploadButton = findViewById(R.id.btnAñadirImagenes1);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        mProgressDialog=new ProgressDialog(this);
        muploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
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
        if(imgUri !=null)
        {
            Toast.makeText(this, "Subiendo Imagen", Toast.LENGTH_SHORT).show();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
            Date now = new Date();
            String fileName = formatter.format(now);
            mStorage = FirebaseStorage.getInstance().getReference(fileName);

            mStorage.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imgUri=null;
                            muploadButton.setImageURI(null);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }else{
            Toast.makeText(Ay_up_images.this,"Imagen sin seleccionar",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null){
            imgUri = data.getData();
            muploadButton.setImageURI(imgUri);
        }
        if(requestCode == 2 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            muploadButton.setImageBitmap(imgBitmap);

        }
    }
}