package com.example.layoutsample.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutsample.FoodData;
import com.example.layoutsample.R;

/**
 * 리사이클러뷰에 적용할 어댑터
 * 데이터를 참조하여 뷰홀더 생성, 바인드작업을 담당
 */
public class SampleCardAdapter extends RecyclerView.Adapter<SampleCardViewHolder> {
    // 데이터 저장장소
    private FoodData[] mDataList;

    /**
     * 데이터리스트 등록하는 곳
     * @param dataList 등록할 데이터 리스트
     */
    public void setDataList(FoodData[] dataList) {
        mDataList = dataList;
    }


    // RecyclerView.Adapter Override
    /**
     * 리사이클러뷰가 관리할 뷰홀더를 생성하는 곳
     * @param parent 리사이클러뷰의 레이아웃 매니저
     * @param viewType 현재 사용하지않음
     * @return 생성된 뷰홀더
     */
    @NonNull
    @Override
    public SampleCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater 를 이용하여 현재 컨텍스트로 해당 xml에 기술된 뷰를 인스턴스화
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.sample_cardview, parent, false);

        return new SampleCardViewHolder(cardView);
    }

    /**
     * 재활용가능 뷰홀더에 데이터를 바인드하는 곳
     * @param holder 바인딩 대상 뷰홀더
     * @param position 현재 몇번째 뷰홀더인지 나타내는 것
     */
    @Override
    public void onBindViewHolder(@NonNull SampleCardViewHolder holder, int position) {
        FoodData element = mDataList[position];
        holder.applyData(element);
    }

    /**
     * 현재 아이템이 몇개인지 나타내는 곳
     * @return 아이템 갯수
     */
    @Override
    public int getItemCount() {
        return mDataList.length;
    }
}
