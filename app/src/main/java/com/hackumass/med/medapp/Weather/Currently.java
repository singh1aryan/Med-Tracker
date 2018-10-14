
package com.hackumass.med.medapp.Weather;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Currently {

    @SerializedName("apparentTemperature")
    private Double mApparentTemperature;


    @SerializedName("humidity")
    private Double mHumidity;

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("ozone")
    private Double mOzone;

    @SerializedName("precipIntensity")
    private Double mPrecipIntensity;

    @SerializedName("pressure")
    private Double mPressure;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("temperature")
    private Double mTemperature;

    @SerializedName("time")
    private Long mTime;

    @SerializedName("uvIndex")
    private Double mUvIndex;

    @SerializedName("visibility")
    private Double mVisibility;

    @SerializedName("windSpeed")
    private Double mWindSpeed;

    public Double getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }


    public Double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Double humidity) {
        mHumidity = humidity;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }


    public Double getOzone() {
        return mOzone;
    }

    public void setOzone(Double ozone) {
        mOzone = ozone;
    }

    public Double getPrecipIntensity() {
        return mPrecipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        mPrecipIntensity = precipIntensity;
    }


    public Double getPressure() {
        return mPressure;
    }

    public void setPressure(Double pressure) {
        mPressure = pressure;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public Double getUvIndex() {
        return mUvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        mUvIndex = uvIndex;
    }

    public Double getVisibility() {
        return mVisibility;
    }

    public void setVisibility(Double visibility) {
        mVisibility = visibility;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        mWindSpeed = windSpeed;
    }

}
