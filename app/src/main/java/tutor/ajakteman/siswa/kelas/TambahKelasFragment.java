 package tutor.ajakteman.siswa.kelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tutor.ajakteman.R;
import tutor.ajakteman.siswa.kelas.privat.KelasPrivateFragment;
import tutor.ajakteman.siswa.kelas.umum.carikelas.CariKelasFragment;


 /**
 * A simple {@link Fragment} subclass.
 */
public class TambahKelasFragment extends Fragment {
    Button buttonAjakTemanTambahKelas, buttonPrivateTambahKelas;

    public TambahKelasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tambah_kelas, container, false);
        buttonAjakTemanTambahKelas = rootView.findViewById(R.id.BtnAjakTemanTambahKelas);
        buttonPrivateTambahKelas = rootView.findViewById(R.id.BtnPrivateTambahKelas);
        buttonAjakTemanTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.root_frame, new CariKelasFragment());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        buttonPrivateTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.root_frame, new KelasPrivateFragment());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }

}
