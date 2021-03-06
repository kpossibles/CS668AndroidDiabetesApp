package com.example.njjeske.cs668androiddiabetesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<DB_Object> {
    // Store a member variable for the data
    private ArrayList<DB_Object> objects;
    private Context context;

    private static class ViewHolder {
        TextView tvType, tvDate, tvTime, tvDescription;
        int id;
        DB_Object activity;
    }

    public DataAdapter(Context context, ArrayList<DB_Object> objects) {
        super(context, 0, objects);
        this.objects = objects;
        this.context = context;
    }

    @Nullable
    @Override
    public DB_Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DB_Object activity = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_result, parent, false);
            // Lookup view for data population
            viewHolder.tvType = (TextView) convertView.findViewById(R.id.ItemResult_Type);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.ItemResult_Date);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.ItemResult_Time);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.ItemResult_Description);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.id = activity.getId();
        viewHolder.tvType.setText(activity.getActivityType());
        viewHolder.tvDate.setText(activity.getDate());
        viewHolder.tvTime.setText(activity.getTime());
        viewHolder.tvDescription.setText(activity.getDescription());

        //set onclicklistener
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (context.getClass().equals(RegimenActivity.class)) {
                    i = new Intent(context, EditRegimen.class);
                } else {
                    i = new Intent(context, EditActivity.class);
                }
                i.putExtra("Data", viewHolder.id);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
