package com.example.layoutsample.Adapter;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutsample.R;
import com.example.layoutsample.Util.BitmapCacheManager;
import com.example.layoutsample.Util.BitmapLoader;

public class SampleCardViewHolder extends RecyclerView.ViewHolder {
    private final ImageView mBackground;
    private final TextView mName;
    private final Button mStar;
    private final ViewGroup mLayout;

    private boolean mIsStarFill;

    public SampleCardViewHolder(@NonNull View card) {
        super(card);
        mLayout = card.findViewById(R.id.sample_card_layout);
        mBackground = mLayout.findViewById(R.id.sample_card_background);
        mName = mLayout.findViewById(R.id.sample_card_name);
        mStar = mLayout.findViewById(R.id.sample_card_star);

        mIsStarFill = false;

        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarFill(!mIsStarFill);
            }
        });
    }
    public void setImage(int imageId) {
        Resources resources = mLayout.getResources();
        Drawable drawable = resources.getDrawable(imageId, null);

        mBackground.setImageDrawable(drawable);
    }
    public void setName(String name) {
        mName.setText(name);
    }
    public void setStarFill(boolean isFill) {
        Resources resources = mLayout.getResources();
        Drawable drawable = null;

        if(isFill) {
            drawable = resources.getDrawable(R.drawable.ic_star_white_24dp, null);
        } else {
            drawable = resources.getDrawable(R.drawable.ic_star_border_white_24dp, null);
        }
        mIsStarFill = isFill;
        mStar.setBackground(drawable);
    }


}
