package com.example.androidtamagotchis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class TamagotchiArrayAdapter extends ArrayAdapter<TamagotchiThread> {

    private static final int VIEW_TYPE_COUNT = 1;

    TamagotchiArrayAdapter(Context context, ArrayList<TamagotchiThread> tamagotchis) {
        super(context, 0, tamagotchis);
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TamagotchiThread thread = getItem(position);
        if (convertView == null) {
            int layoutId = R.layout.tamagotchi_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        LinearLayout linearLayout = convertView.findViewById(R.id.layout);
        linearLayout.setBackgroundColor(Objects.requireNonNull(thread).getStatusColor());
        TextView textViewID = convertView.findViewById(R.id.tvID);
        textViewID.setText(String.format(Locale.getDefault(), "%d", thread.getID() + 1));
        TextView textViewFood = convertView.findViewById(R.id.tvFood);
        textViewFood.setText(String.format(Locale.getDefault(), "%d", thread.getFood()));
        return convertView;
    }
}
