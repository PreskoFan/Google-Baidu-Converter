# Google-Baidu-Converter

Baidu Map uses the Mercator projection, with latitude and longitude expressed in degrees, and distances in meters. However, it uses the BD-09 coordinate system, which adds further obfuscation to the already obfuscated national standard in China, GCJ-02 (which in turn is offset from the de facto standard around the world, WGS-84).

The Baidu Map API documentation specifies that real GPS coordinates must be converted via a coordinate conversion interface.  An HTTP interface, JavaScript API, Android SDK, and iOS SDK are available.

The JavaScript coordinate conversion API is demonstrated online by Baidu, but without any reverse (to GCJ-02) conversion capabilities.

This is an Open source implementations in Java, to attempt to convert from "Google"/WGS-84 coordinates to "Baidu"/BD-09/GCJ-02 coordinates and back.


## Sample Usage

Coming soon