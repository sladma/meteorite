package com.sladma.common.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base class for all Fragments which use MVVM pattern.
 * <p>
 * initAndBind() has to be called in subclass.
 * <p>
 * Created by sladma
 */
public abstract class BaseFragment<D extends ViewDataBinding, VM extends IViewModel> extends Fragment implements IView {

    protected D dataBinding;

    protected VM viewModel;

    protected final View initAndBind(@NonNull VM viewModel, @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @LayoutRes int layoutResId, int variableId, Bundle storedState) {
        this.viewModel = viewModel;
        this.dataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false);
        this.dataBinding.setVariable(variableId, viewModel);
        //noinspection unchecked
        this.viewModel.attachView(this, storedState);
        return this.dataBinding.getRoot();

    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel != null) {
            viewModel.saveState(outState);
        }
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        if (viewModel != null) {
            viewModel.detachView();
        }
        dataBinding = null;
        viewModel = null;
    }

}