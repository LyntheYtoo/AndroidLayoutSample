package com.example.layoutsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.layoutsample.Adapter.SampleCardAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MockCardData[] mDataArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) getSupportActionBar().setElevation(0);

        if(savedInstanceState == null) {
            //Mock Data Initialize
            mDataArray = new MockCardData[5];
            mDataArray[0] = new MockCardData(R.drawable.chicken, "Chicken");
            mDataArray[1] = new MockCardData(R.drawable.doughnuts, "Doughnuts");
            mDataArray[2] = new MockCardData(R.drawable.icecream, "Icecream");
            mDataArray[3] = new MockCardData(R.drawable.maratang, "Maratang");
            mDataArray[4] = new MockCardData(R.drawable.noodle, "Noodle");
        } else {
            mDataArray = (MockCardData[])savedInstanceState.getParcelableArray("payload");
        }


        //RecyclerView Initialize
        RecyclerView recyclerView = findViewById(R.id.sample_recyclerview);

        //specify Layout inside RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //specify Adapter
        SampleCardAdapter adapter = new SampleCardAdapter();
        adapter.setDataList(mDataArray);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray("payload", mDataArray);
    }

    public void startActivity(View v) {

    }
}
