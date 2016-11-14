package com.sladma.common.mvvm;

import android.support.v7.widget.RecyclerView;

/**
 * Base interface for view-model of adapter.
 * <p>
 * Created by sladma
 */
public interface IBaseAdapterViewModel<V extends IView> extends IViewModel<V> {

    /**
     * Adapter's getter
     */
    RecyclerView.Adapter getAdapter();
}
