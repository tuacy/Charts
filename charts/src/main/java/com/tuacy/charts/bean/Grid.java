package com.tuacy.charts.bean;


import android.graphics.Color;

public class Grid {

	/**
	 * 虚线的宽度
	 */
	private int mLineWidth;
	/**
	 * 虚线的颜色
	 */
	private int mLineColor;
	/**
	 * 每一段实线的长度
	 */
	private int mLineFullLength;
	/**
	 * 每一段虚线的长度
	 */
	private int mLineDottedLength;

	public Grid() {
		mLineWidth = 1;
		mLineColor = Color.DKGRAY;
		mLineFullLength = 10;
		mLineDottedLength = 10;
	}

	public int getLineWidth() {
		return mLineWidth;
	}

	public void setLineWidth(int lineWidth) {
		mLineWidth = lineWidth;
	}

	public int getLineColor() {
		return mLineColor;
	}

	public void setLineColor(int lineColor) {
		mLineColor = lineColor;
	}

	public int getLineFullLength() {
		return mLineFullLength;
	}

	public void setLineFullLength(int lineFullLength) {
		mLineFullLength = lineFullLength;
	}

	public int getLineDottedLength() {
		return mLineDottedLength;
	}

	public void setLineDottedLength(int lineDottedLength) {
		mLineDottedLength = lineDottedLength;
	}
}
