package com.bawei.www.day1dome;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.www.day1dome.adpter.FragementAdpter;
import com.bawei.www.day1dome.bean.BannerBean;
import com.bawei.www.day1dome.okhttputil.Httputil;
import com.bawei.www.day1dome.okhttputil.ICallBack;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.zqj)
    TextView zqj;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vpager)
    ViewPager vpager;
    @BindView(R.id.yuyue)
    TextView yuyue;
    @BindView(R.id.wode)
    TextView wode;
    @BindView(R.id.ll)
    LinearLayout ll;
    private FragementAdpter fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        fd = new FragementAdpter(getSupportFragmentManager(), this);
        vpager.setAdapter(fd);

        tabs.setupWithViewPager(vpager);


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @OnClick({R.id.tabs, R.id.vpager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tabs:
                break;
            case R.id.vpager:
                break;
        }
    }
}
