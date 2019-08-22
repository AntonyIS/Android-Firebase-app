package com.example.firebapp;


//CustomAdapter
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Item> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<Item> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.musername = convertView.findViewById(R.id.tvuname);
            viewHolder.mfullnames = convertView.findViewById(R.id.tvfullname);
            viewHolder.memail =  convertView.findViewById(R.id.tvemail);
            viewHolder.mpassword =  convertView.findViewById(R.id.tvpassword);
            viewHolder.mbtndelete =  convertView.findViewById(R.id.btndelete);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        final Item person = data.get(position);//modify here
        viewHolder.musername.setText(person.getUsername());//modify here
        viewHolder.mfullnames.setText(person.getFullnames());//modify here
        viewHolder.memail.setText(person.getEmail());//modify here
        viewHolder.mpassword.setText(person.getPassword());//modify here
        viewHolder.mbtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/"+person.getId());
                ref.removeValue();
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }
    static class ViewHolder {
        TextView musername;
        TextView mfullnames;
        TextView memail;
        TextView mpassword;
        Button mbtndelete;
    }

}