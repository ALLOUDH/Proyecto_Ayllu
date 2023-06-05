package ay.llu.project_ayllu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class UserProfiles extends AppCompatActivity {
    EditText user_descr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profiles);
        user_descr = findViewById(R.id.edt_userdescr);
        user_descr.setTextColor(getColor(R.color.white));

    }
}