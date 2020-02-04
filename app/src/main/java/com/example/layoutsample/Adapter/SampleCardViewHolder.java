package com.example.layoutsample.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutsample.FoodData;
import com.example.layoutsample.InfoActivity;
import com.example.layoutsample.R;
import com.example.layoutsample.Util.BitmapCacheManager;
import com.example.layoutsample.Util.ImageResourceLoader;

/**
 * RecyclerView.ViewHolder 를 상속받아 만든 클래스
 * 대상 루트뷰의 자식뷰와 해당 카드 데이터를 가지고 있음
 */
public class SampleCardViewHolder extends RecyclerView.ViewHolder {
    // 카드뷰의 배경 뷰
    private final ImageView mBackground;
    // 카드뷰의 이름 제목
    private final TextView mName;
    // 카드뷰의 별 버튼
    private final Button mStar;
    // 카드뷰의 내부 Layout
    private final ViewGroup mLayout;

    // 카드뷰의 해당하는 데이터
    private FoodData mData;

    /**
     * 카드뷰의 자식뷰 초기화, 리스너 등록
     *
     * @param card 현재 뷰홀더에 멤버 등록할 루트뷰
     */
    public SampleCardViewHolder(@NonNull View card) {
        super(card);

        // Id로 요소 가져오기
        mLayout = card.findViewById(R.id.sample_card_layout);
        mBackground = mLayout.findViewById(R.id.sample_card_background);
        mName = mLayout.findViewById(R.id.sample_card_name);
        mStar = mLayout.findViewById(R.id.sample_card_star);

        // mStar 클릭리스너 등록
        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.stared = !mData.stared;
                setStarFill(mData.stared);
            }
        });

        // mBackground 클릭리스너 등록
        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = mLayout.getContext();
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("payload", mData);

                context.startActivity(intent);
            }
        });
    }

    /**
     * 배경 뷰 이미지 지정
     * @param imageId 이미지 리소스id
     */
    private void setImage(int imageId) {
        Resources resources = mLayout.getResources();

        // 로더에게 비동기적으로 Drawable 리소스 로드작업을 맡김
        ImageResourceLoader loader = new ImageResourceLoader(new BitmapCacheManager());
        //이미지 배율 조정
        loader.setScale(0.6f);
        loader.loadImageResource(resources, imageId, mBackground);
    }

    /**
     * 이름 뷰 제목 지정
     * @param name 이름 제목 문자열
     */
    private void setName(String name) {
        mName.setText(name);
    }

    /**
     * 별 버튼뷰 on/off 표시 지정
     * @param isFill on/off 여부
     */
    private void setStarFill(boolean isFill) {
        Resources resources = mLayout.getResources();
        Drawable drawable = null;

        if(isFill) {
            drawable = resources.getDrawable(R.drawable.ic_star_white_24dp, null);
        } else {
            drawable = resources.getDrawable(R.drawable.ic_star_border_white_24dp, null);
        }
        mStar.setBackground(drawable);
    }

    /**
     * 데이터 적용하는 곳
     * 이미지, 이름, 별표 여부 자식뷰에 적용
     * @param data 적용할 데이터 (FoodData)
     */
    public void applyData(FoodData data) {
        mData = data;
        setImage(mData.imageId);
        setName(mData.name);
        setStarFill(mData.stared);
    }


}
