package tutor.ajakteman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(String.valueOf(dataSnapshot.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).child("state").getValue()).equalsIgnoreCase("siswa")){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(),AfterTentorRegistrationActivity.class));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else {
            startActivity(new Intent(SplashActivity.this,WelcomeScreenActivity.class));
        }

//        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}
