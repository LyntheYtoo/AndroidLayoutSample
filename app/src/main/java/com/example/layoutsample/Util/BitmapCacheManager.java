package com.example.layoutsample.Util;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

public class BitmapCacheManager {
    public static final int MAX_CACHE = (int)(Runtime.getRuntime().maxMemory() / 1024);
    public static final int CACHE_SIZE = MAX_CACHE / 8;

    private LruCache<String, Bitmap> mMemoryCache;

    public BitmapCacheManager() {
        mMemoryCache = new LruCache<String, Bitmap>(CACHE_SIZE) {
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }
}
