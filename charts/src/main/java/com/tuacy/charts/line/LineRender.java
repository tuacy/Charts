package com.tuacy.charts.line;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import com.tuacy.charts.IRenderAction;
import com.tuacy.charts.bean.Render;
import com.tuacy.charts.bean.RenderPoint;

import java.util.ArrayList;
import java.util.List;

public class LineRender implements IRenderAction {

	private Paint mRenderPaint;
	private List<Render> mRender;
	private List<List<RenderPoint>> mRenderPoint;
	private Path mRenderPath;

	public LineRender(List<Render> renders, float xAxisConvert, float yAxisMin, float yAxisConvert) {
		mRenderPaint = new Paint();
		mRenderPaint.setStyle(Paint.Style.STROKE);
		mRenderPaint.setAntiAlias(true);
		mRenderPath = new Path();
		mRender = renders;
		mRenderPoint = toRendPoint(renders,xAxisConvert, yAxisMin, yAxisConvert);
	}

	private List<List<RenderPoint>> toRendPoint(List<Render> renders,  float xAxisConvert, float yAxisMin, float yAxisConvert) {
		if (renders == null || renders.isEmpty()) {
			return null;
		}
		List<List<RenderPoint>> pointList = new ArrayList<>();
		for (Render render : renders) {
			if (render.getData() != null && !render.getData().isEmpty()) {
				List<RenderPoint> renderPoints = new ArrayList<>();
				for (int index = 0; index < render.getData().size(); index++) {
					Number value = render.getData().get(index);
					if (value != null) {
						float pointX = index * xAxisConvert;
						float pointY = -(value.floatValue() - yAxisMin) / yAxisConvert;
						RenderPoint point = new RenderPoint(index, value, pointX, pointY);
						renderPoints.add(point);
					} else {
						renderPoints.add(null);
					}
				}
				pointList.add(renderPoints);
			}
		}
		return pointList;
	}

	@Override
	public void onDraw(Canvas canvas) {
		if (mRenderPoint != null && !mRenderPoint.isEmpty()) {
			for (int index = 0; index < mRenderPoint.size(); index++) {
				onDrawRender(canvas, index, mRenderPoint.get(index));
			}
		}
	}

	private void onDrawRender(Canvas canvas, int index, List<RenderPoint> points) {
		if (points == null || points.isEmpty()) {
			return;
		}
		mRenderPaint.setStrokeWidth(mRender.get(index).getWidth());
		mRenderPaint.setColor(mRender.get(index).getColor());
		mRenderPath.reset();
		for (int loop = 0; loop < points.size(); loop++) {
			RenderPoint point = points.get(loop);
			if (loop == 0) {
				mRenderPath.moveTo(point.getPointerX(), point.getPointerY());
			} else {
				mRenderPath.lineTo(point.getPointerX(), point.getPointerY());
			}
		}
		canvas.drawPath(mRenderPath, mRenderPaint);

	}
}
