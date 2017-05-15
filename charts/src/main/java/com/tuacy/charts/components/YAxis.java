package com.tuacy.charts.components;

import android.graphics.Color;
import android.graphics.Paint;

import com.tuacy.charts.formatter.DefaultValueFormatter;
import com.tuacy.charts.formatter.DefaultYAxisValueFormatter;
import com.tuacy.charts.formatter.YAxisValueFormatter;
import com.tuacy.charts.utils.Utils;


public class YAxis extends AxisBase {

	protected YAxisValueFormatter mYAxisValueFormatter;

	public float[] mEntries = new float[]{};

	public int mEntryCount;

	public int mDecimals;

	private int mLabelCount = 6;

	private boolean mDrawTopYLabelEntry = true;

	protected boolean mShowOnlyMinMax = false;

	protected boolean mInverted = false;

	protected boolean mForceLabels = false;

	protected boolean mDrawZeroLine = false;

	protected int mZeroLineColor = Color.GRAY;

	protected float mZeroLineWidth = 1f;

	protected float mSpacePercentTop = 10f;

	protected float mSpacePercentBottom = 10f;

	private YAxisLabelPosition mPosition = YAxisLabelPosition.OUTSIDE_CHART;

	public enum YAxisLabelPosition {
		OUTSIDE_CHART,
		INSIDE_CHART
	}

	private AxisDependency mAxisDependency;

	protected float mMinWidth = 0.f;

	protected float mMaxWidth = Float.POSITIVE_INFINITY;

	protected boolean mGranularityEnabled = false;

	protected float mGranularity = 1.0f;

	public enum AxisDependency {
		LEFT,
		RIGHT
	}

	public YAxis() {
		super();

		// default left
		this.mAxisDependency = AxisDependency.LEFT;
		this.mYOffset = 0f;
	}

	public YAxis(AxisDependency position) {
		super();
		this.mAxisDependency = position;
		this.mYOffset = 0f;
	}

	public AxisDependency getAxisDependency() {
		return mAxisDependency;
	}

	public float getMinWidth() {
		return mMinWidth;
	}

	public void setMinWidth(float minWidth) {
		mMinWidth = minWidth;
	}

	public float getMaxWidth() {
		return mMaxWidth;
	}

	public void setMaxWidth(float maxWidth) {
		mMaxWidth = maxWidth;
	}

	public boolean isGranularityEnabled() {
		return mGranularityEnabled;
	}

	public void setGranularityEnabled(boolean enabled) {
		mGranularityEnabled = true;
	}

	public float getGranularity() {
		return mGranularity;
	}

	public void setGranularity(float granularity) {
		mGranularity = granularity;
		// set this to true if it was disabled, as it makes no sense to call this method with granularity disabled
		mGranularityEnabled = true;
	}

	public YAxisLabelPosition getLabelPosition() {
		return mPosition;
	}

	public void setPosition(YAxisLabelPosition pos) {
		mPosition = pos;
	}

	public boolean isDrawTopYLabelEntryEnabled() {
		return mDrawTopYLabelEntry;
	}

	public void setDrawTopYLabelEntry(boolean enabled) {
		mDrawTopYLabelEntry = enabled;
	}

	public void setLabelCount(int count, boolean force) {

		if (count > 25) {
			count = 25;
		}
		if (count < 2) {
			count = 2;
		}

		mLabelCount = count;
		mForceLabels = force;
	}

	public int getLabelCount() {
		return mLabelCount;
	}

	public boolean isForceLabelsEnabled() {
		return mForceLabels;
	}

	public void setShowOnlyMinMax(boolean enabled) {
		mShowOnlyMinMax = enabled;
	}

	public boolean isShowOnlyMinMaxEnabled() {
		return mShowOnlyMinMax;
	}

	public void setInverted(boolean enabled) {
		mInverted = enabled;
	}

	public boolean isInverted() {
		return mInverted;
	}

