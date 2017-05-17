package com.tuacy.charts.renderer;

import android.content.Context;

import com.tuacy.charts.ViewPortHandler;

public abstract class Renderer {

	protected Context mContext;

	/**
	 * the component that handles the drawing area of the chart and it's offsets
	 */
	protected ViewPortHandler mViewPortHandler;

	/**
	 * the minimum value on the x-axis that should be plotted
	 */
	protected int mMinX = 0;

	/**
	 * the maximum value on the x-axis that should be plotted
	 */
	protected int mMaxX = 0;

	public Renderer(Context context, ViewPortHandler viewPortHandler) {
		mContext = context;
		mViewPortHandler = viewPortHandler;
	}
}
