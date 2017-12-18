package com.preskofan.converter.googlebaidu;

public class GoogleLocation extends GeoLocation {

	public GoogleLocation(double latitude, double longitude) {
		super(latitude, longitude);
	}

	public BaiduLocation toBaiduLocation() {
		if (GeoLocation.isInChina(this.latitude, this.longitude)) {
			GeoLocation offset = this.offset();
			return new BaiduLocation(this.latitude + offset.getLatitude(), this.longitude + offset.getLongitude());
		} else {
			return new BaiduLocation(this.latitude, this.longitude);
		}
	}

}
