package tutor.ajakteman.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import tutor.ajakteman.siswa.chat.ChatFragment;
import tutor.ajakteman.siswa.myclass.MyClassRootFragment;
import tutor.ajakteman.siswa.profile.ProfileSiswaFragment;
import tutor.ajakteman.siswa.kelas.TambahKelasRootFragment;
import tutor.ajakteman.siswa.about.TentangFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 5;
    ArrayList<Fragment> fragments = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        fragments.add(new MyClassRootFragment());
        fragments.add(new ChatFragment());
        fragments.add(new TambahKelasRootFragment());
        fragments.add(new TentangFragment());
        fragments.add(new ProfileSiswaFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}