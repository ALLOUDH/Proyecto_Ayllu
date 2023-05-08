package ay.llu.project_ayllu.MapsProblema;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
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
import ay.llu.project_ayllu.RegistrarProblema;

public class SeleccionarUbicacionProblema extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{

    private GoogleMap mMap;
    //private ActivityLugarEntregaClienteBinding binding;

    double mLat, mLong;
    Boolean crearMarca = false;
    LatLng unLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = ActivityLugarEntregaClienteBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        btnAceptarPuntoEntrega.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                guardarUbicacion();
//            }
//        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        unLugar = new LatLng(-12.047986752139545, -75.19903770004737);
        CameraPosition cameraPosition = CameraPosition.builder().target(unLugar).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "permiso para utilizar ubicacion", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                crearMarca=true;
                mMap.clear();
                mMap.addMarker( new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                        .title("punto")
                        .snippet("Yo estuve aqui")
                        .position(latLng)
                        .anchor(0.5F,0.5F));
                mLat = latLng.latitude;
                mLong = latLng.longitude;

                Intent intent= new Intent(SeleccionarUbicacionProblema.this, RegistrarProblema.class);
                Bundle punto = new Bundle();
                punto.putParcelable("lugar", new LatLng(mLat, mLong));
                intent.putExtras(punto);
                startActivity(intent);
                mMap.clear();
                crearMarca=false;
            }
        });

    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnAceptarPuntodeEntrega:{
//                if(crearMarca){
//                    Intent intent=new Intent(LugarEntregaCliente.this, com.example.smjdisfraces.cliente.RegistrarPedido.class);
//                    Bundle punto = new Bundle();
//                    punto.putParcelable("location", new LatLng(mLat, mLong));
//                    intent.putExtras(punto);
//                    startActivity(intent);
//                    mMap.clear();
//                    crearMarca=false;
//                }else{
//                    Toast.makeText(this, "Falta marca la ubicación del pedido", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//        }

    }

}