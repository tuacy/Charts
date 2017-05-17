package com.tuacy.charts.components;

import android.content.Context;
import android.graphics.Paint;

import com.tuacy.charts.formatter.DefaultValueFormatter;
import com.tuacy.charts.formatter.DefaultYAxisValueFormatter;
import com.tuacy.charts.formatter.YAxisValueFormatter;
import com.tuacy.charts.utils.Utils;


public class YAxis extends AxisBase {

	/**
	 * y轴上要显示字符的格式化
	 */
	protected YAxisValueFormatter mYAxisValueFormatter = null;
	/**
	 * y轴上要显示的label的集合
	 */
	public    float[]             mEntries             = new float[]{};
	/**
	 * entry的个数
	 */
	public    int                 mEntryCount          = 0;
	/**
	 * y轴上要显示的label保留几位小数
	 */
	public    int                 mDecimals            = 0;
	/**
	 * y轴上要显示label的个数
	 */
	private   int                 mLabelCount          = 6;
	/**
	 * y轴上要显示的最大值超出实际最大值多少
	 */
	protected float               mSpacePercentTop     = 10f;
	/**
	 * y轴上要显示的最小值小于实际最小值多少
	 */
	protected float               mSpacePercentBottom  = 10f;
	/**
	 * y轴上label文字的最小宽度
	 */
	protected float               mMinWidth            = 0.f;
	/**
	 * y轴上label文字的最大宽度
	 */
	protected float               mMaxWidth            = Float.POSITIVE_INFINITY;

	public YAxis(Context context) {
		super(context);
		this.mYOffset = 0f;
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


	public void setLabelCount(int count) {

		if (count > 25) {
			count = 25;
		}
		if (count < 2) {
			count = 2;
		}

		mLabelCount = count;
	}

	public int getLabelCount() {
		return mLabelCount;
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

	public void calculate(float dataMin, float dataMax) {

		float min = mCustomAxisMin ? mAxisMinimum : dataMin;
		float max = mCustomAxisMax ? mAxisMaximum : dataMax;

		float range = Math.abs(max - min);

		if (range == 0f) {
			max = max + 1f;
			min = min - 1f;
		}

		if (!mCustomAxisMin) {
			float bottomSpace = range / 100f * getSpaceBottom();
			this.mAxisMinimum = (min - bottomSpace);
		}

		if (!mCustomAxisMax) {
			float topSpace = range / 100f * getSpaceTop();
			this.mAxisMaximum = (max + topSpace);
		}

		this.mAxisRange = Math.abs(this.mAxisMaximum - this.mAxisMinimum);

	}

}
