package com.preskofan.converter.googlebaidu;

import java.text.DecimalFormat;

public class GeoLocation {

	/*
	 * 6,371 km (3,959 mi)
	 */
	public static final double EARTH_RADIUS_METERS = 6378137.0;
	private final static DecimalFormat df = new DecimalFormat("0.000000");

	public GeoLocation() {
	}

	public GeoLocation(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	double longitude;
	double latitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		} else if (other instanceof GeoLocation) {
			GeoLocation otherPointer = (GeoLocation) other;
			return df.format(latitude).equals(df.format(otherPointer.latitude))
					&& df.format(longitude).equals(df.format(otherPointer.longitude));
		} else {
			return false;
		}
	}

	public String toString() {
		return "latitude:" + latitude + " longitude:" + longitude;
	}

	public double distance(GeoLocation target) {
		double earthR = 6371000; // EARTH_RADIUS_METERS = 6378137
		double x = Math.cos(this.latitude * Math.PI / 180) * Math.cos(target.latitude * Math.PI / 180)
				* Math.cos((this.longitude - target.longitude) * Math.PI / 180);
		double y = Math.sin(this.latitude * Math.PI / 180) * Math.sin(target.latitude * Math.PI / 180);
		double s = x + y;
		if (s > 1) {
			s = 1;
		}
		if (s < -1) {
			s = -1;
		}
		double alpha = Math.acos(s);
		double distance = alpha * earthR;
		return distance;
	}

	public static boolean isInChina(double lat, double lng) {
		if ((lng < 72.004) || (lng > 137.8347) || (lat < 0.8293) || (lat > 55.8271)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isInChina() {
		return GeoLocation.isInChina(this.latitude, this.longitude);
	}

	public static double transformLat(double x, double y) {
		return -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x))
				+ (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0
				+ (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0
				+ (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
	}

	public static double transformLon(double x, double y) {
		return 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x))
				+ (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0
				+ (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0
				+ (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
	}

	/**
	 * @param lat
	 * @param lng
	 * @return GeoLocation
	 */
	public GeoLocation offset() {
		return GeoLocation.offset(this.getLatitude(), this.getLongitude());
	}

	/**
	 * @param lat
	 * @param lng
	 * @return GeoLocation
	 */
	public static GeoLocation offset(double lat, double lng) {
		double ee = 0.00669342162296594323;
		double dLat = transformLat(lng - 105.0, lat - 35.0);
		double dLng = transformLon(lng - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * Math.PI;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		GeoLocation offset = new GeoLocation();
		offset.latitude = (dLat * 180.0) / ((EARTH_RADIUS_METERS * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
		offset.longitude = (dLng * 180.0) / (EARTH_RADIUS_METERS / sqrtMagic * Math.cos(radLat) * Math.PI);
		return offset;
	}

}
