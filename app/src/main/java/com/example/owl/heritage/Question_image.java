package com.example.owl.heritage;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by hyoseung on 2016-10-26.
 */
public class Question_image extends AlertDialog {
    private String path;
    private Context mContext;
    private ImageView imageView;

    public Question_image(Context context) {
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

        setContentView(R.layout.question_image);

        int imageResource = mContext.getResources().getIdentifier("explain", "drawable", "com.example.owl.heritage");
        imageView = (ImageView) findViewById(R.id.question_image);

        Glide.with(mContext)
                .load(imageResource)
                .into(imageView);
    }
}