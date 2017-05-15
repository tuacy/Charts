package com.tuacy.charts.formatter;


import com.tuacy.charts.ViewPortHandler;
import com.tuacy.charts.data.Entry;

public interface ValueFormatter {

	String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler);
}
