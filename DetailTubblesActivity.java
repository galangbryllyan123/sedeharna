package singpaulee.com.haversinealgorythm.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import singpaulee.com.haversinealgorythm.R;
import singpaulee.com.haversinealgorythm.SharedPrefManager;
import singpaulee.com.haversinealgorythm.model.TubblesModel;

public class DetailTubblesActivity extends AppCompatActivity {

    TextView tvTitle;
    ImageView ivGambar;
    TextView tvAlamat;
    Button btnMaps;
    TextView tvTelp;
    CheckBox cbFitur1;
    CheckBox cbFitur2;
    CheckBox cbFitur3;
    CheckBox cbFitur4;
    CheckBox cbFitur5;
    Button btnCall;

    TubblesModel tubblesModel;
    SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tubbles);

        tvTitle = findViewById(R.id.tv_title);
        ivGambar = findViewById(R.id.iv_gambar);
        tvAlamat = findViewById(R.id.tv_alamat);
        btnMaps = findViewById(R.id.btn_maps);
        tvTelp = findViewById(R.id.tv_telp);
        cbFitur1 = findViewById(R.id.cb_fitur_mobil);
        cbFitur2 = findViewById(R.id.cb_fitur_motor);
        cbFitur3 = findViewById(R.id.cb_fitur_fullday);
        cbFitur4 = findViewById(R.id.cb_fitur_tubbles);
        cbFitur5 = findViewById(R.id.cb_fitur_nitrogen);
        btnCall = findViewById(R.id.btn_call);

        tubblesModel = getIntent().getParcelableExtra("TUBBLES");
        prefManager = new SharedPrefManager(this);

        tvTitle.setText(""+tubblesModel.getNama());
        tvAlamat.setText(""+tubblesModel.getAlamat());
        tvTelp.setText(""+tubblesModel.getTelepon());

        Picasso.with(this)
                .load("https://seputarsemarang.000webhostapp.com/api/tambal_ban/Gambar/"+tubblesModel.getGambar())
                .into(ivGambar);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://maps.google.com/maps?saddr="+prefManager.getLATITUDE()+","+prefManager.getLONGITUDE()+"" +
                        "&daddr="+""+tubblesModel.getLatitude()+","+tubblesModel.getLongitude();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pesan = "Halo%20,%20Ban%20kendaraan%20saya%20bocor.%20Bisa%20tolong%20jemput%20di%20http://maps.google.com/?q="+prefManager.getLATITUDE()+","+prefManager.getLONGITUDE();
                Uri uri = Uri.parse("https://wa.me/+"+tubblesModel.getTelepon()+"?text="+pesan+"");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        if (tubblesModel.getFiturMobile().equals("1")){
            cbFitur1.setChecked(true);
        }else {
            cbFitur1.setChecked(false);
        }

        if (tubblesModel.getFiturMotor().equals("1")){
            cbFitur2.setChecked(true);
        }else {
            cbFitur2.setChecked(false);
        }

        if (tubblesModel.getFiturFullday().equals("1")){
            cbFitur3.setChecked(true);
        }else {
            cbFitur3.setChecked(false);
        }

        if (tubblesModel.getFiturTubbles().equals("1")){
            cbFitur4.setChecked(true);
        }else {
            cbFitur4.setChecked(false);
        }

        if (tubblesModel.getFiturNitrogen().equals("1")){
            cbFitur5.setChecked(true);
        }else {
            cbFitur5.setChecked(false);
        }
    }
}
