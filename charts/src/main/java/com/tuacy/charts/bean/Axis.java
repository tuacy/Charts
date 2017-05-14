package com.tuacy.charts.bean;

import android.content.Context;
import android.graphics.Color;

import com.tuacy.charts.IAxisFormat;
import com.tuacy.charts.utils.DensityUtils;

/**
 * 轴线信息
 */

public class Axis {

	//坐标轴上文字大小
	private int         mTextSize;
	//坐标轴上文字的颜色
	private int         mTextColor;
	//坐标轴上文字的格式化
	private IAxisFormat mTextFormat;
	//坐标轴线的大小
	private int         mLineSize;
	//走标轴线的颜色
	private int         mLineColor;
	//坐标轴上文字的左边偏移位置(不至于靠的太近)
	private int         mTextSpace;
	//坐标轴上画多少个点
	private int         mNumber;
	//坐标轴上顶部点偏移位置
	private int         mTopTextOffset;

	public Axis(Context context) {
		if (context == null) {
			throw new NullPointerException();
		}
		mTextSize = DensityUtils.sp2px(context, 12);
		mTextColor = Color.BLACK;
		mTextFormat = new IAxisFormat() {
			@Override
			public String format(Number axisValue) {
				if (axisValue == null) {
					return "";
				} else {
					return axisValue.intValue() + "";
				}
			}
		};
		mLineSize = DensityUtils.dp2px(context, 1);
		mLineColor = Color.BLACK;
		mTextSpace = DensityUtils.dp2px(context, 2);
		mTopTextOffset = 12;
		mNumber = 8;
	}

	public int getTextSize() {
		return mTextSize;
	}

	public void setTextSize(int textSize) {
		mTextSize = textSize;
	}

	public int getTextColor() {
		return mTextColor;
	}

	public void setTextColor(int textColor) {
		mTextColor = textColor;
	}

	public IAxisFormat getTextFormat() {
		return mTextFormat;
	}

	public void setTextFormat(IAxisFormat textFormat) {
		mTextFormat = textFormat;
	}

	public int getLineSize() {
		return mLineSize;
	}

	public void setLineSize(int lineSize) {
		mLineSize = lineSize;
	}

	public int getLineColor() {
		return mLineColor;
	}

	public void setLineColor(int lineColor) {
		mLineColor = lineColor;
	}

	public int getTextSpace() {
		return mTextSpace;
	}

	public void setTextSpace(int textSpace) {
		mTextSpace = textSpace;
	}

	public int getNumber() {
		return mNumber;
	}

	public void setNumber(int number) {
		mNumber = number;
	}

	public int getTopTextOffset() {
		return mTopTextOffset;
	}

	public void setTopTextOffset(int topTextOffset) {
		mTopTextOffset = topTextOffset;
	}
}