package com.hryg.ui.mine;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hryg.base.BaseActivity;
import com.hryg.base.MyTextUtils;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
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

public class ApplyBusiness extends BaseActivity {


    String red_gold;

    @Bind(R.id.etID)
    EditText etID;
    @Bind(R.id.tvRecharge)
    TextView tvRecharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_number);
        ButterKnife.bind(this);
        getTopBar("红包充值");

        RxTextView.textChanges(etID).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                red_gold = charSequence.toString();
            }
        });


    }


    public void rechargeByNumber() {

        if (MyTextUtils.isEmpty(red_gold)) {
            ToastUtils.showSuperToastAlert(this, "id 码不能为空");
            return;
        }


        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("red_gold", red_gold);

        Network.getMineApi().rechargeByNumber(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    Observer<ResultBean4Rep> observer2 = new Observer<ResultBean4Rep>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlert(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean4Rep data) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), data.getDescription());
            if (data.getCode() == 1) {
                finish();
            }
        }

    };


    @OnClick(R.id.tvRecharge)
    public void onClick() {
        rechargeByNumber();
    }
}
