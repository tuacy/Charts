package com.tuacy.charts.data;


import java.util.List;

public class LineData {

	private List<String>      mXVals             = null;
	private List<LineDataSet> mDataSets          = null;
	private float             mYMax              = 0.0f;
	private float             mYMin              = 0.0f;
	private float             mXValMaximumLength = 0;

	public LineData(List<String> xVals, List<LineDataSet> sets) {
		mXVals = xVals;
		mDataSets = sets;
		init();
	}

	protected void init() {

		checkLegal();
		calcMinMax();
		calcXValMaximumLength();
	}

	/**
	 * 监测是否合法，点的个数不能超过x轴的总数
	 */
	private void checkLegal() {

		if (mDataSets == null) {
			return;
		}

		for (int i = 0; i < mDataSets.size(); i++) {
			if (mDataSets.get(i).getEntryCount() > mXVals.size()) {
				throw new IllegalArgumentException(
					"One or more of the DataSet Entry arrays are longer than the x-values array of this ChartData object.");
			}
		}
	}

	/**
	 * 计算给出来的所有曲线点的最大值和最小值
	 */
	private void calcMinMax() {
		if (mDataSets == null || mDataSets.size() < 1) {
			mYMax = 0f;
			mYMin = 0f;
		} else {
			mYMin = Float.MAX_VALUE;
			mYMax = -Float.MAX_VALUE;
			for (int i = 0; i < mDataSets.size(); i++) {
				LineDataSet set = mDataSets.get(i);
				if (set.getYMin() < mYMin) {
					mYMin = set.getYMin();
				}
				if (set.getYMax() > mYMax) {
					mYMax = set.getYMax();
				}
			}
			if (mYMin == Float.MAX_VALUE) {
				mYMin = 0.f;
				mYMax = 0.f;
			}
		}
	}

	public int getXValCount() {
		return mXVals.size();
	}

	public List<String> getXVals() {
		return mXVals;
	}

	public List<LineDataSet> getDataSets() {
		return mDataSets;
	}

	public float getYMax() {
		return mYMax;
	}

	public float getYMin() {
		return mYMin;
	}

	private void calcXValMaximumLength() {

		if (mXVals.size() <= 0) {
			mXValMaximumLength = 1;
			return;
		}

		int max = 1;

		for (int i = 0; i < mXVals.size(); i++) {

			int length = mXVals.get(i).length();

			if (length > max) {
				max = length;
			}
		}

		mXValMaximumLength = max;
	}

	public float getXValMaximumLength() {
		return mXValMaximumLength;
	}
}
