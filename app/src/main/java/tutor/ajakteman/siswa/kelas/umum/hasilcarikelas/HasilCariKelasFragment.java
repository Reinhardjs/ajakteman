package tutor.ajakteman.siswa.kelas.umum.hasilcarikelas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import tutor.ajakteman.R;
import tutor.ajakteman.adapter.RecyclerViewAdapter;
import tutor.ajakteman.adapter.SpinnerArrayAdapter;
import tutor.ajakteman.data.Kelas;

public class HasilCariKelasFragment extends Fragment implements HasilCariKelasContract.View {

    private HasilCariKelasContract.Presenter mPresenter;
    private RecyclerViewAdapter adapter;

    public HasilCariKelasFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(HasilCariKelasContract.Presenter presenter) {
        this.mPresenter = presenter;
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
        View root =  inflater.inflate(R.layout.fragment_hasil_cari_kelas, container, false);

        mPresenter = new HasilCariKelasPresenter(this);

        String jenjang = getArguments().getString("jenjang");
        String kelas = getArguments().getString("kelas");
        String jurusan = getArguments().getString("jurusan");
        String pelajaran = getArguments().getString("pelajaran");


        final Spinner spinnerJenjang = root.findViewById(R.id.spinnerJenjang);
        spinnerJenjang.setAdapter(getAdapter(jenjang));

        final Spinner spinnerKelas = root.findViewById(R.id.spinnerKelas);
        spinnerKelas.setAdapter(getAdapter(kelas));

        final Spinner spinnerJurusan = root.findViewById(R.id.spinnerJurusan);
        spinnerJurusan.setAdapter(getAdapter(jurusan));

        final Spinner spinnerPelajaran = root.findViewById(R.id.spinnerPelajaran);
        spinnerPelajaran.setAdapter(getAdapter(pelajaran));


        Button openClassButton = root.findViewById(R.id.openClassButton);
        openClassButton.setOnClickListener(new View.OnClickListener() {
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

        jurusan = jenjang.equals("SMP") ? "" : jurusan;

        DatabaseReference kelasRef = FirebaseDatabase.getInstance()
                .getReference("kelas")
                .child(jenjang + "/" + (((!jurusan.isEmpty()) ? jurusan + "/" : "")))
                .child(kelas);

        DatabaseReference detailKelasRef = FirebaseDatabase.getInstance()
                .getReference("detail-kelas");

        FirebaseRecyclerOptions<Kelas> options =
                new FirebaseRecyclerOptions.Builder<Kelas>()
                        .setIndexedQuery(
                                kelasRef.orderByChild("pelajaran").equalTo(pelajaran), detailKelasRef, Kelas.class)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new RecyclerViewAdapter(this, getContext(), options);
        RecyclerView rv = root.findViewById(R.id.recyclerView);
        rv.setAdapter(adapter);
        adapter.startListening();

//        adapter.populateList(jenjang, jurusan, kelas, pelajaran);

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2){
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            fragmentManager.popBackStack();
//            Toast.makeText(getContext(), "POPPED", Toast.LENGTH_SHORT).show();
        }
    }

//    public ArrayList<Kelas> getKelasList(){
//
//        Kelas kelas1 = new Kelas("-LQo8FlIv4OqoaKFScRn");
//        kelas1.setJenjang("SMP");
//        kelas1.setJurusan("");
//        kelas1.setKelas("VII");
//        kelas1.setPelajaran("Matematika");
//
//        Kelas kelas2 = new Kelas("-LQo8SgKVYGFjguJg0-4");
//        kelas2.setJenjang("SMA");
//        kelas2.setJurusan("IPS");
//        kelas2.setKelas("XII");
//        kelas2.setPelajaran("B. Inggris");
//
//        ArrayList<Kelas> users = new ArrayList<>();
//        users.add(kelas1);
//        users.add(kelas2);
//        users.add(kelas1);
//        users.add(kelas2);
//        users.add(kelas1);
//        users.add(kelas2);
//        users.add(kelas1);
//        users.add(kelas2);
//        users.add(kelas1);
//        users.add(kelas2);
//        users.add(kelas1);
//        users.add(kelas2);
//
//        return users;
//    }
}
