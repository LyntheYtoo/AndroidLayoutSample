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
 * Food의 설명을 보여주는 액티비티
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
        }

        //데이터 등록
        if(mData != null) {
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

    /**
     * 이미지 리소스 배경뷰에 등록
     * @param imageId 이미지 리소스 id
     */
    public void setImage(int imageId) {
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(imageId, null);

        mBackground.setImageDrawable(drawable);
    }

    /**
     * 문자열 이름뷰에 등록
     * @param name 이름 문자열
     */
    public void setName(String name) {
        mName.setText(name);
    }

    /**
     * 스타 버튼의 별이 채워질지 말지 결정
     * @param isFill 결정여부
     */
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

    /**
     * xml에서 onClick 속성으로 사용할 메서드
     * @param v 속성에 등록한 뷰
     */
    public void finishActivity(View v) {
        finish();
    }
}
