package com.voodoo94.app.technieks2015;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Shreesha on 3/6/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String mImgUrl;

    public ImageAdapter(Context context, String url) {
        this.mContext = context;
        this.mImgUrl = url;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = (ImageView) convertView.findViewById(R.id.gallery_imageview);
            imageView.setPadding(8, 8, 8, 8);
            Picasso.with(mContext)
                    .load(mImgUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .fit()
                    .centerCrop()
                    .into(imageView);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
    };
}
