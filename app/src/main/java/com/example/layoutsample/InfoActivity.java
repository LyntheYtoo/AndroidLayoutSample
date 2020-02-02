package com.example.layoutsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private ImageView mBackground;
    private TextView mName;
    private Button mStar;

    private MockCardData mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //view initialize
        mBackground = findViewById(R.id.info_imageview);
        mName = findViewById(R.id.info_name);
        mStar = findViewById(R.id.info_star);

        //get intent payload
        Intent intent = getIntent();
        if(intent != null) {
            mData = intent.getParcelableExtra("payload");
        }
        //get save state
        if(savedInstanceState != null) {
            mData = savedInstanceState.getParcelable("payload");
        }

        if(mData != null) {
            setName(mData.name);
            setImage(mData.imageId);
            setStarFill(mData.getState());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("payload", mData);
    }

    public void setImage(int imageId) {
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(imageId, null);

        mBackground.setImageDrawable(drawable);
    }
    public void setName(String name) {
        mName.setText(name);
    }
    public void setStarFill(boolean isFill) {
        Resources resources = getResources();
        Drawable drawable = null;

        if(isFill) {
            drawable = resources.getDrawable(R.drawable.ic_star_white_24dp, null);
        } else {
            drawable = resources.getDrawable(R.drawable.ic_star_border_white_24dp, null);
        }
        mStar.setBackground(drawable);
    }
    public void finishActivity(View v) {
        finish();
    }
}
