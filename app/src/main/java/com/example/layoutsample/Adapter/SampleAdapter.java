package com.example.layoutsample.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutsample.MockCardData;
import com.example.layoutsample.R;

import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SampleCardViewHolder> {
    public static final String TAG = "SampleAdapter";
    private List<MockCardData> mCardDataList;

    public void setDataList(List<MockCardData> cardDataList) {
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
        MockCardData element = mCardDataList.get(position);
        holder.setImage(element.imageId);
        holder.setName(element.name);
        
    }

    @Override
    public int getItemCount() {
        return mCardDataList.size();
    }
}
