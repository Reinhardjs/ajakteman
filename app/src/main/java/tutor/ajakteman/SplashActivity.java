package tutor.ajakteman;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import tutor.ajakteman.data.Siswa;
import tutor.ajakteman.fcm.FCMHandler;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//        startActivity(intent);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            final String userID = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid());
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");

            Query query = databaseRef.child("siswa").orderByKey().equalTo(userID);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshots) {
                    if (snapshots.exists()){
                        // EXISTS ON SISWA CHILD
                        for (DataSnapshot snapshot : snapshots.getChildren()){
                            Siswa siswa = (Siswa) snapshot.getValue(Siswa.class);

                            String userID = String.valueOf(FirebaseAuth.getInstance()
                                    .getCurrentUser().getUid());

                            DatabaseReference myConnectionsRef = FirebaseDatabase.getInstance()
                                    .getReference("users/siswa")
                                    .child(userID).child("online");

                            myConnectionsRef.setValue(true);
                            myConnectionsRef.onDisconnect().setValue(false);

                            DatabaseReference lastConnectionRef = FirebaseDatabase.getInstance()
                                    .getReference("users/siswa")
                                    .child(userID).child("lastOnline");

                            lastConnectionRef.onDisconnect().setValue(ServerValue.TIMESTAMP);

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                    } else {
                        // NOT EXISTS ON SISWA CHILD
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseRef.child("tentor").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        // EXISTS ON TENTOR CHILD
                        // startActivity(new Intent(getApplicationContext(),AfterTentorRegistrationActivity.class));
                    } else {
                        // NOT EXISTS ON TENTOR CHILD
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        else {
            startActivity(new Intent(SplashActivity.this,WelcomeScreenActivity.class));
        }


    }
}
