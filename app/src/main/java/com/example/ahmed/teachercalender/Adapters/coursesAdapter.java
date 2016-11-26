package com.example.ahmed.teachercalender.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.teachercalender.R;
import com.example.ahmed.teachercalender.database.Subject;

import java.util.ArrayList;

/**
 * Created by Ahmed on 11/11/2016.
 */
public class coursesAdapter extends BaseAdapter {


    ArrayList<Subject>arrayList;
   Context context;

    public coursesAdapter(ArrayList<Subject> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view=inflater.inflate(R.layout.grid_view_material,null);
        ImageView img= (ImageView) view.findViewById(R.id.materialImg);


        TextView textView=(TextView)view.findViewById(R.id.materialName);

          textView.setText((arrayList.get(position).getName()));
        return view;
    }
}
