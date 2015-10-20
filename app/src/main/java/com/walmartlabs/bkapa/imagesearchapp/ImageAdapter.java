package com.walmartlabs.bkapa.imagesearchapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by bkapa on 10/19/15.
 */
public class ImageAdapter extends ArrayAdapter<ImageResult> {

    public ImageAdapter(Context context,  List<ImageResult> objects) {
        super(context,R.layout.item_image_result,objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageResult imageResult = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvText);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        ivImage.setImageResource(0);
      //  tvTitle.setText(imageResult.getTitle());
        Picasso.with(getContext()).load(imageResult.getThumbUrl()).into(ivImage);
        return convertView;
    }
}
