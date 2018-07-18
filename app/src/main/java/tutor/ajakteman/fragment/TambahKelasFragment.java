package tutor.ajakteman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tutor.ajakteman.R;


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


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        buttonAjakTemanTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_Frame,new AjakTemanFragment()).commit();
            }
        });
        buttonPrivateTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_Frame,new KelasPrivateFragment()).commit();
            }
        });
        return rootView;
    }

}
