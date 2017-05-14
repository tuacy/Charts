package com.tuacy.charts;

import android.graphics.Canvas;


/**
 * 绘制x轴或者y轴
 */

public interface IAxisAction {

	/**
	 * 绘制轴线的内容
	 * @param canvas: 画布
	 * @param xAxisLength: x轴的长度
	 * @param yAxisLength: y轴的长度
	 */
	void onDraw(Canvas canvas, float xAxisLength, float yAxisLength);

	/**
	 * 获取轴上面要绘制的文字的最大宽度
	 * @return 如果是x轴返回高度， 如果是y轴返回宽度
	 */
	float onGetAxisSize();

	float onGetAxisMin();

	float onGetAxisConvert(float axisLength);


}
