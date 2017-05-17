package com.tuacy.charts.renderer;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import com.tuacy.charts.ViewPortHandler;
import com.tuacy.charts.components.LimitLine;
import com.tuacy.charts.components.XAxis;
import com.tuacy.charts.utils.FSize;
import com.tuacy.charts.utils.Utils;

import java.util.List;

import javax.xml.transform.Transformer;

public class XAxisRenderer extends AxisRenderer {

	protected XAxis mXAxis;

	public XAxisRenderer(Context context, ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
		super(context, viewPortHandler, trans);

		this.mXAxis = xAxis;

		mAxisLabelPaint.setColor(Color.BLACK);
		mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
		mAxisLabelPaint.setTextSize(10f);
	}

	public void computeAxis(float xValMaximumLength, List<String> xValues) {

		mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
		mAxisLabelPaint.setTextSize(mXAxis.getTextSize());

		StringBuilder widthText = new StringBuilder();

		int xValChars = Math.round(xValMaximumLength);

		for (int i = 0; i < xValChars; i++) {
			widthText.append('h');
		}

		final FSize labelSize = Utils.calcTextSize(mAxisLabelPaint, widthText.toString());

		final float labelWidth = labelSize.width;
		final float labelHeight = Utils.calcTextHeight(mAxisLabelPaint, "Q");

		StringBuilder space = new StringBuilder();
		int xValSpaceChars = mXAxis.getSpaceBetweenLabels();

		for (int i = 0; i < xValSpaceChars; i++) {
			space.append('h');
		}

		final FSize spaceSize = Utils.calcTextSize(mAxisLabelPaint, space.toString());

		mXAxis.mLabelWidth = Math.round(labelWidth + spaceSize.width);
		mXAxis.mLabelHeight = Math.round(labelHeight);

		mXAxis.setValues(xValues);
	}

	@Override
	public void renderAxisLabels(Canvas c) {

		if (!mXAxis.isEnabled() || !mXAxis.isDrawLabelsEnabled()) {
			return;
		}

		float yoffset = mXAxis.getYOffset();

		mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
		mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
		mAxisLabelPaint.setColor(mXAxis.getTextColor());
		drawLabels(c, mViewPortHandler.contentBottom() + yoffset, new PointF(0.5f, 0.0f));

	}

	@Override
	public void renderAxisLine(Canvas c) {

		if (!mXAxis.isDrawAxisLineEnabled() || !mXAxis.isEnabled()) {
			return;
		}

		mAxisLinePaint.setColor(mXAxis.getAxisLineColor());
		mAxisLinePaint.setStrokeWidth(mXAxis.getAxisLineWidth());

		c.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), mViewPortHandler.contentRight(),
				   mViewPortHandler.contentBottom(), mAxisLinePaint);
	}

	/**
	 * draws the x-labels on the specified y-position
	 */
	protected void drawLabels(Canvas c, float pos, PointF anchor) {

		// pre allocate to save performance (dont allocate in loop)
		float[] position = new float[]{0f,
									   0f};

		for (int i = mMinX; i <= mMaxX; i += mXAxis.mAxisLabelModulus) {

			position[0] = i;
			if (mViewPortHandler.isInBoundsX(position[0])) {
				String label = mXAxis.getValues().get(i);
				drawLabel(c, label, i, position[0], pos, anchor, 0);
			}
		}
	}

	protected void drawLabel(Canvas c, String label, int xIndex, float x, float y, PointF anchor, float angleDegrees) {
		String formattedLabel = mXAxis.getValueFormatter().getXValue(label, xIndex, mViewPortHandler);
		//TODO:
	}

	@Override
	public void renderGridLines(Canvas c) {

		if (!mXAxis.isDrawGridLinesEnabled() || !mXAxis.isEnabled()) {
			return;
		}

		// pre alloc
		float[] position = new float[]{0f,
									   0f};

		mGridPaint.setColor(mXAxis.getGridColor());
		mGridPaint.setStrokeWidth(mXAxis.getGridLineWidth());
		mGridPaint.setPathEffect(mXAxis.getGridDashPathEffect());

		Path gridLinePath = new Path();

		for (int i = mMinX; i <= mMaxX; i += mXAxis.mAxisLabelModulus) {

			position[0] = i;

			if (position[0] >= mViewPortHandler.offsetLeft() && position[0] <= mViewPortHandler.getChartWidth()) {

				gridLinePath.moveTo(position[0], mViewPortHandler.contentBottom());
				gridLinePath.lineTo(position[0], mViewPortHandler.contentTop());

				// draw a path because lines don't support dashing on lower android versions
				c.drawPath(gridLinePath, mGridPaint);
			}

			gridLinePath.reset();
		}
	}

	/**
	 * Draws the LimitLines associated with this axis to the screen.
	 */
	@Override
	public void renderLimitLines(Canvas c) {

		List<LimitLine> limitLines = mXAxis.getLimitLines();

		if (limitLines == null || limitLines.size() <= 0) {
			return;
		}

		float[] position = new float[2];

		for (int i = 0; i < limitLines.size(); i++) {

			LimitLine l = limitLines.get(i);

			if (!l.isEnabled()) {
				continue;
			}

			position[0] = l.getLimit();
			position[1] = 0.f;

			renderLimitLineLine(c, l, position);
			renderLimitLineLabel(c, l, position, 2.f + l.getYOffset());
		}
	}

	float[] mLimitLineSegmentsBuffer = new float[4];
	private Path mLimitLinePath = new Path();

	public void renderLimitLineLine(Canvas c, LimitLine limitLine, float[] position) {
		mLimitLineSegmentsBuffer[0] = position[0];
		mLimitLineSegmentsBuffer[1] = mViewPortHandler.contentTop();
		mLimitLineSegmentsBuffer[2] = position[0];
		mLimitLineSegmentsBuffer[3] = mViewPortHandler.contentBottom();

		mLimitLinePath.reset();
		mLimitLinePath.moveTo(mLimitLineSegmentsBuffer[0], mLimitLineSegmentsBuffer[1]);
		mLimitLinePath.lineTo(mLimitLineSegmentsBuffer[2], mLimitLineSegmentsBuffer[3]);

		mLimitLinePaint.setStyle(Paint.Style.STROKE);
		mLimitLinePaint.setColor(limitLine.getLineColor());
		mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
		mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());

		c.drawPath(mLimitLinePath, mLimitLinePaint);
	}

	public void renderLimitLineLabel(Canvas c, LimitLine limitLine, float[] position, float yOffset) {
		String label = limitLine.getLabel();

		// if drawing the limit-value label is enabled
		if (label != null && !label.equals("")) {

			mLimitLinePaint.setStyle(limitLine.getTextStyle());
			mLimitLinePaint.setPathEffect(null);
			mLimitLinePaint.setColor(limitLine.getTextColor());
			mLimitLinePaint.setStrokeWidth(0.5f);
			mLimitLinePaint.setTextSize(limitLine.getTextSize());

			float xOffset = limitLine.getLineWidth() + limitLine.getXOffset();

			mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
			c.drawText(label, position[0] + xOffset, mViewPortHandler.contentBottom() - yOffset, mLimitLinePaint);

		}

	}
}
