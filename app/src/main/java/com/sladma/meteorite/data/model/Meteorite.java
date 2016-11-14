package com.sladma.meteorite.data.model;

import android.text.format.DateFormat;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;
import java.util.Locale;

import io.realm.MeteoriteRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * This object represents a meteorite.
 * <p>
 * It is used by GSON to serialize JSON into object.
 * Structure of JSON and doc can be found at https://dev.socrata.com/foundry/data.nasa.gov/y77d-th95
 * Example:
 * {
 * * "fall":"Fell",
 * * "geolocation":{
 * *** "type":"Point",
 * *** "coordinates":[6.08333,50.775]
 * * },
 * * "id":"1",
 * * "mass":"21",
 * * "name":"Aachen",
 * * "nametype":"Valid",
 * * "recclass":"L5",
 * * "reclat":"50.775000",
 * * "reclong":"6.083330",
 * * "year":"1880-01-01T00:00:00.000"
 * }
 * <p>
 * Created by sladma
 */
@SuppressWarnings("WeakerAccess")
@Parcel(
        implementations = {MeteoriteRealmProxy.class},
        value = Parcel.Serialization.FIELD,
        analyze = {Meteorite.class}
)
public class Meteorite extends RealmObject {

    @PrimaryKey
    public int id;

    public String name;

    @SerializedName("recclass")
    public String type;

    @SerializedName("mass")
    public float weight; // grams

    public SimpleDate year;

    @SerializedName("geolocation")
    public Location location; // custom deserializer is used to convert into Location

    /**
     * Print weight with units based on value
     * < 100   - 50.3g
     * < 1000  - 530g
     * > 1000  - 1.5kg
     */
    public String getWeightString() {
        if (weight < 100) {
            return String.format(Locale.US, "%.1fg", weight);
        } else if (weight < 1000) {
            return String.format(Locale.US, "%dg", (int) weight);
        } else {
            return String.format(Locale.US, "%dkg", (int) weight / 1000);
        }
    }

    /**
     * Print only year from timestamp
     */
    public CharSequence getYearString() {
        return DateFormat.format("yyyy", new Date(year.timestamp));
    }

}
