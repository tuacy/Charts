package com.tuacy.charts.data;

public class Entry {

	private Number mVal    = 0f;
	private int    mXIndex = 0;

	public Entry(Number val, int xIndex) {
		mVal = val;
		mXIndex = xIndex;
	}

	public int getXIndex() {
		return mXIndex;
	}

	public void setXIndex(int x) {
		this.mXIndex = x;
	}

	public Number getVal() {
		return mVal;
	}

	public void setVal(float val) {
		this.mVal = val;
	}

	@Override
	public String toString() {
		return "Entry, xIndex: " + mXIndex + " val (sum): " + getVal();
	}

}
