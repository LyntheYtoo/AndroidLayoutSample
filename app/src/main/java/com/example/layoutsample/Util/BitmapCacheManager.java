package com.example.layoutsample.Util;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

/**
 * 비트맵 캐시를 관리하는 곳
 */
public class BitmapCacheManager {
    // 현재 시스템에서 사용가능 최대 메모리 크기 (kb 단위)
    private static final int MAX_CACHE = (int)(Runtime.getRuntime().maxMemory() / 1024);
    // 캐시에서 사용할 메모리 크기
    private static final int CACHE_SIZE = MAX_CACHE / 8;

    private LruCache<String, Bitmap> mMemoryCache;

    /**
     * 생성자에서 비트맵 캐쉬를 초기화한다
     * 오버플로우를 방지하기 위해 kilobyte 단위로 계산한다
     */
    public BitmapCacheManager() {
        mMemoryCache = new LruCache<String, Bitmap>(CACHE_SIZE) {

            // sizeOf() 를 오버라이딩 하여 각 비트맵 요소를 kilobyte 단위로 계산하게 만듬
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    /**
     * Key, Value(비트맵) 으로 캐싱
     * @param key 키값
     * @param bitmap 밸류값
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 키값으로 캐싱된 데이터를 가져오기
     * @param key 키값
     * @return 키값에 대응하는 데이터(비트맵)
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }
}
