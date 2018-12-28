package com.bawei.www.day2dome.presonter;

import com.bawei.www.day2dome.callback.MyCallback;
import com.bawei.www.day2dome.model.IModel;
import com.bawei.www.day2dome.view.IView;

public class IPersonter implements IP {

    IView iView;
    IModel iModel;


    public IPersonter(IView iView) {
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

    public void onDatech() {
        if (iView != null) {
            iView = null;
        }
        if (iModel != null) {
            iModel = null;
        }
    }
}
