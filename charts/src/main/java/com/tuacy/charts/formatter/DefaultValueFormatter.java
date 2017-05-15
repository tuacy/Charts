package com.tuacy.charts.formatter;


import com.tuacy.charts.ViewPortHandler;
import com.tuacy.charts.data.Entry;

import java.text.DecimalFormat;

public class DefaultValueFormatter implements ValueFormatter {

	/**
	 * decimalformat for formatting
	 */
	private DecimalFormat mFormat;

	/**
	 * Constructor that specifies to how many digits the value should be
	 * formatted.
	 */
	public DefaultValueFormatter(int digits) {

		StringBuffer b = new StringBuffer();
		for (int i = 0; i < digits; i++) {
			if (i == 0) {
				b.append(".");
			}
			b.append("0");
		}

		mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
	}

	@Override
	public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

		// put more logic here ...
		// avoid memory allocations here (for performance reasons)

		return mFormat.format(value);
	}

}
