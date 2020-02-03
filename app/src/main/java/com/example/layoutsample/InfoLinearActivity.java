package com.example.layoutsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


/**
 * LinearLayout, FrameLayout 만으로 기존 InfoActivity 를 구현한 액티비티
 */
public class InfoLinearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_linear);
    }

    /**
     * xml의 onClick 속성 을 위한 메서드
     * 액티비티를 종료
     *
     * @param v onClick 속성으로 호출한 뷰
     */
    public void finishActivity(View v) {
        finish();
    }
}
