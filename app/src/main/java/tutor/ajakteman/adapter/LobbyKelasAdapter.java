package tutor.ajakteman.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

import java.util.Calendar;
import java.util.Locale;

import tutor.ajakteman.R;
import tutor.ajakteman.adapter.firebase.FirebaseRecyclerAdapter;
import tutor.ajakteman.data.Siswa;

public class LobbyKelasAdapter extends FirebaseRecyclerAdapter<Siswa, LobbyKelasAdapter.ViewHolder> {

    public LobbyKelasAdapter(FirebaseRecyclerOptions<Siswa> options) {
        super(options, true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, int position, Siswa model) {
        holder.textNama.setText(model.getNama());
        holder.textOnline.setText(model.isOnline() ? "Online" : "Offline");

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(model.getLastOnline());
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

        holder.textLastOnline.setText( (model.getLastOnline()==0L) ? "" : "Last Online : \n" + date);
    }

    @Override
    protected void onChildUpdate(Siswa model, ChangeEventType type, DataSnapshot snapshot, int newIndex, int oldIndex) {
        super.onChildUpdate(model, type, snapshot, newIndex, oldIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder   {
        TextView textNama, textOnline, textLastOnline;

        public ViewHolder(View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.textNama);
            textOnline = itemView.findViewById(R.id.textOnline);
            textLastOnline = itemView.findViewById(R.id.textLastOnline);
        }
    }
}