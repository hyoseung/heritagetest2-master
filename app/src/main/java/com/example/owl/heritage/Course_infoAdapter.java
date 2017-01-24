package com.example.owl.heritage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hyoseung on 2016-09-26.
 */
public class Course_infoAdapter extends BaseAdapter {

    private ArrayList<Course_item> list;
    private Context mContext;
    private ViewHolder viewHolder;

    public Course_infoAdapter(ArrayList<Course_item> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        mContext = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_view_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text_num = (TextView) convertView.findViewById(R.id.course_number);
            viewHolder.text_num.setText(String.valueOf(list.get(pos).getNum()));
            viewHolder.text_name = (TextView) convertView.findViewById(R.id.course_name);
            viewHolder.text_name.setText(list.get(pos).getName());
            viewHolder.imageButton = (ImageView) convertView.findViewById(R.id.course_image);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(!list.get(pos).getUri().equals("null")) {

            try {
                Uri uri = Uri.parse(list.get(pos).getUri());
                Glide.with(mContext)
                        .load(uri)
                        .override(180, 100)
                        .bitmapTransform(new CropCircleTransformation(new CustomBitmapPool()))
                        .into(viewHolder.imageButton);

            } catch (Exception e) {
                Log.i("imagebutton error", " "+e.toString());
            }
            convertView.setTag(viewHolder);
        }

        return convertView;
    }

    class ViewHolder {
        public TextView text_num = null;
        public ImageView imageButton = null;
        public TextView text_name = null;
    }

    public class CustomBitmapPool implements BitmapPool {
        @Override
        public int getMaxSize() { return 0; }
        @Override
        public void setSizeMultiplier(float sizeMultiplier) {}
        @Override
        public boolean put(Bitmap bitmap) { return false; }
        @Override
        public Bitmap get(int width, int height, Bitmap.Config config) { return null; }
        @Override
        public Bitmap getDirty(int width, int height, Bitmap.Config config) { return null; }
        @Override
        public void clearMemory() {}
        @Override
        public void trimMemory(int level) {}
    }

}
