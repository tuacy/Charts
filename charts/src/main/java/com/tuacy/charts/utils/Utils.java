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

	public static FSize calcTextSize(Paint paint, String demoText) {

		Rect r = new Rect();
		paint.getTextBounds(demoText, 0, demoText.length(), r);
		return new FSize(r.width(), r.height());
	}

	public static float roundToNextSignificant(double number) {
		final float d = (float) Math.ceil((float) Math.log10(number < 0 ? -number : number));
		final int pw = 1 - (int) d;
		final float magnitude = (float) Math.pow(10, pw);
		final long shifted = Math.round(number * magnitude);
		return shifted / magnitude;
	}

	/**
	 * Returns the appropriate number of decimals to be used for the provided
	 * number.
	 */
	public static int getDecimals(float number) {

		float i = roundToNextSignificant(number);
		return (int) Math.ceil(-Math.log10(i)) + 2;
	}

	public static double nextUp(double d) {
		if (d == Double.POSITIVE_INFINITY) {
			return d;
		} else {
			d += 0.0d;
			return Double.longBitsToDouble(Double.doubleToRawLongBits(d) + ((d >= 0.0d) ? +1L : -1L));
		}
	}


}
