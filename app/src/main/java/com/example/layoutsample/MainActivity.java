package com.example.layoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.layoutsample.Adapter.SampleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MockCardData> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) getSupportActionBar().setElevation(0);

        //Mock Data Initialize
        mDataList = new ArrayList<>();
        mDataList.add(new MockCardData(R.drawable.chicken, "Chicken"));
        mDataList.add(new MockCardData(R.drawable.doughnuts, "Doughnuts"));
        mDataList.add(new MockCardData(R.drawable.icecream, "Icecream"));
        mDataList.add(new MockCardData(R.drawable.maratang, "Maratang"));
        mDataList.add(new MockCardData(R.drawable.noodle, "Noodle"));

        //RecyclerView Initialize
        RecyclerView recyclerView = findViewById(R.id.sample_recyclerview);

        //specify Layout inside RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //specify Adapter
        SampleAdapter adapter = new SampleAdapter();
        adapter.setDataList(mDataList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
