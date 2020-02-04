package com.example.layoutsample.Util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 이미지리소스를 비동기적으로 이미지뷰에 적용하기위한 곳
 */
public class ImageResourceLoader {
    // 이미지리소스를 비트맵으로 변환한 것들을 캐싱하는 곳
    private BitmapCacheManager mCacheManager;
    // 이미지의 스케일을 얼마나 할 것인지 결정
    private float mScale = 0f;

    /**
     * 이미지 리소스 로더 생성자
     * 캐시매니저를 필요로 한다
     * @param cacheManager 비트맵 캐싱할 BitmapCacheManager
     */
    public ImageResourceLoader(BitmapCacheManager cacheManager) {
        mCacheManager = cacheManager;
    }

    /**
     * 이미지 리소스에 해당하는 비트맵을 캐시에서 찾고
     * 없을 시 비동기적으로 해당 이미지 리소스를 로드, 비트맵으로 변환하여 이미지뷰에 적용
     * @param res 현재 Context의 Resources 객체
     * @param resId 불러올 리소스 id
     * @param imageView 이미지가 적용될 이미지뷰
     */
    public void loadImageResource(Resources res, int resId, ImageView imageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = mCacheManager.getBitmapFromMemoryCache(imageKey);
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            WorkerTask task = new WorkerTask(res, resId, imageView);
            // 스케일 값을 파라미터로 넣어준다 0일때 null
            Float s = mScale == 0f? null: mScale;
            task.execute(s);
        }
    }

    /**
     * 스케일 등록
     * @param scale 스케일 수치 float
     */
    public void setScale(float scale) {
        mScale = scale;
    }

    /**
     * 비동기 태스크
     * 여기에서 execute(Float...) 를 호출시
     * 이미지 리소스 ==> 비트맵 변환 ==> 캐싱 ==> (메인 스레드) 이미지뷰에 비트맵 등록
     * 순으로 작업된다
     */
    class WorkerTask extends AsyncTask<Float, Void, Bitmap> {
        private final Resources mRes;
        private final ImageView mImageView;
        private final int mResId;

        /**
         * 생성자에서 멤버변수를 초기화
         * @param res Resources 객체
         * @param resId 대상 이미지뷰
         * @param imageView 적용할 리소스id
         */
        WorkerTask(Resources res, int resId, ImageView imageView) {
            mRes = res;
            mImageView = imageView;
            mResId = resId;
        }

        /**
         * 비동기(백그라운드) 에서 실행할 작업
         * 이미지 리소스 ==> 비트맵 변환 ==> 캐싱
         * @param params 비트맵 변환시 스케일링 수치
         * @return 변환된 비트맵 객체
         */
        @Override
        protected Bitmap doInBackground(Float... params) {

            // 이미지 리소스 ==> 비트맵
            Bitmap bitmap = BitmapFactory.decodeResource(mRes, mResId);
            // 스케일 수치가 정해져 있을 시 비트맵 스케일링
            if(params[0] != null) {
                bitmap = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*params[0]), (int)(bitmap.getHeight()*params[0]), false);
            }
            // 캐싱
            mCacheManager.addBitmapToMemoryCache(String.valueOf(mResId), bitmap);

            return bitmap;
        }

        /**
         * (메인스레드에서 작업)
         *  이미지뷰에 변환된 비트맵 등록
         * @param bitmap 변환된 비트맵
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }

}
