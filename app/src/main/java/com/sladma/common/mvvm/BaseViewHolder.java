package com.sladma.common.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Base class for all ViewHolders in MVVM pattern.
 * <p>
 * initAndBind() has to be called in subclass.
 * <p>
 * Created by sladma
 */
public abstract class BaseViewHolder<D extends ViewDataBinding, VM extends IViewModel> extends RecyclerView.ViewHolder implements IView {

    protected D dataBinding;

    protected VM viewModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected final void initAndBind(@NonNull VM viewModel, @NonNull View view, int variableId) {
        this.viewModel = viewModel;
        this.dataBinding = DataBindingUtil.bind(view);
        this.dataBinding.setVariable(variableId, viewModel);
        //noinspection unchecked
        this.viewModel.attachView(this, null);
    }

    @CallSuper
    public void updateBindings() {
        if (dataBinding != null) {
            dataBinding.executePendingBindings();
        }
    }

    public final VM getViewModel() {
        return viewModel;
    }

}
