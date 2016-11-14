package com.sladma.meteorite.detail;

import android.support.annotation.NonNull;

import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.common.mvvm.IViewModel;

/**
 * ViewModel interface for {@link DetailActivity}.
 * <p>
 * Created by sladma
 */
interface IDetailViewModel extends IViewModel<IDetailView> {

    /**
     * Meteorite setter
     */
    void setMeteorite(@NonNull final Meteorite meteorite);

    /**
     * Informs that async map is ready to work with it
     */
    void onMapReady();
}
