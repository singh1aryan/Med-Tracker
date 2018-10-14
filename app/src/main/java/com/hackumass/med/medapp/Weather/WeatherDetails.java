
package com.hackumass.med.medapp.Weather;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class WeatherDetails {

    @SerializedName("currently")
    private Currently mCurrently;
    @SerializedName("daily")
    private Daily mDaily;
    @SerializedName("hourly")
    private Hourly mHourly;
    @SerializedName("latitude")
    private Double mLatitude;
    @SerializedName("longitude")
    private Double mLongitude;
    @SerializedName("minutely")
    private Minutely mMinutely;
    @SerializedName("offset")
    private Double mOffset;
    @SerializedName("timezone")
    private String mTimezone;

    public Currently getCurrently() {
        return mCurrently;
    }

    public void setCurrently(Currently currently) {
        mCurrently = currently;
    }

    public Daily getDaily() {
        return mDaily;
    }

    public void setDaily(Daily daily) {
        mDaily = daily;
    }

    public Hourly getHourly() {
        return mHourly;
    }

    public void setHourly(Hourly hourly) {
        mHourly = hourly;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public Minutely getMinutely() {
        return mMinutely;
    }

    public void setMinutely(Minutely minutely) {
        mMinutely = minutely;
    }

    public Double getOffset() {
        return mOffset;
    }

    public void setOffset(Double offset) {
        mOffset = offset;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

}