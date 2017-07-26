package com.example.jurizo.bitacora;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.DAO_Oficinas;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityOficina;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ActivityOficinasUbication extends AppCompatActivity
        implements OnMapReadyCallback, LocationListener {
    private Context context;
    private GoogleMap mMap;
    private LocationManager locationManager;
    String mprovider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficinas_ubication);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LoadToolBar();
        LoadOficcesTheMap loadOficcesTheMap = new LoadOficcesTheMap();
        loadOficcesTheMap.execute();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(mprovider);
        locationManager.requestLocationUpdates("", 15000, 1, this);

        if (location != null) {
            onLocationChanged(location);
        } else {
            Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }

    }

    private void LoadToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ubication_os);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Ubicación de OS");
        bar.setSubtitle("Buscar oficinas de servicio");
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //LatLng sydney = new LatLng(latitude, longitude);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        /*CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044, -73.98180484771729));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);*/
    }

    @Override
    public void onLocationChanged(Location location) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public class LoadOficcesTheMap extends AsyncTask<String, String, Void> {

        private ProgressDialog progressDialog;
        private ArrayList<MarkerOptions> markersArray;

        @Override
        protected Void doInBackground(String... params) {
            DAO_Oficinas daoOficinas = new DAO_Oficinas(context);
            List<EntityOficina> oficinas = daoOficinas.getOficinas();
            markersArray = new ArrayList<>();

            for (EntityOficina os : oficinas) {
                markersArray.add(new MarkerOptions()
                        .position(new LatLng(os.getLatitud(), os.getLongitud()))
                        .anchor(0.5f, 0.5f)
                        .title(os.getCc() + "-" + os.getOficina())
                        .snippet(os.getRegion()));
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Detectando oficinas");
            progressDialog.setMessage("Buscando oficinas de compartamos");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (markersArray != null && markersArray.size() > 0) {
                PaintToMarks(markersArray);
            }
            progressDialog.cancel();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            progressDialog.setMessage(values[0]);
        }

    }

    private void PaintToMarks(ArrayList<MarkerOptions> markersArray) {
        mMap.clear();
        for (MarkerOptions marker : markersArray) {
            mMap.addMarker(marker);
        }
    }

}
