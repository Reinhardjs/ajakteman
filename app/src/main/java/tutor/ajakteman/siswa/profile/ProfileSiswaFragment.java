package tutor.ajakteman.siswa.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tutor.ajakteman.R;
import tutor.ajakteman.WelcomeScreenActivity;
import tutor.ajakteman.fcm.FCMHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileSiswaFragment extends Fragment {
    private TextView namaSiswa, alamat, email, jenjang;
    private DatabaseReference databaseReference;
    private Button button;
    public ProfileSiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_siswa, container, false);
        namaSiswa = rootView.findViewById(R.id.MenuProfileSiswaNama);
        alamat = rootView.findViewById(R.id.MenuProfileSiswaAlamat);
        email = rootView.findViewById(R.id.MenuProfileSiswaEmail);
        jenjang = rootView.findViewById(R.id.MenuProfileSiswaJenjang);
        button = rootView.findViewById(R.id.MenuProfileSiswaButtonLogOut);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                namaSiswa.setText(String.valueOf(dataSnapshot.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).child("nama").getValue()));
//                alamat.setText("alamat    : "+String.valueOf(dataSnapshot.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).child("alamat").getValue()));
//                email.setText("email     : "+String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
//                jenjang.setText("jenjang   : "+String.valueOf(dataSnapshot.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).child("jenjang").getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Deleting Firebase Cloud Messaging Instance ID
                FCMHandler.deleteInstanceId(getContext());

                // LOG TO FIREBASE DATABASE
                String userID = String.valueOf(FirebaseAuth.getInstance()
                        .getCurrentUser().getUid());

                DatabaseReference myConnectionsRef = FirebaseDatabase.getInstance()
                        .getReference("users/siswa")
                        .child(userID).child("fcm_token");

                myConnectionsRef.setValue("");

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), WelcomeScreenActivity.class));
            }
        });

        return rootView;
    }

}
