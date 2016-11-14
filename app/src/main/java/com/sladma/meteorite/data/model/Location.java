package com.sladma.meteorite.data.model;

import org.parceler.Parcel;

import io.realm.RealmObject;

/**
 * This object represents a location of meteorite.
 * <p>
 * Example:
 * * {
 * *** "type":"Point",
 * *** "coordinates":[6.08333,50.775]
 * * }
 * <p>
 * Created by sladma
 */
@Parcel
public class Location extends RealmObject {
    public float latitude;
    public float longitude;

    public Location() {
    }

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}