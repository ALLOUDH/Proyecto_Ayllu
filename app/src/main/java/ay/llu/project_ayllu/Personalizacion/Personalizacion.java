package ay.llu.project_ayllu.Personalizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import ay.llu.project_ayllu.Login;
import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.UserAccount;

public class Personalizacion extends AppCompatActivity {
    Switch sw_tema;
    LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizacion);
        sw_tema = findViewById(R.id.switch_custom);
        linearlayout =findViewById(R.id.layout_main);
    }
    public void setDayNight(int mode){
        if(mode == 0){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    public void set_night(View view) {
        if (sw_tema.isChecked()){
            linearlayout.setBackgroundColor(getColor(R.color.black));
        }else {
            setDayNight(1);
            linearlayout.setBackgroundColor(getColor(R.color.white_decolorated));
        }
    }

    public void llamar_mi_cuenta(View view) {
        Intent i = new Intent(Personalizacion.this, UserAccount.class);
        startActivity(i);
    }
}