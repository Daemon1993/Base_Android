package com.das.god_base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle4.components.support.RxFragment;

import androidx.annotation.Nullable;

public abstract class BaseFragment extends RxFragment {

    public View rootView;
    public LayoutInflater inflater;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;
        if (rootView == null) {
            rootView=onNewCreateView(inflater, container, savedInstanceState);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected abstract View onNewCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

