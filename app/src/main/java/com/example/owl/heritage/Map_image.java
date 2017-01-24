package com.example.owl.heritage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by owl on 2016-10-27.
 */

public class Map_image extends AlertDialog{
    private String path;
    private Context mContext;
    private ImageView imageView;
    private PhotoViewAttacher mAttacher;

    public Map_image(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.map_image);

        int imageResource = mContext.getResources().getIdentifier("seoulmap", "drawable", "com.example.owl.heritage");
        imageView = (ImageView) findViewById(R.id.map_image);

        Glide.with(mContext)
                .load(imageResource)
                .override(1000,1000)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if(mAttacher != null) mAttacher.update();
                        else mAttacher = new PhotoViewAttacher(imageView);
                        mAttacher.setScaleType(ImageView.ScaleType.CENTER);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
