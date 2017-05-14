package com.tuacy.charts.bean;

public class RenderPoint {

	private int mXIndex;
	private Number mValue;
	private float mPointerX;
	private float mPointerY;

	public RenderPoint(int XIndex, Number value, float pointerX, float pointerY) {
		mXIndex = XIndex;
		mValue = value;
		mPointerX = pointerX;
		mPointerY = pointerY;
	}

	public int getXIndex() {
		return mXIndex;
	}

	public void setXIndex(int XIndex) {
		mXIndex = XIndex;
	}

	public Number getValue() {
		return mValue;
	}

	public void setValue(Number value) {
		mValue = value;
	}

	public float getPointerX() {
		return mPointerX;
	}

	public void setPointerX(float pointerX) {
		mPointerX = pointerX;
	}

	public float getPointerY() {
		return mPointerY;
	}

	public void setPointerY(float pointerY) {
		mPointerY = pointerY;
	}
}
