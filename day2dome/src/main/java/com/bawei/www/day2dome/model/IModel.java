package com.bawei.www.day2dome.model;

import android.util.Log;

import com.bawei.www.day2dome.bean.ShopBean;
import com.bawei.www.day2dome.callback.MyCallback;
import com.bawei.www.day2dome.httputil.RetrofitHttp;
import com.google.gson.Gson;

public class IModel implements IM {
    @Override
    public void setResponse(String url, final MyCallback myCallback) {

        RetrofitHttp.getInstense().get(url).result(new RetrofitHttp.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object obj = new Gson().fromJson(data,ShopBean.class);
                if(myCallback!=null){
                    myCallback.setData(obj);
                }
            }
            @Override
            public void onFail(String error) {
                Log.d("WS",error);
            }
        });

    }
}
