package com.laoxu.userdemo.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.laoxu.userdemo.MainActivity;
import com.laoxu.userdemo.R;
import com.laoxu.userdemo.api.UserApi;
import com.laoxu.userdemo.contract.IRegContract;
import com.laoxu.userdemo.model.entity.RegEntity;
import com.laoxu.userdemo.presenter.RegPresenter;
import com.laoxu.userdemo.utils.PhoneFormatCheckUtils;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity implements View.OnClickListener, IRegContract.IView {

    private EditText phoneEt,pwdEt,codeEt;
    private Button regBtn;

    private RegPresenter regPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();
    }

    private void initData() {
        regPresenter = new RegPresenter(this);
    }

    private void initView() {
        phoneEt = findViewById(R.id.et_phone);
        pwdEt = findViewById(R.id.et_pwd);
        codeEt = findViewById(R.id.et_code);
        regBtn = findViewById(R.id.btn_reg);
        regBtn.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg:
                if (TextUtils.isEmpty(phoneEt.getText().toString())){
                    Toast.makeText(this, "您的手机号不能为空～", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(pwdEt.getText().toString())){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();

                    return;
                }
                //正则校验
                if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phoneEt.getText().toString())){
                    Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();

                    return;
                }


                Map<String,String> params = new HashMap<>();
                params.put("phone",phoneEt.getText().toString());
                params.put("pwd",pwdEt.getText().toString());
                regPresenter.reg(UserApi.REG_URL,params);






                break;
        }
    }

    /**
     * 注册成功
     * @param regEntity
     */
    @Override
    public void success(RegEntity regEntity) {
        //判断状态吗是0000的时候
        if (regEntity.status.equals("0000")){

            //跳转到登录页面
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    /**
     * 注册失败
     * @param throwable
     */
    @Override
    public void error(Throwable throwable) {

    }
}
