package ay.llu.project_ayllu.InformacionProblema;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.databinding.ActivityMostrarUbicacionProblemaBinding;

public class MostrarUbicacionProblema extends FragmentActivity implements OnMapReadyCallback {

    String latitud,longitud;
    double mLat,mLong;
    LatLng destino;
    private GoogleMap mMap;
    private ActivityMostrarUbicacionProblemaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMostrarUbicacionProblemaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitud = getIntent().getStringExtra("lati");
        longitud = getIntent().getStringExtra("longi");

        mLat = Double.parseDouble(latitud);
        mLong = Double.parseDouble(longitud);
        destino = new LatLng(mLat,mLong);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "Dio su permiso para utilizar su ubicacion", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
        CameraPosition cameraPosition = CameraPosition.builder().target(destino).zoom(17).build();
        mMap.addMarker( new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                .title("Ubicación Porblema")
                .position(destino)
                .anchor(0.5F,0.5F));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}