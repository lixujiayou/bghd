package com.bghd.express.ui.order;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bghd.express.R;
import com.bghd.express.adapter.GlideImageLoader;
import com.bghd.express.entiy.TellEntity;
import com.bghd.express.ui.mine.tell.TellListActivity;
import com.bghd.express.utils.base.BaseActivity;
import com.bghd.express.utils.tools.StringUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2018/2/5.
 */

public class PlaceOrderActivity extends BaseActivity {
    private List<String> imageList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();
    private TextView tvAcceptAdress;
    private TextView tvSendAdress;
    private Banner banner;

    private Button tvAccpetPerson;
    private Button tvSendPerson;


    //接收 选择的地址id
    //private ArrayList accpetAdressIdList = new ArrayList();
    private String mAccpetAdressId;


    //发送 选择的地址id
    //private ArrayList sendAdressIdList = new ArrayList();
    private String mSendAdressId;


    //收件人
    private TellEntity.DateBean tellAccpet;
    //寄件人
    private TellEntity.DateBean sendAccpet;



    private EditText etOrderPrice;//订单价格
    private EditText etWeight;//物品重量
    /**
     * 收件人
     */
    private EditText etAName;//姓名
    private EditText etAPHone;//电话
    private EditText etAAdressInfo;//详细地址

    /**
     * 寄件人
     */
    private EditText etSName;//姓名
    private EditText etSPHone;//电话
    private EditText etSAdressInfo;//详细地址


