package com.bawei.www.day1.preonster;

import com.bawei.www.day1.model.IModel;
import com.bawei.www.day1.mycallback.MyCallback;
import com.bawei.www.day1.view.IView;

public class IPresonter implements IP {
    private IView iView;
    private IModel iModel;

    public IPresonter(IView iView) {
        this.iView = iView;
        iModel = new IModel();
    }

    @Override
    public void setRequest(String url) {
        iModel.setResponse(url, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setSuccess(data);
            }
        });
    }


    //防止内存泄露
    public void onDistouch() {
        if (iView != null) {
            iView = null;
        }
        if (iModel != null) {
            iModel = null;
        }
    }
}
