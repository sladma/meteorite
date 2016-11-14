package com.sladma.meteorite.data.local;

import android.support.annotation.NonNull;

import com.sladma.meteorite.data.model.Meteorite;

import java.util.List;

/**
 * Interface for repository which holds all meteorites and sort/filter it
 * <p>
 * Created by sladma
 */
public interface IMeteoriteRepository {

    /**
     * Via this callback you can get results
     */
    interface Callback {

        /**
         * Invoked with results when success
         */
        void onDone(List<Meteorite> meteorites);

        /**
         * Invoked when error occurred
         */
        void onError();
    }

    /**
     * Get all meteorites since 2011 sorted by their weight
     */
    void getMeteoritesSince2011SortedByWeight(@NonNull final Callback callback);

}