    private Button llSave;



    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_order);
    }

    @Override
    public void initViews() {

        tvAcceptAdress = findViewById(R.id.tv_accept_adress);
        tvSendAdress = findViewById(R.id.tv_send_adress);
        tvAccpetPerson = findViewById(R.id.bt_accpet_person);
        tvSendPerson = findViewById(R.id.bt_send_person);

        llSave = findViewById(R.id.ll_meter_save);

        etAName = findViewById(R.id.et_a_name);
        etAPHone = findViewById(R.id.et_a_phone);
        etAAdressInfo = findViewById(R.id.et_a_adressinfo);

        etSName = findViewById(R.id.et_s_name);
        etSPHone = findViewById(R.id.et_s_phone);
        etSAdressInfo = findViewById(R.id.et_s_adressinfo);


        tvAcceptAdress.setOnClickListener(this);
        tvAccpetPerson.setOnClickListener(this);
        tvSendPerson.setOnClickListener(this);
        tvSendAdress.setOnClickListener(this);

        imageList.add("http://bmob-cdn-9637.b0.upaiyun.com/2017/11/13/82486396404ad7dd808ad45414e3a040.png");
        imageList.add("http://bmob-cdn-9637.b0.upaiyun.com/2017/11/13/f4a08ce240b28285805aff5d2a89865a.jpg");
        imageList.add("http://bmob-cdn-9637.b0.upaiyun.com/2017/11/13/54f587bb404ce43880842163818bb812.jpg");

        strList.add("\t\t\t\t一位传道人服侍感悟：反思侍奉");
        strList.add("\t\t\t\t反省自己的服侍");
        strList.add("\t\t\t\t基督徒的生活应该是一种常常默想的生活");
        //   initChenJin();
    }

    @Override
    public void initData() {
        banner = findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置图片集合
        banner.setImages(imageList);
        //设置banner动画效果
        // banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(strList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.tv_accept_adress:
                Intent aIntent = new Intent(PlaceOrderActivity.this, AdressListActivity.class);
                aIntent.putExtra(AdressListActivity.ADRESSID, "0");
                startActivityForResult(aIntent, 0);
                break;
            case R.id.tv_send_adress:
                Intent sIntent = new Intent(PlaceOrderActivity.this, AdressListActivity.class);
                sIntent.putExtra(AdressListActivity.ADRESSID, "0");
                startActivityForResult(sIntent, 1);
                break;
            case R.id.bt_accpet_person:
                Intent paIntent = new Intent(PlaceOrderActivity.this, TellListActivity.class);
                paIntent.putExtra(TellListActivity.TELL_TYPE, TellListActivity.TELL_TYPE_ACCPET);
                paIntent.putExtra(TellListActivity.TELL_STATUS, TellListActivity.TELL_STATUS_SELECT);
                startActivityForResult(paIntent, 2);
                break;
            case R.id.bt_send_person:
                Intent psIntent = new Intent(PlaceOrderActivity.this, TellListActivity.class);
                psIntent.putExtra(TellListActivity.TELL_TYPE, TellListActivity.TELL_TYPE_SEND);
                psIntent.putExtra(TellListActivity.TELL_STATUS, TellListActivity.TELL_STATUS_SELECT);
                startActivityForResult(psIntent, 3);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (banner != null) {
            //banner.stopAutoPlay();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            //收件
            if (requestCode == 0) {
                if (resultCode == AdressListActivity.FINISH_CODE && data != null) {
                    ArrayList<String> nameList = data.getStringArrayListExtra(AdressListActivity.ADRESSNAMELIST);
                    mAccpetAdressId = data.getExtras().getString(AdressListActivity.ADRESSIDLIST);

                    String adressTest = null;
                    for (String str : nameList) {
                        if (StringUtils.isEmpty(adressTest)) {
                            adressTest = str;
                        } else {
                            adressTest = adressTest + "," + str;
                        }
                    }
                    tvAcceptAdress.setText(adressTest);
                }
                //寄件
            } else if (requestCode == 1) {
                if (resultCode == AdressListActivity.FINISH_CODE && data != null) {
                    ArrayList<String> nameList = data.getStringArrayListExtra(AdressListActivity.ADRESSNAMELIST);
                    mSendAdressId = data.getExtras().getString(AdressListActivity.ADRESSIDLIST);
                    String adressTest = null;
                    for (String str : nameList) {
                        if (StringUtils.isEmpty(adressTest)) {
                            adressTest = str;
                        } else {
                            adressTest = adressTest + "," + str;
                        }
                    }
                    tvSendAdress.setText(adressTest);

                }
                //收件人
            }else if(requestCode == 2){
                if(resultCode == TellListActivity.FINISH_CODE){

                    tellAccpet = (TellEntity.DateBean) data.getSerializableExtra(TellListActivity.FINISH_TELL);
                    if(!StringUtils.isEmpty(tellAccpet.getTruename())) {
                        etAName.setText(tellAccpet.getTruename());
                    }

                    if(!StringUtils.isEmpty(tellAccpet.getMobile())) {
                        etAPHone.setText(tellAccpet.getMobile());
                    }
                    if(!StringUtils.isEmpty(tellAccpet.getAddress())) {
                        etAAdressInfo.setText(tellAccpet.getAddress());
                    }

                    mAccpetAdressId = tellAccpet.getAddress_id();

                    if(!StringUtils.isEmpty(tellAccpet.getProvince()) && !StringUtils.isEmpty(tellAccpet.getCity()) && !StringUtils.isEmpty(tellAccpet.getDistrict())) {
                        tvAcceptAdress.setText(tellAccpet.getProvince()
                                + tellAccpet.getCity()
                                + tellAccpet.getDistrict());
                    }

                }
                //寄件人
            }else if(requestCode == 3){
                if(resultCode == TellListActivity.FINISH_CODE){
                    sendAccpet = (TellEntity.DateBean) data.getSerializableExtra(TellListActivity.FINISH_TELL);
                    if(!StringUtils.isEmpty(sendAccpet.getTruename())) {
                        etSName.setText(sendAccpet.getTruename());
                    }
                    if(!StringUtils.isEmpty(sendAccpet.getMobile())) {
                        etSPHone.setText(sendAccpet.getMobile());
                    }
                    if(!StringUtils.isEmpty(sendAccpet.getAddress())) {
                        etSAdressInfo.setText(sendAccpet.getAddress());
                    }
                    if(!StringUtils.isEmpty(sendAccpet.getAddress_id())) {
                        mSendAdressId = sendAccpet.getAddress_id();
                    }

                    if(!StringUtils.isEmpty(sendAccpet.getProvince()) && !StringUtils.isEmpty(sendAccpet.getCity()) && !StringUtils.isEmpty(sendAccpet.getDistrict())) {
                        tvSendAdress.setText(sendAccpet.getProvince()
                                + sendAccpet.getCity()
                                + sendAccpet.getDistrict());
                    }

                }
            }
        }
    }
}
