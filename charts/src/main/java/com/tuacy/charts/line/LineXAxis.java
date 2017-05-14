package com.tuacy.charts.line;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;

import com.tuacy.charts.IAxisAction;
import com.tuacy.charts.bean.Axis;
import com.tuacy.charts.bean.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * 曲线图的x轴
 */

public class LineXAxis implements IAxisAction {

	private List<String>         mData;
	private float                mAxisHeight;
	private Axis                 mAxis;
	private TextPaint            mTextPaint;
	private Paint                mLinePaint;
	private Paint                mGridPaint;
	private Path                 mGridPath;
	private Paint.FontMetricsInt mTextMetrics;

	public LineXAxis(Axis axis, Grid grid, List<Number> axisData) {
		//绘制轴线的paint
		mLinePaint = new Paint();
		mLinePaint.setColor(axis.getLineColor());
		mLinePaint.setStrokeWidth(axis.getLineSize());
		//绘制轴上数据的paint
		mTextPaint = new TextPaint();
		mTextPaint.setColor(axis.getTextColor());
		mTextPaint.setTextSize(axis.getTextSize());
		mTextPaint.setTextAlign(Paint.Align.CENTER);
		mTextMetrics = mTextPaint.getFontMetricsInt();
		//绘制表格的paint
		mGridPaint = new Paint();
		mGridPaint.setStrokeWidth(grid.getLineWidth());
		mGridPaint.setStyle(Paint.Style.STROKE);
		mGridPaint.setAntiAlias(true);
		mGridPaint.setColor(grid.getLineColor());
		mGridPaint.setPathEffect(new DashPathEffect(new float[]{grid.getLineFullLength(),
																grid.getLineDottedLength()}, 0));
		mGridPath = new Path();

		mAxis = axis;

		mData = new ArrayList<>();
		for (Number number : axisData) {
			mData.add(axis.getTextFormat().format(number));
		}

		mAxisHeight = mTextMetrics.bottom - mTextMetrics.top + axis.getTextSpace();

	}


	public void onDrawAxisLine(Canvas canvas, float xAxisLength, float yAxisLength) {
		canvas.drawLine(0, 0, xAxisLength, 0, mLinePaint);
	}


	public void onDrawAxisGrid(Canvas canvas, float xAxisLength, float yAxisLength) {
		if (mData != null && !mData.isEmpty()) {
			float sectionLength = (xAxisLength - mAxis.getTopTextOffset()) / mAxis.getNumber();
			for (int index = 0; index < mData.size(); index++) {
				if (index == 0) {
					continue;
				}
				mGridPath.reset();
				mGridPath.moveTo(index * sectionLength, 0);
				mGridPath.lineTo(index * sectionLength, -yAxisLength);
				canvas.drawPath(mGridPath, mGridPaint);
			}
		}
	}


	public void onDrawAxisPoint(Canvas canvas, float xAxisLength, float yAxisLength) {
		if (mData != null && !mData.isEmpty()) {
			float baseLineOffset = (mTextMetrics.bottom - mTextMetrics.top) / 2 + mTextMetrics.top;
			float sectionWidth = (xAxisLength - mAxis.getTopTextOffset()) / mAxis.getNumber();
			for (int index = 0; index < mData.size(); index++) {
				String text = mData.get(index);
				canvas.drawText(text, sectionWidth * index,
								mAxis.getTextSpace() + (mTextMetrics.bottom - mTextMetrics.top) / 2.0f - baseLineOffset, mTextPaint);
			}
		}
	}

	@Override
	public void onDraw(Canvas canvas, float xAxisLength, float yAxisLength) {
		onDrawAxisLine(canvas, xAxisLength, yAxisLength);
		onDrawAxisGrid(canvas, xAxisLength, yAxisLength);
		onDrawAxisPoint(canvas, xAxisLength, yAxisLength);
	}

	@Override
	public float onGetAxisSize() {
		return mAxisHeight;
	}

	@Override
	public float onGetAxisMin() {
		return 0;
	}

	@Override
	public float onGetAxisConvert(float axisLength) {
		return (axisLength - mAxis.getTopTextOffset()) / mAxis.getNumber();
	}
}
