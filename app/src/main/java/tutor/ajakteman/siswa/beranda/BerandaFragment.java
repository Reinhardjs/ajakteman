package tutor.ajakteman.siswa.beranda;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tutor.ajakteman.R;
import tutor.ajakteman.siswa.kelas.lobbykelas.LobbyKelasActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_beranda, container, false);

        Button buttonSiswa = root.findViewById(R.id.btnSiswa);
        buttonSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LobbyKelasActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
