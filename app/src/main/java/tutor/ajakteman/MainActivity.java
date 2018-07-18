package tutor.ajakteman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import tutor.ajakteman.fragment.BerandaFragment;
import tutor.ajakteman.fragment.ChatFragment;
import tutor.ajakteman.fragment.ProfileSiswaFragment;
import tutor.ajakteman.fragment.TambahKelasFragment;
import tutor.ajakteman.fragment.TentangFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_Beramda:
                    fragmentTransaction.replace(R.id.main_Frame,new BerandaFragment()).commit();
                    return true;
                case R.id.navigation_Chat:
                    fragmentTransaction.replace(R.id.main_Frame,new ChatFragment()).commit();
                    return true;
                case R.id.navigation_Tambah_Kelas:
                    fragmentTransaction.replace(R.id.main_Frame,new TambahKelasFragment()).commit();
                    return true;
                case R.id.navigation_Profil:
                    fragmentTransaction.replace(R.id.main_Frame,new ProfileSiswaFragment()).commit();
                    return true;
                case R.id.navigation_Tentang:
                    fragmentTransaction.replace(R.id.main_Frame,new TentangFragment()).commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_Frame, new TambahKelasFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.navigation_Tambah_Kelas).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
