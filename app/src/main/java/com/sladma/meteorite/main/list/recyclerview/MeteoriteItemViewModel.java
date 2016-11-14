package com.sladma.meteorite.main.list.recyclerview;

import android.view.View;

import com.sladma.common.mvvm.BaseViewModel;
import com.sladma.meteorite.data.model.Meteorite;

/**
 * ViewModel for {@link MeteoriteItemViewHolder}
 * <p>
 * Created by sladma
 */
public class MeteoriteItemViewModel extends BaseViewModel<IMeteoriteItemView> implements IMeteoriteItemViewModel {

    public Meteorite meteorite;

    @Override
    public void setMeteorite(Meteorite meteorite) {
        this.meteorite = meteorite;
        notifyChange();
    }

    @Override
    public void onItemClicked(View view) {
        getView().showDetail(meteorite);
    }

}