package com.sladma.meteorite.detail;

import android.support.annotation.NonNull;

import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.common.mvvm.BaseViewModel;

/**
 * ViewModel for {@link DetailActivity}.
 * <p>
 * Created by sladma
 */
public class DetailViewModel extends BaseViewModel<IDetailView> implements IDetailViewModel {

    public Meteorite meteorite;

    @Override
    public void setMeteorite(@NonNull final Meteorite meteorite) {
        this.meteorite = meteorite;
        notifyChange();
    }

    @Override
    public void onMapReady() {
        if (meteorite != null) {
            getView().addMeteoriteMarker(meteorite.location.latitude, meteorite.location.longitude, meteorite.name, true);
        }
    }

}
