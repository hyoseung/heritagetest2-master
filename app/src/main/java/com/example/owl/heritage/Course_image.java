package com.example.owl.heritage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hyoseung on 2016-10-07.
 */
public class Course_image extends AlertDialog {
    private String path;
    private Context mContext;
    private ImageView imageView;

    public Course_image(Context context, String path) {
        super(context);
        this.path = path;
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

        setContentView(R.layout.course_image);

        imageView = (ImageView) findViewById(R.id.camera_image);

        try {
            Uri uri = Uri.parse(path);
            Glide.with(mContext)
                    .load(uri)
                    .into(imageView);

        } catch (Exception e) {
            Log.i("imagebutton error", " "+e.toString());
        }
    }
}
