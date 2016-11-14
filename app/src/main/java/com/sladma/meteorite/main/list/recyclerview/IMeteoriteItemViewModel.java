package com.sladma.meteorite.main.list.recyclerview;

import android.view.View;

import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.common.mvvm.IViewModel;

/**
 * ViewModel interface for {@link MeteoriteItemViewHolder}
 * <p>
 * Created by sladma
 */
interface IMeteoriteItemViewModel extends IViewModel<IMeteoriteItemView> {

    /**
     * Meteorite setter
     */
    void setMeteorite(Meteorite meteorite);

    /**
     * Handles click on item in list
     */
    void onItemClicked(View view);
}
