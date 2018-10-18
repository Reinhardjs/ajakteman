package tutor.ajakteman.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import tutor.ajakteman.POJO.FilteredKelas;

import tutor.ajakteman.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {

    private Context context;

    public ArrayList<FilteredKelas> kelasList, filterList;
    KelasSearchFilter filter;

    public RecyclerViewAdapter(Context context, ArrayList<FilteredKelas> kelasList){
        this.context = context;
        this.kelasList = kelasList;
        this.filterList = kelasList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil_cari_kelas_item, null);

        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

//        holder.id.setText(""+(holder.getLayoutPosition()+1));
//        holder.nameTxt.setText(kelasList.get(holder.getLayoutPosition()).getName());

    }

    @Override
    public int getItemCount() {
        return kelasList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new KelasSearchFilter(filterList, this);
        }

        return filter;
    }
}

class MyHolder extends RecyclerView.ViewHolder {

    //OUR VIEWS
    public TextView id;
    public TextView nameTxt;

    public MyHolder(View itemView) {
        super(itemView);

//        this.id = itemView.findViewById(R.id.id);
//        this.nameTxt= (TextView) itemView.findViewById(R.id.content);
    }
}