	@Deprecated
	public void setStartAtZero(boolean startAtZero) {
		if (startAtZero) {
			setAxisMinValue(0f);
		} else {
			resetAxisMinValue();
		}
	}

	public void setSpaceTop(float percent) {
		mSpacePercentTop = percent;
	}

	public float getSpaceTop() {
		return mSpacePercentTop;
	}

	public void setSpaceBottom(float percent) {
		mSpacePercentBottom = percent;
	}

	public float getSpaceBottom() {
		return mSpacePercentBottom;
	}

	public boolean isDrawZeroLineEnabled() {
		return mDrawZeroLine;
	}

	public void setDrawZeroLine(boolean mDrawZeroLine) {
		this.mDrawZeroLine = mDrawZeroLine;
	}

	public int getZeroLineColor() {
		return mZeroLineColor;
	}

	public void setZeroLineColor(int color) {
		mZeroLineColor = color;
	}

	public float getZeroLineWidth() {
		return mZeroLineWidth;
	}

	public void setZeroLineWidth(float width) {
		this.mZeroLineWidth = width;
	}

	public float getRequiredWidthSpace(Paint p) {

		p.setTextSize(mTextSize);

		String label = getLongestLabel();
		float width = (float) Utils.calcTextWidth(p, label) + getXOffset() * 2f;

		float minWidth = getMinWidth();
		float maxWidth = getMaxWidth();

		width = Math.max(minWidth, Math.min(width, maxWidth > 0.0 ? maxWidth : width));

		return width;
	}

	public float getRequiredHeightSpace(Paint p) {

		p.setTextSize(mTextSize);

		String label = getLongestLabel();
		return (float) Utils.calcTextHeight(p, label) + getYOffset() * 2f;
	}

	@Override
	public String getLongestLabel() {

		String longest = "";

		for (int i = 0; i < mEntries.length; i++) {
			String text = getFormattedLabel(i);

			if (longest.length() < text.length()) {
				longest = text;
			}
		}

		return longest;
	}

	public String getFormattedLabel(int index) {

		if (index < 0 || index >= mEntries.length) {
			return "";
		} else {
			return getValueFormatter().getFormattedValue(mEntries[index], this);
		}
	}

	public void setValueFormatter(YAxisValueFormatter f) {

		if (f == null) {
			mYAxisValueFormatter = new DefaultYAxisValueFormatter(mDecimals);
		} else {
			mYAxisValueFormatter = f;
		}
	}

	public YAxisValueFormatter getValueFormatter() {

		if (mYAxisValueFormatter == null) {
			mYAxisValueFormatter = new DefaultYAxisValueFormatter(mDecimals);
		}

		return mYAxisValueFormatter;
	}

	public boolean needsDefaultFormatter() {
		if (mYAxisValueFormatter == null) {
			return true;
		}
		if (mYAxisValueFormatter instanceof DefaultValueFormatter) {
			return true;
		}

		return false;
	}

	public boolean needsOffset() {
		if (isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxisLabelPosition.OUTSIDE_CHART) {
			return true;
		} else {
			return false;
		}
	}

	public void calculate(float dataMin, float dataMax) {

		// if custom, use value as is, else use data value
		float min = mCustomAxisMin ? mAxisMinimum : dataMin;
		float max = mCustomAxisMax ? mAxisMaximum : dataMax;

		// temporary range (before calculations)
		float range = Math.abs(max - min);

		// in case all values are equal
		if (range == 0f) {
			max = max + 1f;
			min = min - 1f;
		}

		// bottom-space only effects non-custom min
		if (!mCustomAxisMin) {

			float bottomSpace = range / 100f * getSpaceBottom();
			this.mAxisMinimum = (min - bottomSpace);
		}

		// top-space only effects non-custom max
		if (!mCustomAxisMax) {

			float topSpace = range / 100f * getSpaceTop();
			this.mAxisMaximum = (max + topSpace);
		}

		// calc actual range
		this.mAxisRange = Math.abs(this.mAxisMaximum - this.mAxisMinimum);

	}

}
