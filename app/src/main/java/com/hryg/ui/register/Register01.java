package com.hryg.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hryg.base.BaseActivity;
import com.hryg.base.MyTextUtils;
import com.hryg.base.ToastUtils;
import com.hryg.model.Auth;
import com.hryg.model.RegisterParameter;
import com.hryg.model.ResultBean;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Register01 extends BaseActivity {


    @Bind(R.id.tvYanzhen)
    TextView tvYanzhen;

    @Bind(R.id.tvGetyanzhen)
    TextView tvGetyanzhen;

    @Bind(R.id.etPwd)
    EditText etPwd;

    @Bind(R.id.etPwdConfirm)
    EditText etPwdConfirm;

    @Bind(R.id.linNextStep)
    LinearLayout linNextStep;

    @Bind(R.id.etPhone)
    EditText etPhone;


    private int recLen = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step_01);
        ButterKnife.bind(this);
        getTopBar("注册1/2");
        initView();

    }


    void initView() {

        RxTextView.textChanges(etPhone).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                RegisterParameter.getInstance().setPhone(charSequence.toString());

            }
        });


        RxTextView.textChanges(etPwd).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                RegisterParameter.getInstance().setPassword(charSequence.toString());

            }
        });

        RxTextView.textChanges(etPwdConfirm).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                RegisterParameter.getInstance().setPassword_confirm(charSequence.toString());

            }
        });

    }


    Observer<ResultBean<Auth>> observer = new Observer<ResultBean<Auth>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean<Auth> resultBean) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), resultBean.getDescription());
        }
    };


    public void getAuthCode(String phone) {


        Network.getRegisterApi().getAuth(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            if (60 - recLen < 59) {
                tvGetyanzhen.setClickable(false);
                recLen--;
                String strTime = "" + recLen;
                tvGetyanzhen.setBackgroundResource(R.color.dove_gray);
                tvGetyanzhen.setText(strTime + "秒");
                handler.postDelayed(this, 1000);
            } else {
                tvGetyanzhen.setText("获取验证码");
                tvGetyanzhen.setBackgroundResource(R.color.red_orange);
                handler.removeCallbacks(this);
                tvGetyanzhen.setClickable(true);

            }
        }
    };


    @OnClick({R.id.tvGetyanzhen, R.id.linNextStep})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvGetyanzhen:


                if (MyTextUtils.isEmpty(RegisterParameter.getInstance().getPhone())) {
                    ToastUtils.showSuperToastComment(Register01.this, "请输入手机号");
                    return;
                }
                handler.postDelayed(runnable, 50);
                recLen = 60;
                handler.sendEmptyMessageDelayed(1, 60 * 1000);
                getAuthCode(RegisterParameter.getInstance().getPhone());
                break;
            case R.id.linNextStep:



                Intent intent = new Intent(this, Register02.class);
                this.startActivity(intent);
                break;
        }
    }
}
