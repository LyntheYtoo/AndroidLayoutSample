package com.example.layoutsample.Util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapLoader {
    private BitmapCacheManager mCacheManager;

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
            task.execute();
        }
    }
    static class BitmapWorkerTask extends AsyncTask<Void, Void, Bitmap> {
        private final Resources mRes;
        private final int mResId;
        private final ImageView mImageView;

        BitmapWorkerTask(Resources res, int resId, ImageView imageView) {
            mRes = res;
            mResId = resId;
            mImageView = imageView;
        }
        @Override
        protected Bitmap doInBackground(Void... voids) {
            return BitmapFactory.decodeResource(mRes, mResId);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }

}
