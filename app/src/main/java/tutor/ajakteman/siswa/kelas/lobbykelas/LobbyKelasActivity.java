package tutor.ajakteman.siswa.kelas.lobbykelas;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import tutor.ajakteman.R;
import tutor.ajakteman.adapter.LobbyKelasAdapter;
import tutor.ajakteman.data.Kelas;
import tutor.ajakteman.data.Siswa;
import tutor.ajakteman.glide.GlideApp;
import tutor.ajakteman.helper.Utils;

public class LobbyKelasActivity extends AppCompatActivity {

    private boolean isJoin = false;
    private boolean isDataLoaded = false;

    private String kelasID = "";
    private String userID;
    private FirebaseDatabase database;
    private DatabaseReference lobbyRef;
    private DatabaseReference userRef;

    private LobbyKelasAdapter adapter;
    private RecyclerView recyclerView;
    private TextView dateText;

    private TextView locationText;
    private View locationContainer;

    private ImageView coverImage;
    private CircleImageView profileImage;
    private FloatingActionButton fab;

    FirebaseRecyclerAdapter mAdapter;

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        TextView textNama, textOnline, textLastOnline;

        public MyViewHolder(View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.textNama);
            textOnline = itemView.findViewById(R.id.textOnline);
            textLastOnline = itemView.findViewById(R.id.textLastOnline);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_kelas);

        database = FirebaseDatabase.getInstance();

        userID = String.valueOf(FirebaseAuth.getInstance()
                .getCurrentUser().getUid());

        Kelas kelasObj = (Kelas) getIntent().getSerializableExtra("KELAS-OBJECT");

        kelasID = kelasObj.getKelasID();
        String jenjang = kelasObj.getJenjang();
        String jurusan = kelasObj.getJurusan();
        String kelas = kelasObj.getKelas();


//        lobbyRef = database.getReference("kelas/"+ kelasID + "/lobby");

        lobbyRef = FirebaseDatabase.getInstance()
                .getReference("kelas")
                .child(jenjang + "/" + (((!jurusan.isEmpty()) ? jurusan + "/" : "")))
                .child(kelas).child(kelasID).child("lobby");

        userRef = database.getReference("users").child("siswa");


        // #################################################################
        // ------------------ FIREBASE RECYCLER ADAPTER --------------------

        Query query = lobbyRef;
        FirebaseRecyclerOptions<Siswa> options =
                new FirebaseRecyclerOptions.Builder<Siswa>()
                        .setIndexedQuery(query, userRef, Siswa.class)
                        .build();
//        adapter = new LobbyKelasAdapter(options);

        mAdapter = new FirebaseRecyclerAdapter<Siswa, MyViewHolder>(options) {
            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.lobby_user_item, parent, false);

                return new MyViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(MyViewHolder holder, int position, Siswa model) {
                holder.textNama.setText(model.getNama());
                holder.textOnline.setText(model.isOnline() ? "Online" : "Offline");

                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(model.getLastOnline());
                String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

                holder.textLastOnline.setText( (model.getLastOnline()==0L) ? "" : "Last Online : \n" + date);

                if (model.getKelas() != null){
//                    Toast.makeText(getApplicationContext(), "MAP SIZE : " + model.getKelas(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
        mAdapter.startListening();

        // #################################################################


        String day = kelasObj.getWaktu().get("hari");
        String month = kelasObj.getWaktu().get("bulan");
        String year = (Integer.valueOf(kelasObj.getWaktu().get("tahun")) - 2000) + "";

        String date = day + " / " + month + " / " + year;

        dateText = findViewById(R.id.dateText);
        dateText.setText(date);

        String placename = kelasObj.getLokasi().get("namatempat");
        String address = kelasObj.getLokasi().get("alamat");

        locationText = findViewById(R.id.locationText);
        if (placename.contains("°") && placename.contains("\"S") && placename.contains("\"E")){
            locationText.setText(address);
        } else {
            locationText.setText(placename);
        }

        locationContainer = findViewById(R.id.locationContainer);
        locationContainer.setOnClickListener(view -> {
            AlertDialog alertDialog = Utils.buildLocationDetailDialog(this, placename, address);
            alertDialog.show();
        });


        coverImage = findViewById(R.id.coverImage);
        profileImage = findViewById(R.id.profileImage);

        GlideApp.with(LobbyKelasActivity.this)
                .load(R.drawable.background_image)
                .centerCrop()
                .into(coverImage);

        GlideApp.with(LobbyKelasActivity.this)
                .load(R.drawable.avatar)
                .centerCrop()
                .into(profileImage);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                YoYo.with(Techniques.Tada)
                        .duration(500)
                        .playOn(fab);

                if (isDataLoaded) {

                    if (isJoin) {

                        isJoin = false;
                        lobbyRef.child(userID).removeValue();
                        userRef.child(userID).child("kelas").child(kelasID).removeValue();
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                    } else {

                        isJoin = true;
                        lobbyRef.child(userID).child("status").setValue("ready");
                        userRef.child(userID).child("kelas").child(kelasID).child("status").setValue("ready");
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));

                    }

                }

            }
        });

        Query lobbyQuery = lobbyRef.orderByKey().equalTo(userID);
        lobbyQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isDataLoaded = true;
                if (dataSnapshot.exists()){
                    isJoin = true;

                    // change fab icon
                    // change with exit join icon
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));

                    for (DataSnapshot child : dataSnapshot.getChildren()){
                        // Toast.makeText(getApplicationContext(), "KEY : " + child.getKey(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
