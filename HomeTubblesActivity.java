package singpaulee.com.haversinealgorythm.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.MapsInitializer;

import singpaulee.com.haversinealgorythm.GpsTracker;
import singpaulee.com.haversinealgorythm.MapsAndLocation;
import singpaulee.com.haversinealgorythm.R;
import singpaulee.com.haversinealgorythm.SharedPrefManager;

public class HomeTubblesActivity extends AppCompatActivity {

    MapsAndLocation mapsAndLocation;
    LocationManager locationManager;
    GpsTracker gpsTracker;

    double latUser;
    double longUser;

    LinearLayout llDaftarTambalban;
    LinearLayout llTambahTambalban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tubbles);

        llDaftarTambalban = findViewById(R.id.ll_daftar_tambalban);
        llTambahTambalban = findViewById(R.id.ll_tambah_tambalban);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mapsAndLocation = new MapsAndLocation(this, locationManager);

        mapsAndLocation.checkLocation();

        permissionLokasi();
        getMyLocation();

        llDaftarTambalban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeTubblesActivity.this, DaftarTubblesActivity.class);
                startActivity(intent);
            }
        });

        llTambahTambalban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(HomeTubblesActivity.this, TambahTubblesActivity.class);
//                startActivity(intent);
                Toast.makeText(HomeTubblesActivity.this, "Fitur sedang dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMyLocation() {
        gpsTracker = new GpsTracker(HomeTubblesActivity.this);
        if (gpsTracker.canGetLocation()) {
            latUser = gpsTracker.getLatitude();
            longUser = gpsTracker.getLongitude();
            SharedPrefManager prefManager = new SharedPrefManager(HomeTubblesActivity.this);
            prefManager.savePrefString(SharedPrefManager.LATITUDE, String.valueOf(latUser));
            prefManager.savePrefString(SharedPrefManager.LONGITUDE, String.valueOf(longUser));
            MapsInitializer.initialize(HomeTubblesActivity.this);
            Toast.makeText(HomeTubblesActivity.this, "Saya dapat lokasimu!", Toast.LENGTH_SHORT).show();

            llDaftarTambalban.setClickable(true);
        } else {
            gpsTracker.showSettingsAlert();
        }
    }

    private void permissionLokasi() {
        if (ContextCompat.checkSelfPermission(HomeTubblesActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeTubblesActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(HomeTubblesActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }


}
