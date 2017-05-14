package com.tuacy.charts;

/**
 * 格式化数据
 */

public interface IRenderFormat {

	/**
	 * @return 数据显示的时候做格式化
	 */
	String format(int index, Number pointerValue);

}
