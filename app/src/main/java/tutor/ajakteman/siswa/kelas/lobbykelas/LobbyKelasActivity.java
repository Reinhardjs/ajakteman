package tutor.ajakteman.siswa.kelas.lobbykelas;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;
import tutor.ajakteman.R;
import tutor.ajakteman.glide.GlideApp;

public class LobbyKelasActivity extends AppCompatActivity {

    private boolean isJoin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_kelas);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myConnectionsRef = database.getReference("users/reinhard/connections");
        myConnectionsRef.setValue(true);
        myConnectionsRef.onDisconnect().setValue(false);


        // since I can connect from multiple devices, we store each connection instance separately
        // any time that connectionsRef's value is null (i.e. has no children) I am offline


//        final DatabaseReference connectedRef = database.getReference(".info/connected");
//        connectedRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                boolean connected = snapshot.getValue(Boolean.class);
//                if (connected) {
//
//                    // when this device disconnects, remove it
//                    myConnectionsRef.onDisconnect().removeValue();
//
//                    // when I disconnect, update the last time I was seen online
//                    lastOnlineRef.onDisconnect().setValue(ServerValue.TIMESTAMP);
//
//                    // add this device to my connections list
//                    // this value could contain info about the device or a timestamp too
//                }
//            }

//            @Override
//            public void onCancelled(DatabaseError error) {
////                System.err.println("Listener was cancelled at .info/connected");
//                Toast.makeText(getApplicationContext(), "Listener was cancelled at .info/connected", Toast.LENGTH_SHORT).show();
//            }
//        });


        String day = getIntent().getStringExtra("DATE-DAY");
        String month = getIntent().getStringExtra("DATE-MONTH");
        String year = getIntent().getStringExtra("DATE-YEAR");

        String date = day + " / " + month + " / " + year;

        TextView dateText = findViewById(R.id.dateText);
        dateText.setText(date);

        ImageView coverImage = findViewById(R.id.coverImage);
        CircleImageView profileImage = findViewById(R.id.profileImage);

        GlideApp.with(LobbyKelasActivity.this)
                .load(R.drawable.background_image)
                .centerCrop()
                .into(coverImage);

        GlideApp.with(LobbyKelasActivity.this)
                .load(R.drawable.avatar)
                .centerCrop()
                .into(profileImage);

        if (isJoin){

        } else {

        }

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Tada)
                        .duration(500)
                        .playOn(fab);

                if (isJoin){

                } else {

                }
            }
        });


    }

    protected void onStart(){
        super.onStart();
    }
}
