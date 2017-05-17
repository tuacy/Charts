package com.tuacy.charts.data;


import android.graphics.Color;

import com.tuacy.charts.formatter.ValueFormatter;

import java.util.List;

public class LineDataSet {

	/**
	 * 曲线上节点的颜色
	 */
	private   int            mCircleColor        = Color.WHITE;
	/**
	 * 曲线上节点的半径
	 */
	private   float          mCircleRadius       = 8f;
	/**
	 * 是否绘制曲线上的节点
	 */
	private   boolean        mDrawCircles        = true;
	/**
	 * 曲线上每个点的数据
	 */
	private   List<Entry>    mData               = null;
	/**
	 * 曲线颜色
	 */
	private   int            mLineColor          = Color.BLACK;
	/**
	 * 曲线线宽
	 */
	private   float          mLineWidth          = 2.5f;
	/**
	 * 是否填充
	 */
	private   boolean        mDrawFilled         = false;
	/**
	 * 高亮的线宽
	 */
	private   float          mHighlightLineWidth = 0.5f;
	/**
	 * 高亮线的颜色
	 */
	private   int            mHighLightColor     = Color.rgb(255, 187, 115);
	/**
	 * 曲线数据的format
	 */
	private   ValueFormatter mValueFormatter     = null;

	public LineDataSet(List<Entry> data) {
		mData = data;
	}

	public int getCircleColor() {
		return mCircleColor;
	}

	public void setCircleColor(int circleColor) {
		mCircleColor = circleColor;
	}

	public float getCircleRadius() {
		return mCircleRadius;
	}

	public void setCircleRadius(float circleRadius) {
		mCircleRadius = circleRadius;
	}

	public boolean isDrawCircles() {
		return mDrawCircles;
	}

	public void setDrawCircles(boolean drawCircles) {
		mDrawCircles = drawCircles;
	}

	public List<Entry> getData() {
		return mData;
	}

	public void setData(List<Entry> data) {
		mData = data;
	}

	public int getLineColor() {
		return mLineColor;
	}

	public void setLineColor(int lineColor) {
		mLineColor = lineColor;
	}

	public float getLineWidth() {
		return mLineWidth;
	}

	public void setLineWidth(float lineWidth) {
		mLineWidth = lineWidth;
	}

	public boolean isDrawFilled() {
		return mDrawFilled;
	}

	public void setDrawFilled(boolean drawFilled) {
		mDrawFilled = drawFilled;
	}

	public float getHighlightLineWidth() {
		return mHighlightLineWidth;
	}

	public void setHighlightLineWidth(float highlightLineWidth) {
		mHighlightLineWidth = highlightLineWidth;
	}

	public int getHighLightColor() {
		return mHighLightColor;
	}

	public void setHighLightColor(int highLightColor) {
		mHighLightColor = highLightColor;
	}

	public float getYMax() {
		if (mData == null || mData.isEmpty()) {
			return 0;
		}
		float max = -Integer.MAX_VALUE;
		for (Entry entry:mData) {
			if (entry != null && entry.getVal() != null) {
				max = Math.max(max, entry.getVal().floatValue());
			}
		}
		return max;
	}


	public float getYMin() {
		if (mData == null || mData.isEmpty()) {
			return 0;
		}
		float min = Integer.MAX_VALUE;
		for (Entry entry:mData) {
			if (entry != null && entry.getVal() != null) {
				min = Math.min(min, entry.getVal().floatValue());
			}
		}
		return min;
	}


	public ValueFormatter getValueFormatter() {
		return mValueFormatter;
	}

	public void setValueFormatter(ValueFormatter valueFormatter) {
		mValueFormatter = valueFormatter;
	}

	public int getEntryCount() {
		return mData.size();
	}
}
