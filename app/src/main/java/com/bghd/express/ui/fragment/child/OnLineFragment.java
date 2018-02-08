package com.bghd.express.ui.fragment.child;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bghd.express.R;
import com.bghd.express.adapter.OrderListAdapter;
import com.bghd.express.entiy.OrderListEntity;
import com.bghd.express.entiy.eventbean.MainEvent;
import com.bghd.express.utils.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2018/1/23.
 * 线上
 */

public class OnLineFragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<OrderListEntity.DataBean> orderList = new ArrayList<>();


    public OnLineFragment() {
    }

    public static OnLineFragment getInstance() {
        return answerFragmentHolder.instance;
    }

    public static class answerFragmentHolder {
        public static final OnLineFragment instance = new OnLineFragment();
    }
    @Override
    public void initTheme() {
    }

    @Override
    public int initCreatView() {
        return R.layout.fragment_online;
    }

    @Override
    public void initViews() {
        recyclerView = mView.findViewById(R.id.recycler);

        orderListAdapter = new OrderListAdapter(R.layout.layout_order,orderList);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(orderListAdapter);
    }

    @Override
    public void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MainEvent event) {

    }

    @Override
    public void onClick(View view) {

    }
}
