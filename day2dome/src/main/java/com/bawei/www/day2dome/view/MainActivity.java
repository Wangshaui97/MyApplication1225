package com.bawei.www.day2dome.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bawei.www.day2dome.Apis;
import com.bawei.www.day2dome.R;
import com.bawei.www.day2dome.adpter.RecycviewAdpter;
import com.bawei.www.day2dome.bean.ShopBean;
import com.bawei.www.day2dome.presonter.IPersonter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.qiehuan)
    ImageView qiehuan;
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.putin)
    EditText putin;
    @BindView(R.id.rview)
    RecyclerView rview;
    private IPersonter iPersonter;
    private RecycviewAdpter rd;
    private Boolean aBoolean= true;
    private List<ShopBean.DataBean> shopBeanData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        iPersonter = new IPersonter(this);

        initData();
        initView();

    }

    private void initView() {
        rd = new RecycviewAdpter(this);
        rview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rview.setAdapter(rd);
    }

    private void initData() {
        iPersonter.setRequest(Apis.SHOP_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPersonter != null) {
            iPersonter.onDatech();
        }
    }

    @OnClick({R.id.qiehuan, R.id.sousuo, R.id.back, R.id.putin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qiehuan:
                if(aBoolean==true){
                    rd = new RecycviewAdpter(this);
                    rview.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    rview.setAdapter(rd);
                    rd.setGData(shopBeanData,1);
                    aBoolean=false;
                }else {
                    rd = new RecycviewAdpter(this);
                    rview.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                    rview.setAdapter(rd);
                    rd.setLData(shopBeanData,0);
                    aBoolean=true;
                }
                break;
            case R.id.sousuo:
                break;
            case R.id.back:
                break;
            case R.id.putin:
                break;
        }
    }

    @Override
    public void setSuccess(Object data) {
        ShopBean shopBean = (ShopBean) data;
        shopBeanData = shopBean.getData();
        rd.setData(shopBeanData);
    }
}
