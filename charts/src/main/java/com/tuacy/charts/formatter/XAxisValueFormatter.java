package com.tuacy.charts.formatter;

import com.tuacy.charts.ViewPortHandler;


public interface XAxisValueFormatter {

	String getXValue(String original, int index, ViewPortHandler viewPortHandler);
}
