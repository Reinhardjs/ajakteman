package tutor.ajakteman.siswa.kelas.umum.hasilcarikelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import tutor.ajakteman.POJO.FilteredKelas;
import tutor.ajakteman.R;
import tutor.ajakteman.adapter.RecyclerViewAdapter;
import tutor.ajakteman.siswa.kelas.umum.kelasumumbaru.KelasUmumBaruFragment;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link HasilCariKelasFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link HasilCariKelasFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HasilCariKelasFragment extends Fragment {

    public HasilCariKelasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_hasil_cari_kelas, container, false);

        String jenjang = getArguments().getString("jenjang");
        String kelas = getArguments().getString("kelas");
        String jurusan = getArguments().getString("jurusan");
        String pelajaran = getArguments().getString("pelajaran");


        final Spinner spinnerJenjang = root.findViewById(R.id.spinnerJenjang);
        ArrayAdapter adapterJenjang = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_jenjang_arrays, R.layout.spinner_item);
        adapterJenjang.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerJenjang.setAdapter(adapterJenjang);
        spinnerJenjang.setSelection(adapterJenjang.getPosition(jenjang));


        final Spinner spinnerKelas = root.findViewById(R.id.spinnerKelas);
        ArrayAdapter adapterKelas = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_kelas_arrays, R.layout.spinner_item);
        adapterKelas.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerKelas.setAdapter(adapterKelas);
        spinnerKelas.setSelection(adapterKelas.getPosition(kelas));


        final Spinner spinnerJurusan = root.findViewById(R.id.spinnerJurusan);
        ArrayAdapter adapterJurusan = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_jurusan_arrays, R.layout.spinner_item);
        adapterJurusan.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerJurusan.setAdapter(adapterJurusan);
        spinnerJurusan.setSelection(adapterJurusan.getPosition(jurusan));


        final Spinner spinnerPelajaran = root.findViewById(R.id.spinnerPelajaran);
        ArrayAdapter adapterPelajaran = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_pelajaran_arrays, R.layout.spinner_item);
        adapterPelajaran.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerPelajaran.setAdapter(adapterPelajaran);
        spinnerPelajaran.setSelection(adapterPelajaran.getPosition(pelajaran));


        Button openClassButton = root.findViewById(R.id.openClassButton);
        openClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.root_frame, new KelasUmumBaruFragment());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        boolean jenjangBool = spinnerJenjang.getSelectedItem().equals(spinnerJenjang.getItemAtPosition(0));
        boolean kelasBool = spinnerKelas.getSelectedItem().equals(spinnerKelas.getItemAtPosition(0));
        boolean jurusanBool = spinnerJurusan.getSelectedItem().equals(spinnerJurusan.getItemAtPosition(0));
        boolean pelajaranBool = spinnerPelajaran.getSelectedItem().equals(spinnerPelajaran.getItemAtPosition(0));

//        if (!jenjangBool){
//
//        }

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), getKelasList());
        RecyclerView rv = root.findViewById(R.id.recyclerView);
        rv.setAdapter(adapter);

        return root;
    }

    public ArrayList<FilteredKelas> getKelasList(){
        ArrayList<FilteredKelas> users = new ArrayList<>();
        users.add(new FilteredKelas("1", "1", "1", "1", "1"));
        users.add(new FilteredKelas("2", "2", "2", "2", "2"));
        users.add(new FilteredKelas("3", "3", "3", "3", "3"));
        users.add(new FilteredKelas("1", "1", "1", "1", "1"));
        users.add(new FilteredKelas("2", "2", "2", "2", "2"));
        users.add(new FilteredKelas("3", "3", "3", "3", "3"));
        users.add(new FilteredKelas("1", "1", "1", "1", "1"));
        users.add(new FilteredKelas("2", "2", "2", "2", "2"));
        users.add(new FilteredKelas("3", "3", "3", "3", "3"));

        return users;
    }

}
