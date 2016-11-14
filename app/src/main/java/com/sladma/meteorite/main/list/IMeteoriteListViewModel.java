package com.sladma.meteorite.main.list;

import com.sladma.common.mvvm.IBaseAdapterViewModel;

/**
 * ViewModel interface for {@link MeteoriteListFragment}
 * <p>
 * Created by sladma
 */
interface IMeteoriteListViewModel extends IBaseAdapterViewModel<IMeteoriteListView> {

    /**
     * Refresh data
     */
    void refresh();

}
