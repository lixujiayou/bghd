package com.bghd.express.utils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bghd.express.core.MallRequest;
import com.bghd.express.utils.converter.ServiceGenerator;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Administrator on 2016/12/8 0008.
 */

public abstract class BaseFragment extends Fragment {

    public View mView;
    public Context mContext;
    private int layoutID;
    public boolean isVisible; //Fragment显示隐藏状态
    public LayoutInflater mInflater;
    public Bundle mSavedInstanceState;
    public MallRequest mRequestClient;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       this.mSavedInstanceState = savedInstanceState;
        mInflater = inflater;
        layoutID = initCreatView();
        mView = inflater.inflate(layoutID,container,false);
        mContext = getActivity();
        EventBus.getDefault().register(this);
        mRequestClient = ServiceGenerator.createService(MallRequest.class);
        initViews();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint() && isVisibleToUser) {
            isVisible = true;
            //initData();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public abstract void initTheme();
    public abstract int initCreatView();
    public abstract void initViews();
    public abstract void initData();

}
