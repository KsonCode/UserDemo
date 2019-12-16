package com.laoxu.userdemo.contract;


import com.laoxu.userdemo.model.entity.RegEntity;

import java.util.HashMap;
import java.util.Map;

public interface IRegContract {


    interface IModel{

        void reg(String url, Map<String,String> params, IModelCallback iModelCallback);
        interface IModelCallback{
            void success(RegEntity regEntity);
            void error(Throwable throwable);
        }

    }
    interface IView{

        void success(RegEntity regEntity);
        void error(Throwable throwable);

    }
    interface IPresenter{

        void reg(String url, Map<String,String> params);

    }

}
