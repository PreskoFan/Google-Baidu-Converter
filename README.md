# Google-Baidu-Converter

Baidu Map uses the Mercator projection, with latitude and longitude expressed in degrees, and distances in meters. However, it uses the BD-09 coordinate system, which adds further obfuscation to the already obfuscated national standard in China, GCJ-02 (which in turn is offset from the de facto standard around the world, WGS-84).

The Baidu Map API documentation specifies that real GPS coordinates must be converted via a coordinate conversion interface.  An HTTP interface, JavaScript API, Android SDK, and iOS SDK are available.

The JavaScript coordinate conversion API is demonstrated online by Baidu, but without any reverse (to GCJ-02) conversion capabilities.

This is an Open source implementations in Java, to attempt to convert from "Google"/WGS-84 coordinates to "Baidu"/BD-09/GCJ-02 coordinates and back.


## Sample Usage

1. Download JAR from ```dist``` folder or use Maven

2. Use it in your project


```

import com.preskofan.converter.googlebaidu.*;

GoogleLocation wgs84Shanghai = new GoogleLocation(31.1774276, 121.5272106);
BaiduLocation gcj02Shanghai = wgs84Shanghai.toBaiduLocation();
System.out.println("Shanghai Google WGS-94 location : " + wgs84Shanghai.getLatitude() + "; "
		+ wgs84Shanghai.getLongitude() + " to Baidu GCJ-02 location : " 
		+ gcj02Shanghai.getLatitude() + "; " + gcj02Shanghai.getLongitude());

BaiduLocation wgs84Beijing = new BaiduLocation(39.91334545536069, 116.38404722455657);
GoogleLocation gcj02Beijing = wgs84Beijing.toGoogleLocation();
System.out.println("Beijing Google WGS-94 location : " + wgs84Beijing.getLatitude() + "; "
		+ wgs84Beijing.getLongitude() + " to Baidu GCJ-02 location : " 
		+ gcj02Beijing.getLatitude() + "; " + gcj02Beijing.getLongitude());

```

