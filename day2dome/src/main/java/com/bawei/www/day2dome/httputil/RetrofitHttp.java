package com.bawei.www.day2dome.httputil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitHttp<T> {

    private String BASE_URL = "http://www.zhaoapi.cn/product/";

    public static RetrofitHttp mretrofitHttp;

    public static  RetrofitHttp getInstense() {
        if (mretrofitHttp == null) {
            synchronized (RetrofitHttp.class) {
                mretrofitHttp = new RetrofitHttp();
            }
        }
        return mretrofitHttp;
    }

    private BaseApis mBaseApis;


    public RetrofitHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.retryOnConnectionFailure(true);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();

        mBaseApis = retrofit.create(BaseApis.class);
    }

    public RetrofitHttp get(String url) {

        mBaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return mretrofitHttp;
    }

    private Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();
                if(listener!=null){
                    listener.onSuccess(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if(listener!=null){
                    listener.onFail(e.getMessage());
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            if(listener!=null){
                listener.onFail(e.getMessage());
            }
        }

        @Override
        public void onCompleted() {

        }
    };

    private HttpListener listener;

    public void result(HttpListener listener) {
        this.listener = listener;
    }

    public interface HttpListener {
        void onSuccess(String data);

        void onFail(String error);
    }

}
