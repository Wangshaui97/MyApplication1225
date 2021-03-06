package com.bawei.www.day2dome.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.www.day2dome.R;
import com.bawei.www.day2dome.bean.ShopBean;
import com.bawei.www.day2dome.msgeventbus.MsgEnent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatailActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;
    private List<ShopBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail);
        ButterKnife.bind(this);
        list = new ArrayList<>();

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void event(MsgEnent msgEnent) {
        Toast.makeText(DatailActivity.this,""+msgEnent.getpostion(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
