package com.geekytheory.miguelcatalan.rssgeekytheory;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache extends LruCache implements ImageCache {
	
	public BitmapCache(int maxSize) {
		super(maxSize);
	}

	@Override
	public Bitmap getBitmap(String url) {
		return (Bitmap) get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}
}
