package tutor.ajakteman.siswa.kelas.umum.carikelas;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import tutor.ajakteman.R;
import tutor.ajakteman.adapter.SpinnerArrayAdapter;

public class CariKelasFragment extends Fragment implements CariKelasContract.View {

    private CariKelasContract.Presenter mPresenter;

    public CariKelasFragment() {
        // Required empty public constructor

    }

    private SpinnerArrayAdapter getAdapter(String... strings){
        return new SpinnerArrayAdapter(getContext(),
                R.layout.spinner_item, new ArrayList<String>(
                Arrays.asList(strings)
        ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_kelas_umum, container, false);

        mPresenter = new CariKelasPresenter(this);

        final Spinner spinnerJenjang = root.findViewById(R.id.spinnerJenjang);
        final Spinner spinnerKelas = root.findViewById(R.id.spinnerKelas);
        final Spinner spinnerJurusan = root.findViewById(R.id.spinnerJurusan);
        final Spinner spinnerPelajaran = root.findViewById(R.id.spinnerPelajaran);

        final SpinnerArrayAdapter kelasAdapter = getAdapter("Kelas");
        spinnerKelas.setAdapter(kelasAdapter);

        spinnerJurusan.setAdapter(
                getAdapter(
                        getResources().getStringArray(R.array.spinner_jurusan_arrays)
                )
        );

        spinnerJenjang.setAdapter(
                getAdapter(
                        getResources().getStringArray(R.array.spinner_jenjang_arrays)
                )
        );

        spinnerJenjang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if (selectedItem.equals("SMP")){
                    kelasAdapter.resetList(new ArrayList<String>(
                                    Arrays.asList(getResources().getStringArray(R.array.spinner_kelas_arrays_smp))
                            )
                    );
                } else if (selectedItem.equals("SMA")){
                    kelasAdapter.resetList(new ArrayList<String>(
                                    Arrays.asList(getResources().getStringArray(R.array.spinner_kelas_arrays_sma))
                            )
                    );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPelajaran.setAdapter(
                getAdapter(
                        getResources().getStringArray(R.array.spinner_pelajaran_arrays)
                )
        );

        Button searchButton = root.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean jenjangBool = spinnerJenjang.getSelectedItem().equals(spinnerJenjang.getItemAtPosition(0));
                boolean kelasBool = spinnerKelas.getSelectedItem().equals(spinnerKelas.getItemAtPosition(0));
                boolean jurusanBool = spinnerJurusan.getSelectedItem().equals(spinnerJurusan.getItemAtPosition(0));
                boolean pelajaranBool = spinnerPelajaran.getSelectedItem().equals(spinnerPelajaran.getItemAtPosition(0));

                if (jenjangBool || kelasBool || pelajaranBool) {
                    Toast.makeText(getContext(), "Silakan pilih item untuk semua list", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spinnerJenjang.getSelectedItem().equals("SMP")){

                } else if (spinnerJenjang.getSelectedItem().equals("SMA")){
                    if (jurusanBool){
                        Toast.makeText(getContext(), "Silakan pilih item untuk semua list", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

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