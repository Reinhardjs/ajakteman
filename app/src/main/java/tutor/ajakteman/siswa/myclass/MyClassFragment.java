package tutor.ajakteman.siswa.myclass;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tutor.ajakteman.R;
import tutor.ajakteman.adapter.RecyclerViewAdapter;
import tutor.ajakteman.data.Kelas;
import tutor.ajakteman.glide.GlideApp;
import tutor.ajakteman.siswa.kelas.umum.kelasumumbaru.KelasUmumBaruFragment;

public class MyClassFragment extends Fragment {

    private String userID;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myclass, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        fab = root.findViewById(R.id.fab);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        userID = String.valueOf(FirebaseAuth.getInstance()
                .getCurrentUser().getUid());

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users").child("siswa").child(userID);

        DatabaseReference detailKelasRef = FirebaseDatabase.getInstance()
                .getReference("detail-kelas");

        FirebaseRecyclerOptions<Kelas> options =
                new FirebaseRecyclerOptions.Builder<Kelas>()
                        .setIndexedQuery(
                                userRef.child("kelas"), detailKelasRef, Kelas.class)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new RecyclerViewAdapter(this, getContext(), options);
        adapter.setLayoutType(RecyclerViewAdapter.LAYOUT_LIGHT);

        recyclerView.setAdapter(adapter);
        adapter.startListening();

        ImageView coverImage = view.findViewById(R.id.coverImage);
        GlideApp.with(this)
                .load(R.drawable.background_image)
                .centerCrop()
                .into(coverImage);

        fab.setOnClickListener(v -> {

            KelasUmumBaruFragment fragment = new KelasUmumBaruFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.myclass_root_frame, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
