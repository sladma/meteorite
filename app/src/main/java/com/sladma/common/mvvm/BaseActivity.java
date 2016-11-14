package com.sladma.common.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Base class for all Activities which use MVVM patter.
 * <p>
 * initAndBind() has to be called in subclass.
 * <p>
 * Created by sladma
 */
public abstract class BaseActivity<D extends ViewDataBinding, VM extends IViewModel> extends AppCompatActivity implements IView {

    protected D dataBinding;

    protected VM viewModel;

    protected final void initAndBind(@NonNull VM viewModel, @LayoutRes int layoutResId, int variableId, @Nullable Bundle storedState) {
        this.viewModel = viewModel;
        this.dataBinding = DataBindingUtil.setContentView(this, layoutResId);
        this.dataBinding.setVariable(variableId, viewModel);
        //noinspection unchecked
        this.viewModel.attachView(this, storedState);
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel != null) {
            viewModel.saveState(outState);
        }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.detachView();
        }
        dataBinding = null;
        viewModel = null;
    }

}