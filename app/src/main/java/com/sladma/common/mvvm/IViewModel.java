package com.sladma.common.mvvm;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base interface for all view-models in MVVM pattern.
 * <p>
 * Created by sladma
 */
public interface IViewModel<V extends IView> extends Observable {

    /**
     * Called when view is attached
     */
    void attachView(@NonNull V view, @Nullable Bundle savedInstanceState);

    /**
     * Called when view is detached
     */
    void detachView();

    /**
     * You can store state of view-model
     */
    void saveState(Bundle outState);

    /**
     * You can restore state of view-model
     */
    void restoreState(Bundle storedState);

}
