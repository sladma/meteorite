package com.sladma.meteorite.main.list;

import com.sladma.common.mvvm.IView;

/**
 * View interface for {@link MeteoriteListFragment}
 * <p>
 * Created by sladma
 */
interface IMeteoriteListView extends IView {

    /**
     * Called after refresh with success
     */
    void onRefreshFinished(boolean success);

}
