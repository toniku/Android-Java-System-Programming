package com.example.lunchmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LunchArrayAdapter extends ArrayAdapter<Lunch> {

    private static final int VIEW_TYPE_COUNT = 1;
    private static final int VIEW_TYPE = 2;

    public LunchArrayAdapter(Context context, ArrayList<Lunch> items) {
        super(context, 0, items);
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Lunch lunch = getItem(position);

        if (convertView == null) {
            int layoutId = R.layout.lunch_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView lunch1 = convertView.findViewById(R.id.lunch1);
        TextView lunch2 = convertView.findViewById(R.id.lunch2);

        lunch1.setText(lunch.getLunch1());
        lunch2.setText(lunch.getLunch2());

        return convertView;
    }
}
