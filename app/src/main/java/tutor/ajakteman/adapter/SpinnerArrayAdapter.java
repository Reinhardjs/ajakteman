package tutor.ajakteman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tutor.ajakteman.R;

public class SpinnerArrayAdapter extends ArrayAdapter<String> {

    ArrayList<String> list = new ArrayList<String>();

    public SpinnerArrayAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.list = objects;
    }

    public void resetList(ArrayList<String> objects){
        list.clear();
        list.addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.spinner_item, null);
        TextView textView = v.findViewById(R.id.text);
        textView.setText(list.get(position));
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.spinner_dropdown_item, null);
        TextView textView = v.findViewById(R.id.text);
        textView.setText(list.get(position));
        return v;
    }
}
