package com.tuacy.charts.line;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.tuacy.charts.IAxisFormat;
import com.tuacy.charts.bean.Axis;
import com.tuacy.charts.bean.Render;
import com.tuacy.charts.bean.Grid;

import java.util.List;
import java.util.Locale;

/**
 * 曲线图
 */

public class LineChart extends View {

	private LineAxis     mLineAxis;
	private List<Number> mXData;
	private List<Render> mRenders;
	//x轴描述
	private Axis         mXAxis;
	//y轴描述
	private Axis         mYAxis;
	//x表格先的描述
	private Grid         mXGrid;
	//y表格先的描述
	private Grid         mYGrid;
	private LineRender   mRender;


	public LineChart(Context context) {
		this(context, null);
	}

	public LineChart(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initData();
	}

	private void initData() {
		mLineAxis = new LineAxis();
		mXAxis = new Axis(getContext());
		mXAxis.setTextFormat(new IAxisFormat(){
			@Override
			public String format(Number axisValue) {
				return String.format(Locale.getDefault(), "%02d月", axisValue.intValue());
			}
		});
		mXAxis.setNumber(8);
		mYAxis = new Axis(getContext());
		mYAxis.setTextFormat(new IAxisFormat(){
			@Override
			public String format(Number axisValue) {
				return String.format(Locale.getDefault(), "%.02f", axisValue.floatValue());
			}
		});
		mYAxis.setNumber(3);

		mXGrid = new Grid();
		mYGrid = new Grid();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//确定控件大小，这里我们直接使用系统默认的，当width height 都是wrap_content的时候，让他和父控件相同
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mXData == null || mRenders == null) {
			return;
		}
		drawPrepare();
		//canvas移动到坐标原点去
		float pointeX = mLineAxis.getYAxis().onGetAxisSize();
		float pointeY = getHeight() - mLineAxis.getXAxis().onGetAxisSize();
		canvas.translate(pointeX, pointeY);
		//绘制x轴和y轴
		if (mLineAxis != null) {
			mLineAxis.onAxisDraw(canvas, getWidth() - pointeX, pointeY);
		}
		if (mRender != null) {
			mRender.onDraw(canvas);
		}
	}

	public void setData(List<Number> xData, List<Render> renders) {
		mXData = xData;
		mRenders = renders;
	}

	private void drawPrepare() {
		LineXAxis xAxis = new LineXAxis(mXAxis,mXGrid, mXData);
		LineYAxis yAxis = new LineYAxis(mYAxis, mYGrid, mRenders);
		mLineAxis.setXAxis(xAxis);
		mLineAxis.setYAxis(yAxis);
		float pointeX = mLineAxis.getYAxis().onGetAxisSize();
		float pointeY = getHeight() - mLineAxis.getXAxis().onGetAxisSize();
		mRender = new LineRender(mRenders, xAxis.onGetAxisConvert(getWidth() - pointeX), yAxis.onGetAxisMin(), yAxis.onGetAxisConvert(pointeY));
	}


}
