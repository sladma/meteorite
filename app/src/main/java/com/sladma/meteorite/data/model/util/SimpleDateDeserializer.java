package com.sladma.meteorite.data.model.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sladma.meteorite.data.model.SimpleDate;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This class convert string timestamp to object.
 * <p>
 * Created by sladma
 */
public class SimpleDateDeserializer implements JsonDeserializer<SimpleDate> {

    public static final SimpleDateDeserializer INSTANCE = new SimpleDateDeserializer();

    private static final SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);

    private SimpleDateDeserializer() {
    }

    @Override
    public SimpleDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String timeString = json.getAsString();
            return new SimpleDate(sSimpleDateFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new JsonParseException(e);
        }
    }

}
