package com.hryg.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.LoginUserId;
import com.hryg.model.ResultBean;
import com.hryg.network.Network;
import com.hryg.ui.MainAct;
import com.hryg.ui.register.Register01;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class Login extends BaseActivity {


    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.linLogin)
    LinearLayout linLogin;
    @Bind(R.id.tvRegister)
    TextView tvRegister;
    @Bind(R.id.tvResetPwd)
    TextView tvResetPwd;


    String user_name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        getTopBarNoBack("登录");
        initView();


//test
        PathConfig.user_id = "55434";
        Intent intent = new Intent(Login.this, MainAct.class);
        Login.this.startActivity(intent);
        finish();

    }


    public void initView() {

        RxTextView.textChanges(etUserName).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                user_name = charSequence.toString();
            }
        });

        RxTextView.textChanges(etPassword).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                password = charSequence.toString();
            }
        });


    }


    Observer<ResultBean<LoginUserId>> observer = new Observer<ResultBean<LoginUserId>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean<LoginUserId> resultBean) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), resultBean.getDescription());
            if (resultBean.getCode() == 1) {
//                ToastUtils.showSuperToastAlertGreen(getApplicationContext(),resultBean.getData().user_id);
                PathConfig.user_id = resultBean.getData().user_id;
                Intent intent = new Intent(Login.this, MainAct.class);
                Login.this.startActivity(intent);
                finish();
            }
        }
    };

    @OnClick({R.id.linLogin, R.id.tvRegister, R.id.tvResetPwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linLogin:

                Map<String, Object> map = new HashMap<>();
                map.put("user_name", user_name);
                map.put("password", password);

                Network.getLoginApi().postLogin(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);

                break;

            case R.id.tvRegister:
                Intent intent = new Intent(Login.this, Register01.class);
                Login.this.startActivity(intent);
                break;

            case R.id.tvResetPwd:
                break;
        }
    }
}
