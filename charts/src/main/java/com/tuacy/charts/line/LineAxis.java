package com.tuacy.charts.line;


import android.graphics.Canvas;

import com.tuacy.charts.IAxisAction;

/**
 * 画曲线图的x轴和y轴
 */

public class LineAxis {

	private IAxisAction mXAxis;
	private IAxisAction mYAxis;

	public LineAxis() {
	}

	public IAxisAction getXAxis() {
		return mXAxis;
	}

	public void setXAxis(IAxisAction XAxis) {
		mXAxis = XAxis;
	}

	public IAxisAction getYAxis() {
		return mYAxis;
	}

	public void setYAxis(IAxisAction YAxis) {
		mYAxis = YAxis;
	}

	public void onAxisDraw(Canvas canvas, float xAxisLength, float yAxisLength) {
		if (mXAxis != null) {
			mXAxis.onDraw(canvas, xAxisLength, yAxisLength);
		}
		if(mYAxis!= null) {
			mYAxis.onDraw(canvas, xAxisLength, yAxisLength);
		}
	}
}
