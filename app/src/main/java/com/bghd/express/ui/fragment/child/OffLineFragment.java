package com.bghd.express.ui.fragment.child;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bghd.express.R;
import com.bghd.express.adapter.OrderListAdapter;
import com.bghd.express.core.Constance;
import com.bghd.express.entiy.OrderListEntity;
import com.bghd.express.entiy.eventbean.MainEvent;
import com.bghd.express.model.OrderListModel;
import com.bghd.express.utils.base.BaseFragment;
import com.bghd.express.utils.tools.ToolUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2018/1/23.
 * 线下
 */

public class OffLineFragment extends BaseFragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<OrderListEntity.DataBean> orderList = new ArrayList<>();
    private OrderListModel orderListModel;

    private SwipeRefreshLayout swipeRefreshLayout;



    public OffLineFragment() {
    }

    public static OffLineFragment getInstance() {
        return answerFragmentHolder.instance;
    }

    public static class answerFragmentHolder {
        public static final OffLineFragment instance = new OffLineFragment();
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
        swipeRefreshLayout = mView.findViewById(R.id.swiperefresh);


        orderListAdapter = new OrderListAdapter(R.layout.layout_order,orderList);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(orderListAdapter);


        orderListModel = ViewModelProviders.of(this).get(OrderListModel.class);
        orderListModel.getCurrentData(mContext).observe(this, new Observer<List<OrderListEntity.DataBean>>() {
            @Override
            public void onChanged(@Nullable List<OrderListEntity.DataBean> dataBeans) {
                swipeRefreshLayout.setRefreshing(false);
                if(!ToolUtil.isEmpty(dataBeans)){
                    orderList.clear();
                    orderList.addAll(dataBeans);
                    orderListAdapter.notifyDataSetChanged();
                }

            }
        });

        swipeRefreshLayout.setRefreshing(true);
        orderListModel.loadMyWorkList(mRequestClient, Constance.REQUEST_DOWN_ORDER,1,Constance.ORDER_First_NUM);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListModel.loadMyWorkList(mRequestClient, Constance.REQUEST_DOWN_ORDER,1,Constance.ORDER_First_NUM);
            }
        });

        orderListModel.setOnErroCallback(new OrderListModel.OnErroListener() {
            @Override
            public void onErro() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
