package com.tuacy.charts.renderer;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tuacy.charts.ViewPortHandler;
import com.tuacy.charts.components.YAxis;
import com.tuacy.charts.utils.Utils;


import javax.xml.transform.Transformer;

public class YAxisRenderer extends AxisRenderer {

	private YAxis mYAxis;

	public YAxisRenderer(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
		super(context, viewPortHandler, trans);

		this.mYAxis = yAxis;

		mAxisLabelPaint.setColor(Color.BLACK);
		mAxisLabelPaint.setTextSize(20f);
	}

	/**
	 * Computes the axis values.
	 *
	 * @param yMin - the minimum y-value in the data object for this axis
	 * @param yMax - the maximum y-value in the data object for this axis
	 */
	public void computeAxis(float yMin, float yMax) {
		computeAxisValues(yMin, yMax);
	}

	/**
	 * Sets up the y-axis labels. Computes the desired number of labels between the two given extremes. Unlike the
	 * papareXLabels() method, this method needs to be called upon every refresh of the view.
	 */
	private void computeAxisValues(float min, float max) {

		int labelCount = mYAxis.getLabelCount();
		double range = Math.abs(max - min);

		if (labelCount == 0 || range <= 0) {
			mYAxis.mEntries = new float[]{};
			mYAxis.mEntryCount = 0;
			return;
		}

		double rawInterval = range / labelCount;
		double interval = Utils.roundToNextSignificant(rawInterval);

		double intervalMagnitude = Utils.roundToNextSignificant(Math.pow(10, (int) Math.log10(interval)));
		int intervalSigDigit = (int) (interval / intervalMagnitude);
		if (intervalSigDigit > 5) {
			interval = Math.floor(10 * intervalMagnitude);
		}

		double first = interval == 0.0 ? 0.0 : Math.ceil(min / interval) * interval;
		double last = interval == 0.0 ? 0.0 : Utils.nextUp(Math.floor(max / interval) * interval);

		double f;
		int i;
		int n = 0;
		if (interval != 0.0) {
			for (f = first; f <= last; f += interval) {
				++n;
			}
		}

		mYAxis.mEntryCount = n;

		if (mYAxis.mEntries.length < n) {
			// Ensure stops contains at least numStops elements.
			mYAxis.mEntries = new float[n];
		}

		for (f = first, i = 0; i < n; f += interval, ++i) {

			if (f == 0.0) // Fix for negative zero case (Where value == -0.0, and 0.0 == -0.0)
			{
				f = 0.0;
			}
			mYAxis.mEntries[i] = (float) f;
		}

		// set decimals
		if (interval < 1) {
			mYAxis.mDecimals = (int) Math.ceil(-Math.log10(interval));
		} else {
			mYAxis.mDecimals = 0;
		}
	}

	/**
	 * draws the y-axis labels to the screen
	 */
	@Override
	public void renderAxisLabels(Canvas c) {

		if (!mYAxis.isEnabled() || !mYAxis.isDrawLabelsEnabled()) {
			return;
		}

		float[] positions = new float[mYAxis.mEntryCount * 2];
		pointValuesToPixel(positions);

		mAxisLabelPaint.setTypeface(mYAxis.getTypeface());
		mAxisLabelPaint.setTextSize(mYAxis.getTextSize());
		mAxisLabelPaint.setColor(mYAxis.getTextColor());

		float yoffset = Utils.calcTextHeight(mAxisLabelPaint, "A") / 2.5f + mYAxis.getYOffset();

		mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);

		drawYLabels(c, positions, yoffset);
	}

	@Override
	public void renderAxisLine(Canvas c) {

		if (!mYAxis.isEnabled() || !mYAxis.isDrawAxisLineEnabled()) {
			return;
		}

		mAxisLinePaint.setColor(mYAxis.getAxisLineColor());
		mAxisLinePaint.setStrokeWidth(mYAxis.getAxisLineWidth());

		c.drawLine(mViewPortHandler.contentRight(), mViewPortHandler.contentTop(), mViewPortHandler.contentRight(),
				   mViewPortHandler.contentBottom(), mAxisLinePaint);
	}

	/**
	 * 每个数据转换为坐标点
	 */
	private void pointValuesToPixel(float[] positions) {
		float xoffset = mViewPortHandler.contentLeft() - mYAxis.getXOffset();
		float contentHeight = mViewPortHandler.contentHeight();
		float eachHeigh = contentHeight / (mYAxis.mEntryCount - 1);
		for (int i = 0; i < mYAxis.mEntryCount; i++) {
			positions[2 * i] = xoffset;
			positions[2 * i + 1] = eachHeigh * (mYAxis.mEntryCount - 1 - i);
		}
	}

	/**
	 * draws the y-labels on the specified x-position
	 */
	private void drawYLabels(Canvas c, float[] positions, float offset) {
		for (int i = 0; i < mYAxis.mEntryCount; i++) {
			String text = mYAxis.getFormattedLabel(i);
			c.drawText(text, positions[i * 2], positions[i * 2 + 1] + offset, mAxisLabelPaint);
		}
	}

	@Override
	public void renderGridLines(Canvas c) {

		if (!mYAxis.isEnabled()) {
			return;
		}
	}

	/**
	 * Draws the zero line at the specified position.
	 */
	protected void drawZeroLine(Canvas c, float x1, float x2, float y1, float y2) {

	}

	/**
	 * Draws the LimitLines associated with this axis to the screen.
	 */
	@Override
	public void renderLimitLines(Canvas c) {

	}
}
