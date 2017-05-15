package com.tuacy.charts.data;


import android.graphics.Color;

import java.util.List;

public class LineDataSet {

	private   int         mCircleColor        = Color.WHITE;
	private   float       mCircleRadius       = 8f;
	private   boolean     mDrawCircles        = true;
	private   List<Entry> mData               = null;
	private   int         mLineColor          = Color.BLACK;
	private   float       mLineWidth          = 2.5f;
	private   boolean     mDrawFilled         = false;
	private   float       mHighlightLineWidth = 0.5f;
	private   int         mHighLightColor     = Color.rgb(255, 187, 115);
	protected float       mYMax               = 0.0f;
	protected float       mYMin               = 0.0f;

}
