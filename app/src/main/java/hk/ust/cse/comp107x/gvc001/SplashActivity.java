package hk.ust.cse.comp107x.gvc001;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import hk.ust.cse.comp107x.gvc001.activities.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    private final int REQUEST_CODE_0 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Transaction().execute();
    }

    private void begin() {
        if(checkPermissions()){
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean checkPermissions() {
        String[] permission = {Manifest.permission.INTERNET};
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        if (ActivityCompat.checkSelfPermission(this, permission[0]) == PackageManager.PERMISSION_GRANTED)
            return true;
        getPermission(permission);
        return false;
    }

    private void getPermission(String[] permission) {
        ActivityCompat.requestPermissions(this, permission, REQUEST_CODE_0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_0)
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(SplashActivity.this,
                            "Please, Those permissions are required to proceed",
                            Toast.LENGTH_LONG).show();

                    finish();
                    return;
                }
            }
    }

    class Transaction extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            begin();
        }
    }
}