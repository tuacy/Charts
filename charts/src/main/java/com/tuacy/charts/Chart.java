package com.tuacy.charts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Chart<T> extends View{

	public static final String LOG_TAG = "Chart";

	protected T mData = null;

	public Chart(Context context) {
		this(context, null);
	}

	public Chart(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Chart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		chartInit();
	}

	protected void chartInit() {

	}
}
