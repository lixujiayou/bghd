package com.bghd.express.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bghd.express.R;
import com.bghd.express.adapter.BaseFragmentAdapterTableLayout;
import com.bghd.express.entiy.eventbean.MainEvent;
import com.bghd.express.ui.fragment.child.OffLineFragment;
import com.bghd.express.ui.fragment.child.OnLineFragment;
import com.bghd.express.ui.order.PlaceOrderActivity;
import com.bghd.express.ui.order.SearchOrderActivity;
import com.bghd.express.utils.base.BaseFragment;
import com.cazaea.sweetalert.SweetAlertDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lixu on 2018/1/23.
 */

public class OrderFragment extends BaseFragment implements View.OnClickListener {
    private List<String> strMessageTag = Arrays.asList("线上", "线下");
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar mToolbar;
    private FragmentManager myFM;

    private List<Fragment> list_fragmet = new ArrayList<>();


    private FloatingActionButton floatingActionButton;

    public OrderFragment() {
    }

    public static OrderFragment getInstance() {
        return answerFragmentHolder.instance;
    }

    public static class answerFragmentHolder {
        public static final OrderFragment instance = new OrderFragment();
    }

    @Override
    public void initTheme() {
    }

    @Override
    public int initCreatView() {
        return R.layout.fragment_list;
    }

    @Override
    public void initViews() {
        viewPager = mView.findViewById(R.id.activity_work_main_vp);
        mToolbar = mView.findViewById(R.id.toolbar);
        tabLayout = mView.findViewById(R.id.tabs);
        floatingActionButton = mView.findViewById(R.id.floatingActionButton);


        myFM = getFragmentManager();
        list_fragmet.add(OnLineFragment.getInstance());
        list_fragmet.add(OffLineFragment.getInstance());

        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setText(strMessageTag.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(strMessageTag.get(1)));

        viewPager.setAdapter(new BaseFragmentAdapterTableLayout(strMessageTag, list_fragmet, myFM));

        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(this);


        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Intent sIntent = new Intent(mContext, SearchOrderActivity.class);
                        startActivity(sIntent);
                        break;
                }
                return true;
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
        switch (view.getId()) {
            case R.id.floatingActionButton:
                selectOrderNum();
                break;
        }
    }


    private void selectOrderNum() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //  builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("请选择新增时的'订单号获取方式'");
        //    指定下拉列表的显示数据
        final String[] cities = {"自动匹配", "指定扫描"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                    switch (which){
                        case 0:
                            Intent gIntent = new Intent(mContext, PlaceOrderActivity.class);
                            startActivityForResult(gIntent, 1);
                            break;
                        case 1:
                            Intent aIntent = new Intent(mContext, PlaceOrderActivity.class);
                            startActivityForResult(aIntent, 1);
                            break;
                    }

            }
        });
        builder.show();


        /*new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setContentText("请选择新增时的'订单号获取方式'.")
                .setConfirmText("自动匹配")
                .setCancelText("指定扫描")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        Intent gIntent = new Intent(mContext, PlaceOrderActivity.class);
                        startActivityForResult(gIntent, 1);
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        Intent gIntent = new Intent(mContext, PlaceOrderActivity.class);
                        startActivityForResult(gIntent, 1);
                    }
                })
                .show();*/


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
    }
}
