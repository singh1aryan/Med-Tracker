
package com.hackumass.med.medapp.Weather;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Flags {

    @SerializedName("nearest-station")
    private Double mNearestStation;
    @SerializedName("sources")
    private List<String> mSources;
    @SerializedName("units")
    private String mUnits;

    public Double getNearestStation() {
        return mNearestStation;
    }

    public void setNearestStation(Double nearestStation) {
        mNearestStation = nearestStation;
    }

    public List<String> getSources() {
        return mSources;
    }

    public void setSources(List<String> sources) {
        mSources = sources;
    }

    public String getUnits() {
        return mUnits;
    }

    public void setUnits(String units) {
        mUnits = units;
    }

}
