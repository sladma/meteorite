package com.sladma.meteorite.main.list.recyclerview;

import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.common.mvvm.IView;

/**
 * View interface for {@link MeteoriteItemViewHolder}
 * <p>
 * Created by sladma
 */
interface IMeteoriteItemView extends IView {

    /**
     * Show detail about meteorite
     */
    void showDetail(Meteorite meteorite);
}
