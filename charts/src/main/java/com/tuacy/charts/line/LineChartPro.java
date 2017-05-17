package com.tuacy.charts.line;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tuacy.charts.ViewPortHandler;
import com.tuacy.charts.components.XAxis;
import com.tuacy.charts.components.YAxis;
import com.tuacy.charts.data.LineData;
import com.tuacy.charts.data.LineDataSet;
import com.tuacy.charts.formatter.DefaultValueFormatter;
import com.tuacy.charts.formatter.ValueFormatter;
import com.tuacy.charts.renderer.XAxisRenderer;
import com.tuacy.charts.renderer.YAxisRenderer;
import com.tuacy.charts.utils.DensityUtils;
import com.tuacy.charts.utils.Utils;

public class LineChartPro extends View {

	protected ViewPortHandler mViewPortHandler;
	private   XAxis           mXAxis;
	private   YAxis           mYAxis;
	private   XAxisRenderer   mXAxisRenderer;
	private   YAxisRenderer   mYAxisRenderer;
	private   LineData        mData;
	private   ValueFormatter  mDefaultFormatter;


	public LineChartPro(Context context) {
		this(context, null);
	}

	public LineChartPro(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineChartPro(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mViewPortHandler = new ViewPortHandler(getContext());
		mXAxis = new XAxis(getContext());
		mYAxis = new YAxis(getContext());
		mXAxisRenderer = new XAxisRenderer(getContext(), mViewPortHandler, mXAxis, null);
		mYAxisRenderer = new YAxisRenderer(getContext(), mViewPortHandler, mYAxis, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int size = DensityUtils.dp2px(getContext(), 50);
		//确定控件的大小
		setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(size, widthMeasureSpec)),
							 Math.max(getSuggestedMinimumHeight(), resolveSize(size, heightMeasureSpec)));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		calculateOffsets();
		//		// 绘制表格背景
		//		drawGridBackground(canvas);
		//		// 绘制x轴
		//		mXAxisRenderer.renderAxisLine(canvas);
		//		// 绘制y轴
		mYAxisRenderer.renderAxisLabels(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		if (w > 0 && h > 0 && w < 10000 && h < 10000) {
			mViewPortHandler.setChartDimens(w, h);
		}
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * 画背景框，mViewPortHandler 来控制范围
	 */
	private void drawGridBackground(Canvas canvas) {

	}

	/**
	 * 设置要显示的曲线数据
	 */
	public void setData(@NonNull LineData data) {
		mData = data;
		calculateFormatter(mData.getYMin(), mData.getYMax());
		for (LineDataSet set : mData.getDataSets()) {
			if (set.getValueFormatter() == null || set.getValueFormatter() instanceof DefaultValueFormatter) {
				set.setValueFormatter(mDefaultFormatter);
			}
		}

		mXAxis.mAxisMaximum = mData.getXVals().size() - 1;
		mXAxis.mAxisRange = Math.abs(mXAxis.mAxisMaximum - mXAxis.mAxisMinimum);
		mXAxisRenderer.computeAxis(mData.getXValMaximumLength(), mData.getXVals());

		mYAxis.calculate(mData.getYMin(), mData.getYMax());
		mYAxisRenderer.computeAxis(mYAxis.mAxisMinimum, mYAxis.mAxisMaximum);
	}

	protected void calculateFormatter(float min, float max) {
		float reference;
		if (mData == null || mData.getXValCount() < 2) {
			reference = Math.max(Math.abs(min), Math.abs(max));
		} else {
			reference = Math.abs(max - min);
		}

		int digits = Utils.getDecimals(reference);
		mDefaultFormatter = new DefaultValueFormatter(digits);

	}

	/**
	 * 这里会确定曲线图内容的区域
	 */
	public void calculateOffsets() {
		float offsetLeft = mYAxis.getRequiredWidthSpace(mYAxisRenderer.getPaintAxisLabels()) + mYAxis.getXOffset();
		mViewPortHandler.restrainViewPort(offsetLeft, 0, 0, 20);

	}
}
