package com.example.layoutsample.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutsample.FoodData;
import com.example.layoutsample.R;

public class SampleCardAdapter extends RecyclerView.Adapter<SampleCardViewHolder> {
    public static final String TAG = "SampleCardAdapter";
    private FoodData[] mCardDataList;

    public void setDataList(FoodData[] cardDataList) {
        mCardDataList = cardDataList;
    }

    @NonNull
    @Override
    public SampleCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.sample_cardview, parent, false);

        return new SampleCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleCardViewHolder holder, int position) {
        FoodData element = mCardDataList[position];
        holder.applyData(element);
    }

    @Override
    public int getItemCount() {
        return mCardDataList.length;
    }
}
