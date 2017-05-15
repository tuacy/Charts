package com.tuacy.charts.formatter;


import com.tuacy.charts.ViewPortHandler;

public class DefaultXAxisValueFormatter implements XAxisValueFormatter {

	@Override
	public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
		return original; // just return original, no adjustments
	}

}
