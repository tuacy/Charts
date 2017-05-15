package com.tuacy.charts.components;

import com.tuacy.charts.formatter.DefaultXAxisValueFormatter;
import com.tuacy.charts.formatter.XAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class XAxis extends AxisBase {

	/**
	 * x轴上所有数据的集合
	 */
	protected List<String>        mValues                 = new ArrayList<>();
	public    int                 mLabelWidth             = 1;
	public    int                 mLabelHeight            = 1;
	public    int                 mLabelRotatedWidth      = 1;
	public    int                 mLabelRotatedHeight     = 1;
	protected float               mLabelRotationAngle     = 0f;
	private   int                 mSpaceBetweenLabels     = 4;
	public    int                 mAxisLabelModulus       = 1;
	private   boolean             mIsAxisModulusCustom    = false;
	private   boolean             mAvoidFirstLastClipping = false;
	protected XAxisValueFormatter mXAxisValueFormatter    = new DefaultXAxisValueFormatter();

	public XAxis() {
		super();

		mYOffset = 4.f; // -3
	}


	public float getLabelRotationAngle() {
		return mLabelRotationAngle;
	}

	public void setLabelRotationAngle(float angle) {
		mLabelRotationAngle = angle;
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

	public void setAvoidFirstLastClipping(boolean enabled) {
		mAvoidFirstLastClipping = enabled;
	}

	public boolean isAvoidFirstLastClippingEnabled() {
		return mAvoidFirstLastClipping;
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
