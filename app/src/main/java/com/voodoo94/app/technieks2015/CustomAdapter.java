package com.voodoo94.app.technieks2015;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shreesha on 3/5/2015.
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    List<RowItem> mRowItems;
    int mLayout;

    public static class ViewHolder {
        public TextView mEventName;
        public ImageView mEventImage;
        public String mImageUrl;

        /*public ViewHolder(View view) {
            mEventImage = (ImageView) view.findViewById(R.id.event_imageview);
            mEventDesc = (TextView) view.findViewById(R.id.event_textview);
        }*/
    }

    CustomAdapter(Context context, List<RowItem> rowItems, int layout_id) {
        this.context = context;
        this.mRowItems = rowItems;
        this.mLayout = layout_id;
    }

    @Override
    public int getCount() {
        return mRowItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return mRowItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return mRowItems.indexOf(getItem(pos));
    }

    @Override
    public View getView(int pos, View contentView, ViewGroup parent) {
        ViewHolder mViewHolder;

        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (contentView == null) {
            contentView = mLayoutInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder = new ViewHolder();

            mViewHolder.mEventName = (TextView) contentView.findViewById(R.id.event_textview);
            mViewHolder.mEventImage = (ImageView) contentView.findViewById(R.id.event_imageview);

            contentView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) contentView.getTag();
        }

        RowItem mRowPos = mRowItems.get(pos);

        Log.d("LOG_TAG", mRowPos.getEventName());

        mViewHolder.mEventName.setText(mRowPos.getEventName());
        mViewHolder.mEventName.setTypeface(EventActivity.cb);
        mViewHolder.mImageUrl = mRowPos.getImgUrl();
        Picasso.with(context)
                .load(mViewHolder.mImageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fit().centerCrop()
                .into(mViewHolder.mEventImage);

        return contentView;
    }
}
