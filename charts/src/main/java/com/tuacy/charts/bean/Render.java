package com.tuacy.charts.bean;

import android.graphics.Color;

import com.tuacy.charts.IRenderFormat;

import java.util.List;

/**
 * 要绘制的具体内容信息
 */

public class Render {

	//颜色
	private int           mColor;
	private float mWidth;
	//每个点的数据
	private List<Number>  mData;
	private IRenderFormat mFormat;

	public Render() {
		mColor = Color.BLACK;
		mWidth = 2;
	}

	public int getColor() {
		return mColor;
	}

	public void setColor(int color) {
		mColor = color;
	}

	public List<Number> getData() {
		return mData;
	}

	public void setData(List<Number> data) {
		mData = data;
	}

	public IRenderFormat getFormat() {
		return mFormat;
	}

	public void setFormat(IRenderFormat format) {
		mFormat = format;
	}

	public float getWidth() {
		return mWidth;
	}

	public void setWidth(float width) {
		mWidth = width;
	}
}
