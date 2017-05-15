package com.tuacy.charts.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * x轴和y轴的基类
 */
public abstract class AxisBase extends ComponentBase {

	/**
	 * 表格线的颜色
	 */
	private   int             mGridColor               = Color.GRAY;
	/**
	 * 表格线的宽度
	 */
	private   float           mGridLineWidth           = 1f;
	/**
	 * 轴线的颜色
	 */
	private   int             mAxisLineColor           = Color.GRAY;
	/**
	 * 轴线的宽度
	 */
	private   float           mAxisLineWidth           = 1f;
	/**
	 * 是否绘制表格
	 */
	protected boolean         mDrawGridLines           = true;
	/**
	 * 是否绘制轴线
	 */
	protected boolean         mDrawAxisLine            = true;
	/**
	 * 是否绘制轴上的标签(轴上的文字哦)
	 */
	protected boolean         mDrawLabels              = true;
	/**
	 * 表格线的描述(虚线哦)
	 */
	private   DashPathEffect  mGridDashPathEffect      = null;
	/**
	 * 轴上limit线列表
	 */
	protected List<LimitLine> mLimitLines              = null;
	/**
	 * limit先是否在数据的下方
	 */
	protected boolean         mDrawLimitLineBehindData = false;
	/**
	 * 是否自定义了最小值
	 */
	protected boolean         mCustomAxisMin           = false;
	/**
	 * 是否自定义了最大值
	 */
	protected boolean         mCustomAxisMax           = false;
	/**
	 * 轴上的最大值
	 */
	public    float           mAxisMaximum             = 0f;
	/**
	 * 轴上的最小值
	 */
	public    float           mAxisMinimum             = 0f;
	/**
	 * 轴上每一块的大小
	 */
	public    float           mAxisRange               = 0f;


	public AxisBase() {
		this.mTextSize = 10f;
		this.mXOffset = 5f;
		this.mYOffset = 5f;
		this.mLimitLines = new ArrayList<>();
	}

	public void setDrawGridLines(boolean enabled) {
		mDrawGridLines = enabled;
	}

	public boolean isDrawGridLinesEnabled() {
		return mDrawGridLines;
	}

	public void setDrawAxisLine(boolean enabled) {
		mDrawAxisLine = enabled;
	}

	public boolean isDrawAxisLineEnabled() {
		return mDrawAxisLine;
	}

	public void setGridColor(int color) {
		mGridColor = color;
	}

	public int getGridColor() {
		return mGridColor;
	}

	public void setAxisLineWidth(float width) {
		mAxisLineWidth = width;
	}

	public float getAxisLineWidth() {
		return mAxisLineWidth;
	}

	public void setGridLineWidth(float width) {
		mGridLineWidth = width;
	}

	public float getGridLineWidth() {
		return mGridLineWidth;
	}

	public void setAxisLineColor(int color) {
		mAxisLineColor = color;
	}

	public int getAxisLineColor() {
		return mAxisLineColor;
	}

	public void setDrawLabels(boolean enabled) {
		mDrawLabels = enabled;
	}

	public boolean isDrawLabelsEnabled() {
		return mDrawLabels;
	}

	public void addLimitLine(LimitLine l) {
		mLimitLines.add(l);

		if (mLimitLines.size() > 6) {
			Log.e("Chart", "Warning! You have more than 6 LimitLines on your axis, do you really want " + "that?");
		}
	}

	public void removeLimitLine(LimitLine l) {
		mLimitLines.remove(l);
	}

	public void removeAllLimitLines() {
		mLimitLines.clear();
	}

	public List<LimitLine> getLimitLines() {
		return mLimitLines;
	}

	public void setDrawLimitLinesBehindData(boolean enabled) {
		mDrawLimitLineBehindData = enabled;
	}

	public boolean isDrawLimitLinesBehindDataEnabled() {
		return mDrawLimitLineBehindData;
	}

	public abstract String getLongestLabel();

	public void enableGridDashedLine(float lineLength, float spaceLength, float phase) {
		mGridDashPathEffect = new DashPathEffect(new float[]{lineLength,
															 spaceLength}, phase);
	}

	public void disableGridDashedLine() {
		mGridDashPathEffect = null;
	}

	public boolean isGridDashedLineEnabled() {
		return mGridDashPathEffect != null;
	}

	public DashPathEffect getGridDashPathEffect() {
		return mGridDashPathEffect;
	}

	public float getAxisMaximum() {
		return mAxisMaximum;
	}

	public float getAxisMinimum() {
		return mAxisMinimum;
	}

	public void resetAxisMaxValue() {
		mCustomAxisMax = false;
	}

	public boolean isAxisMaxCustom() {
		return mCustomAxisMax;
	}

	public void resetAxisMinValue() {
		mCustomAxisMin = false;
	}

	public boolean isAxisMinCustom() {
		return mCustomAxisMin;
	}

	public void setAxisMinValue(float min) {
		mCustomAxisMin = true;
		mAxisMinimum = min;
	}

	public void setAxisMaxValue(float max) {
		mCustomAxisMax = true;
		mAxisMaximum = max;
	}
}
