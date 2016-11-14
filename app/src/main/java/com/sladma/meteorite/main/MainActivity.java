package com.sladma.meteorite.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.android.databinding.library.baseAdapters.BR;
import com.sladma.common.mvvm.BaseActivity;
import com.sladma.common.mvvm.IView;
import com.sladma.common.mvvm.IViewModel;
import com.sladma.meteorite.R;
import com.sladma.meteorite.databinding.ActivityMainBinding;

/**
 * Entry point to app and it shows fragment with list
 * <p>
 * Created by sladma
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, IViewModel> implements IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAndBind(new MainViewModel(), R.layout.activity_main, BR.viewModel, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(dataBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
    }

}