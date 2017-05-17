package com.tuacy.charts.components;

import android.content.Context;

import com.tuacy.charts.formatter.DefaultXAxisValueFormatter;
import com.tuacy.charts.formatter.XAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class XAxis extends AxisBase {

	/**
	 * x轴上所有数据的集合
	 */
	protected List<String>        mValues                 = new ArrayList<>();
	/**
	 * x轴上标签的宽度
	 */
	public    int                 mLabelWidth             = 1;
	/**
	 * x轴上标签的高度
	 */
	public    int                 mLabelHeight            = 1;
	/**
	 * x轴标签，左边需要空出来的宽度
	 */
	private   int                 mSpaceBetweenLabels     = 4;
	/**
	 * x轴上标签每次隔多少个画标签
	 */
	public    int                 mAxisLabelModulus       = 1;
	/**
	 * 是否自定义了x轴上标签隔多少个
	 */
	private   boolean             mIsAxisModulusCustom    = false;
	/**
	 * x轴上显示的文字格式化
	 */
	protected XAxisValueFormatter mXAxisValueFormatter    = new DefaultXAxisValueFormatter();

	public XAxis(Context context) {
		super(context);
		mYOffset = 4.f; // -3
	}

	public void setSpaceBetweenLabels(int spaceCharacters) {
		mSpaceBetweenLabels = spaceCharacters;
	}

	public void setLabelsToSkip(int count) {

		if (count < 0) {
			count = 0;
		}

		mIsAxisModulusCustom = true;
		mAxisLabelModulus = count + 1;
	}

	public void resetLabelsToSkip() {
		mIsAxisModulusCustom = false;
	}

	public boolean isAxisModulusCustom() {
		return mIsAxisModulusCustom;
	}

	public int getSpaceBetweenLabels() {
		return mSpaceBetweenLabels;
	}

	public void setValues(List<String> values) {
		mValues = values;
	}

	public List<String> getValues() {
		return mValues;
	}


	public void setValueFormatter(XAxisValueFormatter formatter) {
		if (formatter == null) {
			mXAxisValueFormatter = new DefaultXAxisValueFormatter();
		} else {
			mXAxisValueFormatter = formatter;
		}
	}

	public XAxisValueFormatter getValueFormatter() {
		return mXAxisValueFormatter;
	}

	@Override
	public String getLongestLabel() {

		String longest = "";

		for (int i = 0; i < mValues.size(); i++) {
			String text = mValues.get(i);

			if (longest.length() < text.length()) {
				longest = text;
			}
		}

		return longest;
	}

}
