package com.example.layoutsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutsample.Adapter.SampleCardAdapter;

/**
 * MainActivity
 * 앱 진입 액티비티
 */
public class MainActivity extends AppCompatActivity {
    //데이터 객체 배열
    private FoodData[] mDataArray;

    /**
     * 컨텐트뷰 설정
     * 데이터 초기화, 이전 상태 로드
     * 리사이클러뷰 초기화 ( 레이아웃매니저, 어댑터 설정 )
     *
     * @param savedInstanceState 이전 데이터를 저장하기위한 객체
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SupportActionBar의 높낮이를 낮춘다
        if(getSupportActionBar() != null) getSupportActionBar().setElevation(0);


        //이전 상태가 있을 때만 새로운 데이터 객체 생성
        if(savedInstanceState == null) {
            //Mock Data Initialize
            mDataArray = new FoodData[5];
            mDataArray[0] = new FoodData(R.drawable.chicken, "Chicken");
            mDataArray[1] = new FoodData(R.drawable.doughnuts, "Doughnuts");
            mDataArray[2] = new FoodData(R.drawable.icecream, "Icecream");
            mDataArray[3] = new FoodData(R.drawable.maratang, "Maratang");
            mDataArray[4] = new FoodData(R.drawable.noodle, "Noodle");
        } else {
            mDataArray = (FoodData[])savedInstanceState.getParcelableArray("payload");
        }


        //RecyclerView initialize
        RecyclerView recyclerView = findViewById(R.id.sample_recyclerview);

        //specify LayoutManager inside RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //specify Adapter
        SampleCardAdapter adapter = new SampleCardAdapter();
        adapter.setDataList(mDataArray);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 현재 상태 보존을 위한 메서드
     * @param outState 상태를 담을 Bundle 객체
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray("payload", mDataArray);
    }

    /**
     * xml onClick속성에 붙여줄 메서드
     * InfoLinearActivity 를 호출한다
     *
     * @param v onClick 속성으로 호출한 뷰
     */
    public void startInfoLinearActivity(View v) {
        startActivity(new Intent(this, InfoLinearActivity.class));
    }
}
