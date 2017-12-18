package com.preskofan.converter.googlebaidu;

public class BaiduLocation extends GeoLocation {

	public BaiduLocation() {
	}

	public BaiduLocation(double latitude, double longitude) {
		super(latitude, longitude);
	}

	public GoogleLocation toGoogleLocation() {
		if (GeoLocation.isInChina(this.latitude, this.longitude)) {
			GeoLocation offset = this.offset();
			return new GoogleLocation(this.latitude - offset.getLatitude(), this.longitude - offset.getLongitude());
		} else {
			return new GoogleLocation(this.latitude, this.longitude);
		}
	}

	public GoogleLocation toExactGoogleLocation() {
		final double initDelta = 0.01;
		final double threshold = 0.000001;
		double dLat = initDelta, dLng = initDelta;
		double mLat = this.latitude - dLat, mLng = this.longitude - dLng;
		double pLat = this.latitude + dLat, pLng = this.longitude + dLng;
		double wgsLat, wgsLng;
		GoogleLocation currentWGSPointer = null;
		for (int i = 0; i < 30; i++) {
			wgsLat = (mLat + pLat) / 2;
			wgsLng = (mLng + pLng) / 2;
			currentWGSPointer = new GoogleLocation(wgsLat, wgsLng);
			BaiduLocation tmp = currentWGSPointer.toBaiduLocation();
			dLat = tmp.getLatitude() - this.getLatitude();
			dLng = tmp.getLongitude() - this.getLongitude();
			if ((Math.abs(dLat) < threshold) && (Math.abs(dLng) < threshold)) {
				return currentWGSPointer;
			} else {
				System.out.println(dLat + ":" + dLng);
			}
			if (dLat > 0) {
				pLat = wgsLat;
			} else {
				mLat = wgsLat;
			}
			if (dLng > 0) {
				pLng = wgsLng;
			} else {
				mLng = wgsLng;
			}
		}
		return currentWGSPointer;
	}

}
