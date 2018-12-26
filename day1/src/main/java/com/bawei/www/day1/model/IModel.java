package com.bawei.www.day1.model;

import android.util.Log;

import com.bawei.www.day1.bean.GoodsBean;
import com.bawei.www.day1.mycallback.MyCallback;
import com.bawei.www.day1.okhttputil.Httputil;
import com.bawei.www.day1.okhttputil.ICallBack;

public class IModel implements IM {
    @Override
    public void setResponse(String url, final MyCallback myCallback) {
        Httputil.getInstance().getEnqueue(url, GoodsBean.class, new ICallBack() {
            @Override
            public void success(Object obj) {
                myCallback.setData(obj);
            }

            @Override
            public void failed(Exception e) {
                Log.e("WS","  "+e);
            }
        });
    }
}
