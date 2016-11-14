package com.sladma.meteorite.data.model;

import org.parceler.Parcel;

import io.realm.RealmObject;

/**
 * Simple date
 * <p>
 * Created by sladma
 */
@SuppressWarnings("WeakerAccess")
@Parcel
public class SimpleDate extends RealmObject {

    public long timestamp;

    public SimpleDate() {
    }

    public SimpleDate(long timestamp) {
        this.timestamp = timestamp;
    }
}
