package tutor.ajakteman.adapter;

import android.widget.Filter;
import java.util.ArrayList;

import tutor.ajakteman.POJO.FilteredKelas;
import tutor.ajakteman.data.Kelas;

public class KelasSearchFilter extends Filter {

    RecyclerViewAdapter adapter;
    ArrayList<FilteredKelas> filterList;

    public KelasSearchFilter(ArrayList<FilteredKelas> filterList, RecyclerViewAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<FilteredKelas> filteredItems =new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
//                if(filterList.get(i).getName().toUpperCase().contains(constraint))
//                {
//                    //ADD PLAYER TO FILTERED PLAYERS
//                    filteredItems.add(filterList.get(i));
//                }
            }

            results.count= filteredItems.size();
            results.values= filteredItems;
        }

        else {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.kelasList = (ArrayList<Kelas>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}



