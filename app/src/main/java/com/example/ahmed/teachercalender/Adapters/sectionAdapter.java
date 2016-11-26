package com.example.ahmed.teachercalender.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ahmed.teachercalender.R;
import com.example.ahmed.teachercalender.database.Section;

import java.util.ArrayList;

/**
 * Created by Ahmed on 12/11/2016.
 */
public class sectionAdapter extends BaseAdapter {

  private  ArrayList<Section>sections;

    public sectionAdapter(ArrayList<Section> sections, Context context,boolean flag) {
        this.sections = sections;
        this.context = context;
        this.flag=flag;
    }

    Context context;

boolean flag=false;

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public Object getItem(int position) {
        return sections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view=inflater.inflate(R.layout.section_grid_view,null);
        TextView name=(TextView)view.findViewById(R.id.sectionName);
        TextView day=(TextView)view.findViewById(R.id.day);
        TextView time=(TextView)view.findViewById(R.id.Time);
        TextView location=(TextView)view.findViewById(R.id.location);

          name.setText(sections.get(position).getName());
        if(flag) {
            day.setText(sections.get(position).getDay());
            time.setText(sections.get(position).getTime());
            location.setText(sections.get(position).getLocation());
        }
        return view;
    }
}
