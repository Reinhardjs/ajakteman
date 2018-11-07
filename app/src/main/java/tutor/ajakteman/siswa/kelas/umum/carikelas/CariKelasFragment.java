package tutor.ajakteman.siswa.kelas.umum.carikelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import tutor.ajakteman.R;

public class CariKelasFragment extends Fragment implements CariKelasContract.View {

    private CariKelasContract.Presenter mPresenter;

    public CariKelasFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_kelas_umum, container, false);

        mPresenter = new CariKelasPresenter(this);

        final Spinner spinnerJenjang = root.findViewById(R.id.spinnerJenjang);
        ArrayAdapter adapterJenjang = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_jenjang_arrays, R.layout.spinner_item);
        adapterJenjang.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerJenjang.setAdapter(adapterJenjang);

        final Spinner spinnerKelas = root.findViewById(R.id.spinnerKelas);
        ArrayAdapter adapterKelas = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_kelas_arrays, R.layout.spinner_item);
        adapterKelas.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerKelas.setAdapter(adapterKelas);

        final Spinner spinnerJurusan = root.findViewById(R.id.spinnerJurusan);
        ArrayAdapter adapterJurusan = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_jurusan_arrays, R.layout.spinner_item);
        adapterJurusan.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerJurusan.setAdapter(adapterJurusan);

        final Spinner spinnerPelajaran = root.findViewById(R.id.spinnerPelajaran);
        ArrayAdapter adapterPelajaran = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_pelajaran_arrays, R.layout.spinner_item);
        adapterPelajaran.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerPelajaran.setAdapter(adapterPelajaran);

        Button searchButton = root.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle arguments = new Bundle();
                arguments.putString("jenjang", (String) spinnerJenjang.getSelectedItem());
                arguments.putString("kelas", (String) spinnerKelas.getSelectedItem());
                arguments.putString("jurusan", (String) spinnerJurusan.getSelectedItem());
                arguments.putString("pelajaran", (String) spinnerPelajaran.getSelectedItem());

                mPresenter.startFragment(arguments);
            }
        });

        return root;
    }

    public void setPresenter(CariKelasContract.Presenter presenter) {
        mPresenter = presenter;
    }

}



//        String arraySpinner[] = new String[]{
//                "Item 1",
//                "Item 2",
//                "Item 3",
//                "Item 4",
//                "Item 5",
//        };
//
//        ArrayAdapter<String> adapterJenjang = new ArrayAdapter<>(
//                getActivity(),
//                R.layout.spinner_item,
//                R.id.row_item,
//                arraySpinner);