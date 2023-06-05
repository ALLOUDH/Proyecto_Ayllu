package ay.llu.project_ayllu.Personalizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import ay.llu.project_ayllu.R;

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
}