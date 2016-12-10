package com.example.ahmed.teachercalender.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahmed.teachercalender.CustomQuizz;
import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.R;
import com.example.ahmed.teachercalender.database.Quizz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Ahmed on 01/12/2016.
 */
public class QuizzAdapter extends BaseAdapter {
private Context context;
    ArrayList<CustomQuizz>quizzs;
connection conn;
    public QuizzAdapter(Context context, ArrayList<CustomQuizz> quizzs,connection conn) {
        this.context = context;
        this.quizzs = quizzs;
        this.conn=conn;
    }

    @Override
    public int getCount() {
        return quizzs.size();
    }

    @Override
    public Object getItem(int position) {
        return quizzs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view=inflater.inflate(R.layout.list_view_student_quizzes,null);
        TextView name=(TextView) view.findViewById(R.id.listStuQuiz_Name);

        TextView date=(TextView) view.findViewById(R.id.listStuQuiz_Date);
        TextView value=(TextView) view.findViewById(R.id.quizzDegree);
        Button change=(Button)view.findViewById(R.id.changeDegree) ;
        name.setText(quizzs.get(position).getName());
        date.setText((new SimpleDateFormat("dd-MM-yyyy").format(quizzs.get(position).getDate())).toString());
        value.setText(quizzs.get(position).getValue()+"");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conn.connect(position,"QuizzAdapter");
            }
        });
        return view;
    }
}
