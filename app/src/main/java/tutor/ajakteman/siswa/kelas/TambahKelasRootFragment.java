package tutor.ajakteman.siswa.kelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tutor.ajakteman.R;

public class TambahKelasRootFragment extends Fragment {

    private static final String TAG = "TambahKelasRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_kelas_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.root_frame, new TambahKelasFragment());
        transaction.commit();

        return view;
    }

}
