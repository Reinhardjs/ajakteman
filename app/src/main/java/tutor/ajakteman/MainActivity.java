package tutor.ajakteman;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import tutor.ajakteman.adapter.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(this);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(this);

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
            case R.id.profil:
                viewPager.setCurrentItem(3);
                break;
            case R.id.tentang:
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
}
