package ay.llu.project_ayllu;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Ay_prob_video extends AppCompatActivity {
    Button btnSeleccioarVideo, btnSubirVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ay_prob_video);

        btnSeleccioarVideo = findViewById(R.id.btnSeleccionarVideo);

        btnSeleccioarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarVideo();
            }
        });
        
    }
    private void seleccionarVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 5);
    }

    Uri videoUri;

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            videoUri = data.getData();
            subirVideo();
        }
    }
    private String getfiletype(Uri videouri) {
        ContentResolver r = getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
    }

    private void subirVideo() {
        String idproblema = getIntent().getExtras().getString("idproblema");
        String dnireportero = getIntent().getExtras().getString("dnireportero");

        if (videoUri != null) {
            final StorageReference reference = FirebaseStorage.getInstance().getReference("Videos/videoProblema_"+dnireportero+"_" + idproblema + "." + getfiletype(videoUri));
            reference.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    String downloadUri = uriTask.getResult().toString();
                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
                    reference1.child("Problemas_Recientes").child(idproblema).child("idVideoProblema").setValue(downloadUri);
                    Toast.makeText(Ay_prob_video.this, "Video Subido", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Ay_prob_video.this, MenuReportero.class);
                    String idreportero = dnireportero;
                    i.putExtra("dniuser", idproblema);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Ay_prob_video.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                // Progress Listener for loading
                // percentage on the dialog box
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    // show the progress bar
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                }
            });
        }
    }
}