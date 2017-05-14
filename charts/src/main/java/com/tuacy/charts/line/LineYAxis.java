package com.tuacy.charts.line;


import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;

import com.tuacy.charts.IAxisAction;
import com.tuacy.charts.bean.Axis;
import com.tuacy.charts.bean.Render;
import com.tuacy.charts.bean.Grid;

import java.util.ArrayList;
import java.util.List;

public class LineYAxis implements IAxisAction {

	private List<String>         mData;
	private float                mAxisWidth;
	private Axis                 mAxis;
	private TextPaint            mTextPaint;
	private Paint                mLinePaint;
	private Paint                mGridPaint;
	private Paint.FontMetricsInt mTextMetrics;
	private Path                 mGridPath;
	private float                mAxisMin;
	private float                mAxisMax;
	private float                mAxisSectionValue;

	public LineYAxis(Axis axis, Grid grid, List<Render> renders) {
		//绘制轴线的paint
		mLinePaint = new Paint();
		mLinePaint.setColor(axis.getLineColor());
		mLinePaint.setStrokeWidth(axis.getLineSize());
		mLinePaint.setAntiAlias(true);
		//绘制轴上数据的paint
		mTextPaint = new TextPaint();
		mTextPaint.setColor(axis.getTextColor());
		mTextPaint.setTextSize(axis.getTextSize());
		mTextPaint.setTextAlign(Paint.Align.RIGHT);
		mTextPaint.setAntiAlias(true);
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
		//确定y轴上每个点的值
		float min = 0, max = 0;
		for (Render render : renders) {
			if (render.getData() != null && !render.getData().isEmpty()) {
				for (Number number : render.getData()) {
					if (number != null) {
						min = Math.min(min, number.floatValue());
						max = Math.max(max, number.floatValue());
					}
				}
			}
		}

		float range = max - min;
		float eacheRange = range / axis.getNumber();
		//调整下范围，避免太靠近
		float adjustMin = min - eacheRange / 3;
		float adjustMax = max + eacheRange / 3;
		float adjustSection = (adjustMax - adjustMin) / axis.getNumber();
		mAxisMin = adjustMin;
		mAxisMax = adjustMax;
		mAxisSectionValue = adjustSection;

		mData = new ArrayList<>();
		for (int index = 0; index <= axis.getNumber(); index++) {
			mData.add(axis.getTextFormat().format(adjustMin + index * adjustSection));
		}

		for (String text : mData) {
			mAxisWidth = Math.max(mAxisWidth, mTextPaint.measureText(text) + axis.getTextSpace());
		}

	}


	public void onDrawAxisLine(Canvas canvas, float xAxisLength, float yAxisLength) {
		canvas.drawLine(0, 0, 0, -yAxisLength, mLinePaint);
	}


	public void onDrawAxisGrid(Canvas canvas, float xAxisLength, float yAxisLength) {
		if (mData != null && !mData.isEmpty()) {
			float sectionHeight = (yAxisLength - mAxis.getTopTextOffset()) / mAxis.getNumber();
			for (int index = 0; index < mData.size(); index++) {
				if (index == 0) {
					continue;
				}
				mGridPath.reset();
				mGridPath.moveTo(0, -index * sectionHeight);
				mGridPath.lineTo(xAxisLength, -index * sectionHeight);
				canvas.drawPath(mGridPath, mGridPaint);
			}
		}
	}


	public void onDrawAxisPoint(Canvas canvas, float xAxisLength, float yAxisLength) {

		if (mData != null && !mData.isEmpty()) {
			float baseLineOffset = (mTextMetrics.bottom - mTextMetrics.top) / 2.0f - mTextMetrics.bottom;
			float sectionHeight = (yAxisLength - mAxis.getTopTextOffset()) / mAxis.getNumber();
			for (int index = 0; index < mData.size(); index++) {
				String text = mData.get(index);
				canvas.drawText(text, -mAxis.getTextSpace(), -sectionHeight * index + baseLineOffset, mTextPaint);
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
		return mAxisWidth;
	}

	@Override
	public float onGetAxisMin() {
		return mAxisMin;
	}

	@Override
	public float onGetAxisConvert(float axisLength) {
		float sectionLength = (axisLength - mAxis.getTopTextOffset()) / mAxis.getNumber();
		return mAxisSectionValue / sectionLength;
	}

}
