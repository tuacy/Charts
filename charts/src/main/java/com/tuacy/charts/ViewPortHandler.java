package com.tuacy.charts;


import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;

public class ViewPortHandler {

	private Context mContext;

	/**
	 * View的区域
	 */
	private RectF mContentRect = new RectF();
	/**
	 * view的宽度
	 */
	private float mChartWidth  = 0f;
	/**
	 * view的高度
	 */
	private float mChartHeight = 0f;

	public ViewPortHandler(Context context) {
		mContext = context;
	}

	/**
	 * Sets the width and height of the chart.
	 */
	public void setChartDimens(float width, float height) {
		float offsetLeft = this.offsetLeft();
		float offsetTop = this.offsetTop();
		float offsetRight = this.offsetRight();
		float offsetBottom = this.offsetBottom();
		mChartHeight = height;
		mChartWidth = width;
		restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
	}

	public boolean hasChartDimens() {
		if (mChartHeight > 0 && mChartWidth > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void restrainViewPort(float offsetLeft, float offsetTop, float offsetRight, float offsetBottom) {
		mContentRect.set(offsetLeft, offsetTop, mChartWidth - offsetRight, mChartHeight - offsetBottom);
	}

	public float offsetLeft() {
		return mContentRect.left;
	}

	public float offsetRight() {
		return mChartWidth - mContentRect.right;
	}

	public float offsetTop() {
		return mContentRect.top;
	}

	public float offsetBottom() {
		return mChartHeight - mContentRect.bottom;
	}

	public float contentTop() {
		return mContentRect.top;
	}

	public float contentLeft() {
		return mContentRect.left;
	}

	public float contentRight() {
		return mContentRect.right;
	}

	public float contentBottom() {
		return mContentRect.bottom;
	}

	public float contentWidth() {
		return mContentRect.width();
	}

	public float contentHeight() {
		return mContentRect.height();
	}

	public RectF getContentRect() {
		return mContentRect;
	}

	public PointF getContentCenter() {
		return new PointF(mContentRect.centerX(), mContentRect.centerY());
	}

	public float getChartHeight() {
		return mChartHeight;
	}

	public float getChartWidth() {
		return mChartWidth;
	}

	public boolean isInBoundsX(float x) {
		if (isInBoundsLeft(x) && isInBoundsRight(x)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInBoundsY(float y) {
		if (isInBoundsTop(y) && isInBoundsBottom(y)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInBounds(float x, float y) {
		if (isInBoundsX(x) && isInBoundsY(y)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInBoundsLeft(float x) {
		return mContentRect.left <= x;
	}

	public boolean isInBoundsRight(float x) {
		x = (float) ((int) (x * 100.f)) / 100.f;
		return mContentRect.right >= x;
	}

	public boolean isInBoundsTop(float y) {
		return mContentRect.top <= y;
	}

	public boolean isInBoundsBottom(float y) {
		y = (float) ((int) (y * 100.f)) / 100.f;
		return mContentRect.bottom >= y;
	}


}
