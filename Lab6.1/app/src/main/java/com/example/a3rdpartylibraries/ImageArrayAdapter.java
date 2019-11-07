package com.example.a3rdpartylibraries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageArrayAdapter extends ArrayAdapter<Image> {

    static final int VIEW_TYPE_COUNT = 1;
    static final int VIEW_TYPE = 2;

    public ImageArrayAdapter(Context context, ArrayList<Image> images) {
        super(context, 0, images);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        Image image = getItem(position);
        return VIEW_TYPE;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Image image = getItem(position);

        if (convertView == null) {
            int layoutId = R.layout.image_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        String imageURL = image.getImgUrlAddress();
        Picasso.get().load(imageURL).fit().centerCrop().into(imageView);
        return convertView;
    }
}
