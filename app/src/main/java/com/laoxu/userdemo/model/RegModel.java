package com.laoxu.userdemo.model;

import com.google.gson.Gson;
import com.laoxu.userdemo.contract.IRegContract;
import com.laoxu.userdemo.model.entity.RegEntity;
import com.laoxu.userdemo.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Map;

public class RegModel implements IRegContract.IModel {
    @Override
    public void reg(String url, Map<String, String> params, final IModelCallback iModelCallback) {

        VolleyUtils.getInstance().doPost(url, params, new VolleyUtils.VolleyCallback() {
            @Override
            public void success(String response) {

                RegEntity regEntity = new Gson().fromJson(response,RegEntity.class);
                iModelCallback.success(regEntity);
            }

            @Override
            public void failure(Throwable error) {

                iModelCallback.error(error);

            }
        });
    }
}
