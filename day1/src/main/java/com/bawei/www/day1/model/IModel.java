package com.bawei.www.day1.model;

import com.bawei.www.day1.bean.GoodsBean;
import com.bawei.www.day1.frotrfithttp.RetrofitHttp;
import com.bawei.www.day1.mycallback.MyCallback;
import com.bawei.www.day1.okhttputil.Httputil;
import com.bawei.www.day1.okhttputil.ICallBack;
import com.google.gson.Gson;

public class IModel implements IM {
    @Override
    public void setResponse(String url, final MyCallback myCallback) {

        RetrofitHttp.getInstense().get(url).result(new RetrofitHttp.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object obj = new Gson().fromJson(data,GoodsBean.class);
                    if(myCallback!=null){
                        myCallback.setData(obj);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFail(String error) {

            }
        });



        //OKHttp 获取网络

//        Httputil.getInstance().getEnqueue(url, GoodsBean.class, new ICallBack() {
//            @Override
//            public void success(Object obj) {
//                myCallback.setData(obj);
//            }
//
//            @Override
//            public void failed(Exception e) {
//                Log.e("WS","  "+e);
//            }
//        });
    }
}
