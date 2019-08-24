package tutor.ajakteman;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import tutor.ajakteman.adapter.MyPagerAdapter;
import tutor.ajakteman.fcm.FCMHandler;

import static tutor.ajakteman.helper.PermissionHelper.canAccessLocation;
import static tutor.ajakteman.helper.PermissionHelper.requestLocationPermission;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener, GoogleApiClient.OnConnectionFailedListener {

    ViewPager viewPager;
    BottomBar bottomBar;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (canAccessLocation(this)){
            // do something
            // Toast.makeText(getApplicationContext(), "PERMISSION BERHASIL DIAKTIFKAN", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setOnPageChangeListener(this);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!canAccessLocation(this)){
                requestLocationPermission(this);
            }
        }

        // Firebase Cloud Messaging
        // --------------------------------------------------------------
        FCMHandler.requestNewToken(getApplicationContext());
        // ##############################################################

    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId){
            case R.id.notifikasi:
                viewPager.setCurrentItem(0);
                break;
            case R.id.chat:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tambahKelas:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tentang:
                viewPager.setCurrentItem(3);
                break;
            case R.id.profil:
                viewPager.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        bottomBar.selectTabAtPosition(position, true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
