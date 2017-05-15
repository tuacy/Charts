package com.tuacy.charts.components;


import android.graphics.Color;
import android.graphics.Typeface;

public abstract class ComponentBase {

	/**
	 * 是否显示
	 */
	protected boolean  mEnabled   = true;
	/**
	 * x方向偏移
	 */
	protected float    mXOffset   = 5f;
	/**
	 * y方向偏移
	 */
	protected float    mYOffset   = 5f;
	/**
	 * 字体
	 */
	protected Typeface mTypeface  = null;
	/**
	 * 位置大小
	 */
	protected float    mTextSize  = 10f;
	/**
	 * 文字颜色
	 */
	protected int      mTextColor = Color.BLACK;

	public ComponentBase() {

	}

	public float getXOffset() {
		return mXOffset;
	}

	public void setXOffset(float xOffset) {
		mXOffset = xOffset;
	}

	public float getYOffset() {
		return mYOffset;
	}

	public void setYOffset(float yOffset) {
		mYOffset = yOffset;
	}

	public Typeface getTypeface() {
		return mTypeface;
	}

	public void setTypeface(Typeface tf) {
		mTypeface = tf;
	}

	/**
	 * sets the size of the label text in pixels min = 6f, max = 24f, default
	 * 10f
	 */
	public void setTextSize(float size) {

		if (size > 24f) {
			size = 24f;
		}
		if (size < 6f) {
			size = 6f;
		}

		mTextSize = size;
	}

	public float getTextSize() {
		return mTextSize;
	}

	public void setTextColor(int color) {
		mTextColor = color;
	}

	public int getTextColor() {
		return mTextColor;
	}

	public void setEnabled(boolean enabled) {
		mEnabled = enabled;
	}

	public boolean isEnabled() {
		return mEnabled;
	}
}
