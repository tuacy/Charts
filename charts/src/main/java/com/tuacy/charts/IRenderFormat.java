package com.tuacy.charts;

/**
 * 格式化数据
 */

public interface IRenderFormat {

	/**
	 *
	 * @param index:x轴上点的位置
	 * @param pointerValue:点对于的值
	 * @return 数据显示的时候做格式化
	 */
	String format(int index, Number pointerValue);

}
