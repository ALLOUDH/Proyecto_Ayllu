package ay.llu.project_ayllu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Ay_up_images extends AppCompatActivity {

    private ImageButton muploadButton;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;
    private ImageView mImageview;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_up_images);
        mStorage = FirebaseStorage.getInstance().getReference();
        muploadButton = (ImageButton) findViewById(R.id.btnAñadirImagenes1);
        mImageview=(ImageView) findViewById(R.id.subirimagen);
        mProgressDialog=new ProgressDialog(this);
        muploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setTitle("Subiendo foto...");
            mProgressDialog.setMessage("Subiendo foto a firebase ");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("photos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressDialog.dismiss();
                    Uri descargarFoto = taskSnapshot.getUploadSessionUri();
                    Glide.with(Ay_up_images.this).load(descargarFoto).fitCenter().centerCrop().into(mImageview);

                    Toast.makeText(Ay_up_images.this, "Se subio exitosamente la foto", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}