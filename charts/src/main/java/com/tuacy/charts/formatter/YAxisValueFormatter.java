package com.tuacy.charts.formatter;


import com.tuacy.charts.components.YAxis;

public interface YAxisValueFormatter {
	String getFormattedValue(float value, YAxis yAxis);
}
