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

/**
 * Food의 각 항목 설명을 보여주는 액티비티
 */
public class InfoActivity extends AppCompatActivity {

    // Food 배경 사진 뷰
    private ImageView mBackground;
    // Food 이름 뷰
    private TextView mName;
    // Food 별 표시 뷰
    private Button mStar;


    private FoodData mData;

    /**
     * 이전상태, 인텐트에서 데이터를 받아 저장
     * 뷰 초기화
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();

        //view initialize
        mBackground = findViewById(R.id.info_imageview);
        mName = findViewById(R.id.info_name);
        mStar = findViewById(R.id.info_star);

        // 우선순위 대로 데이터를 받음
        // 1. savedInstanceSate
        // 2. intent
        if(savedInstanceState != null) {
            mData = savedInstanceState.getParcelable("payload");
        } else if(intent != null) {
            mData = intent.getParcelableExtra("payload");
        } else {
            setName(mData.name);
            setImage(mData.imageId);
            setStarFill(mData.stared);
        }
    }

    /**
     * 현재 상태 보존을 위한 메서드
     * @param outState 상태 저장 할 객체
     */
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
