package com.laoxu.userdemo.presenter;

import com.laoxu.userdemo.contract.IRegContract;
import com.laoxu.userdemo.model.RegModel;
import com.laoxu.userdemo.model.entity.RegEntity;

import java.util.HashMap;
import java.util.Map;

public class RegPresenter implements IRegContract.IPresenter {
    private IRegContract.IView iView;
    private RegModel regModel;

    public RegPresenter(IRegContract.IView iView) {
        this.iView = iView;
        regModel = new RegModel();
    }

    @Override
    public void reg(String url, Map<String, String> params) {

        regModel.reg(url, params, new IRegContract.IModel.IModelCallback() {
            @Override
            public void success(RegEntity regEntity) {
                iView.success(regEntity);
            }

            @Override
            public void error(Throwable throwable) {

                iView.error(throwable);
            }
        });
    }
}
