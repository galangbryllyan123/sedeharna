package singpaulee.com.haversinealgorythm.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import singpaulee.com.haversinealgorythm.Haversine;
import singpaulee.com.haversinealgorythm.R;
import singpaulee.com.haversinealgorythm.SharedPrefManager;
import singpaulee.com.haversinealgorythm.adapter.ApotekAdapter;
import singpaulee.com.haversinealgorythm.adapter.TubblesAdapter;
import singpaulee.com.haversinealgorythm.model.ApotekModel;
import singpaulee.com.haversinealgorythm.model.ResponseModel;
import singpaulee.com.haversinealgorythm.model.ResponseTubblesModel;
import singpaulee.com.haversinealgorythm.model.TubblesModel;
import singpaulee.com.haversinealgorythm.rest.ApiClient;
import singpaulee.com.haversinealgorythm.rest.Config;

public class DaftarTubblesActivity extends AppCompatActivity {

    ArrayList<TubblesModel> list;
    TubblesAdapter adapter;
    SharedPrefManager prefManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_tubbles);

        recyclerView = findViewById(R.id.recyclerview);

        prefManager = new SharedPrefManager(this);
        list = new ArrayList<>();

        adapter = new TubblesAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getAlldata();
    }

    private void getAlldata() {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        sweetAlertDialog.setTitleText("Loading ...");
        sweetAlertDialog.show();

        ApiClient apiClient = Config.getRetrofit().create(ApiClient.class);
        Call<ResponseTubblesModel> dataTubbles = apiClient.getAllData();
        dataTubbles.enqueue(new Callback<ResponseTubblesModel>() {
            @Override
            public void onResponse(Call<ResponseTubblesModel> call, Response<ResponseTubblesModel> response) {
                sweetAlertDialog.dismiss();
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getTambalBan().size(); i++) {

                        double latUser = Double.valueOf(prefManager.getLATITUDE());
                        double longUser = Double.valueOf(prefManager.getLONGITUDE());
                        double latTambalBan = Double.valueOf(response.body().getTambalBan().get(i).getLatitude());
                        double longTambalBan = Double.valueOf(response.body().getTambalBan().get(i).getLongitude());

                        double hasilJarak1 = Haversine.hitungJarak(latUser, longUser, latTambalBan, longTambalBan);

                        if (hasilJarak1 < 1000000){
                            TubblesModel apotekModel = new TubblesModel(
                                    response.body().getTambalBan().get(i).getId(),
                                    response.body().getTambalBan().get(i).getNama(),
                                    response.body().getTambalBan().get(i).getAlamat(),
                                    response.body().getTambalBan().get(i).getTelepon(),
                                    response.body().getTambalBan().get(i).getLatitude(),
                                    response.body().getTambalBan().get(i).getLongitude(),
                                    response.body().getTambalBan().get(i).getGambar(),
                                    response.body().getTambalBan().get(i).getFiturMobile(),
                                    response.body().getTambalBan().get(i).getFiturMotor(),
                                    response.body().getTambalBan().get(i).getFiturFullday(),
                                    response.body().getTambalBan().get(i).getFiturTubbles(),
                                    response.body().getTambalBan().get(i).getFiturNitrogen(),
                                    hasilJarak1
                            );
                            list.add(apotekModel);
                        }
                    }

                    Collections.sort(list);

                    adapter = new TubblesAdapter(DaftarTubblesActivity.this, list);
                    recyclerView.setAdapter(adapter);
//                    recyclerView.setVisibility(View.GONE);
                }else {
                    Toast.makeText(DaftarTubblesActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTubblesModel> call, Throwable t) {
                sweetAlertDialog.dismiss();
                Toast.makeText(DaftarTubblesActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                SweetAlertDialog error = new SweetAlertDialog(DaftarTubblesActivity.this, SweetAlertDialog.ERROR_TYPE);
                error.setTitleText("Periksa Koneksi Anda");
                error.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        getAlldata();
                    }
                });
                error.show();
            }
        });
    }
}
