package tutor.ajakteman.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;

import tutor.ajakteman.R;
import tutor.ajakteman.data.Kelas;
import tutor.ajakteman.siswa.kelas.lobbykelas.LobbyKelasActivity;

public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<Kelas, MyHolder> {
//implements Filterable

    final public static int LAYOUT_LIGHT = R.layout.kelas_item_light;
    final public static int LAYOUT_DARK = R.layout.kelas_item;

    int layout_res;
    private Context context;
    public ArrayList<Kelas> kelasList, filterList;
    KelasSearchFilter filter;
    Fragment mFragment;

    public RecyclerViewAdapter(Fragment fragment, Context context, FirebaseRecyclerOptions<Kelas> options){
        super(options);
        this.mFragment = fragment;
        this.context = context;
        layout_res = LAYOUT_DARK;
//        this.kelasList = kelasList;
//        this.filterList = kelasList;
    }

    public void setLayoutType(int type){
        this.layout_res = type;
    }

    public void populateList(String jenjang, String jurusan, String kelas, String pelajaran){
//        DatabaseReference kelasRef = FirebaseDatabase.getInstance()
//                .getReference("kelas")
//                .child(jenjang + "/" + (((!jurusan.isEmpty()) ? jurusan + "/" : "")))
//                .child(kelas);
//
//        kelasRef.orderByChild("pelajaran").equalTo(pelajaran);
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot kelas : dataSnapshot.getChildren()){
//                            Kelas kelasChild = kelas.getValue(Kelas.class);
//                            kelasList.add(kelasChild);
//                            Toast.makeText(context, kelasChild.getKelasID(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        notifyItemRangeInserted(0, kelasList.size());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout_res, null);
        return new MyHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyHolder holder, int position, @NonNull final Kelas model) {
        final String kelasID = model.getKelasID();
        String jenjang = model.getJenjang();
        String jurusan = model.getJurusan();
        String kelas = model.getKelas();
        String pelajaran = model.getPelajaran();

        String textContent = jenjang + "-"
                + kelas + "-"
                + ((!jurusan.isEmpty()) ? jurusan + "-" : "")
                + pelajaran;

        holder.textContent.setText(textContent);

        DatabaseReference kelasRef = FirebaseDatabase.getInstance()
                .getReference("kelas")
                .child(jenjang + "/" + (((!jurusan.isEmpty()) ? jurusan + "/" : "")))
                .child(kelas)
                .child(kelasID)
                .child("lobby");

        kelasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                holder.ratingBar.setRating(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LobbyKelasActivity.class);
                intent.putExtra("KELAS-OBJECT", model);
                intent.putExtra("DATE-DAY", "09");
                intent.putExtra("DATE-MONTH", "11");
                intent.putExtra("DATE-YEAR", "18");
                mFragment.startActivityForResult(intent, 2);
            }
        });
    }

//    @Override
//    public Filter getFilter() {
//        if (filter == null){
//            filter = new KelasSearchFilter(filterList, this);
//        }
//
//        return filter;
//    }
}

class MyHolder extends RecyclerView.ViewHolder {

    //OUR VIEWS
    public View container;
    public ScaleRatingBar ratingBar;
    public TextView textContent;

    public MyHolder(View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.container);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        textContent = itemView.findViewById(R.id.textContent);
    }
}
