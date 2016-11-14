package com.sladma.common.mvvm;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base class for all view-models in MVVM pattern.
 * <p>
 * Created by sladma
 */
public abstract class BaseViewModel<V extends IView> extends BaseObservable implements IViewModel<V> {

    protected V view;

    public final V getView() {
        return view;
    }

    @Override
    @CallSuper
    public void attachView(@NonNull V view, @Nullable Bundle storedState) {
        this.view = view;
        if (storedState != null) {
            restoreState(storedState);
        }
    }

    @Override
    @CallSuper
    public void detachView() {
        this.view = null;
    }

    @Override
    public void saveState(Bundle outState) {
        // do nothing
    }

    @Override
    public void restoreState(Bundle storedState) {
        // do nothing
    }

}