package com.tuacy.charts.utils;


public class FSize {

	public final float width;
	public final float height;

	public FSize(final float width, final float height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj instanceof FSize) {
			final FSize other = (FSize) obj;
			return width == other.width && height == other.height;
		}
		return false;
	}

	@Override
	public String toString() {
		return width + "x" + height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Float.floatToIntBits(width) ^ Float.floatToIntBits(height);
	}
}
