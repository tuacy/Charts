package com.tuacy.charts.utils;

import android.graphics.Paint;
import android.graphics.Rect;

public class Utils {

	public static int calcTextHeight(Paint paint, String demoText) {

		Rect r = new Rect();
		paint.getTextBounds(demoText, 0, demoText.length(), r);
		return r.height();
	}

	public static int calcTextWidth(Paint paint, String demoText) {
		return (int) paint.measureText(demoText);
	}
}
