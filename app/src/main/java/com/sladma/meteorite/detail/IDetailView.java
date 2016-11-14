package com.sladma.meteorite.detail;

import android.support.annotation.NonNull;

import com.sladma.common.mvvm.IView;

/**
 * View interface for {@link DetailActivity}.
 * <p>
 * Created by sladma
 */
interface IDetailView extends IView {

    /**
     * Add marker to map with specific attributes
     */
    void addMeteoriteMarker(final float latitude, final float longitude, @NonNull final String title, boolean moveCamera);

}
