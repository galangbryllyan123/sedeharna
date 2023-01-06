package singpaulee.com.haversinealgorythm.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import singpaulee.com.haversinealgorythm.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //TODO Move to next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent(SplashScreenActivity.this,HomeTubblesActivity.class);
                startActivity(intent);
            }
        },2000);
    }
}




//                Intent intent = new Intent(SplashScreenActivity.this,MainHomeActivity.class);
