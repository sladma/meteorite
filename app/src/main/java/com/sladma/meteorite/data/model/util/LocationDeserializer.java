package com.sladma.meteorite.data.model.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sladma.meteorite.data.model.Location;

import java.lang.reflect.Type;

/**
 * This class convert nested JSON into Location object.
 * Example:
 * {"type":"Point","coordinates":[95.16667,44.83333]}    // [longitude, latitude]
 * <p>
 * Created by sladma
 */
public class LocationDeserializer implements JsonDeserializer<Location> {

    public static final LocationDeserializer INSTANCE = new LocationDeserializer();

    private LocationDeserializer() {
    }

    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray coordinates = json.getAsJsonObject().getAsJsonArray("coordinates");
        return new Location(coordinates.get(1).getAsFloat(), coordinates.get(0).getAsFloat());
    }

}
