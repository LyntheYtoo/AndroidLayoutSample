package com.example.layoutsample.Util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapLoader {
    private BitmapCacheManager mCacheManager;
    private float mScale = 0f;

    public BitmapLoader(BitmapCacheManager cacheManager) {
        mCacheManager = cacheManager;
    }

    public void loadBitmap(Resources res, int resId, ImageView imageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = mCacheManager.getBitmapFromMemoryCache(imageKey);
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            BitmapWorkerTask task = new BitmapWorkerTask(res, resId, imageView);
            Float s = mScale == 0f? null: mScale;
            task.execute(s);
        }
    }
    public void setScale(float scale) {
        mScale = scale;
    }

    static class BitmapWorkerTask extends AsyncTask<Float, Void, Bitmap> {
        private final Resources mRes;
        private final ImageView mImageView;

        private int mResId;

        BitmapWorkerTask(Resources res, int resId, ImageView imageView) {
            mRes = res;
            mImageView = imageView;
            mResId = resId;
        }

        @Override
        protected Bitmap doInBackground(Float... params) {

            if(params[0] == null) {
                return BitmapFactory.decodeResource(mRes, mResId);
            }
            Bitmap bitmap =  BitmapFactory.decodeResource(mRes, mResId);
            //scale size
            return Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*params[0]), (int)(bitmap.getHeight()*params[0]), false);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }

}
