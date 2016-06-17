package com.hryg.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hryg.base.BaseActivity;
import com.hryg.base.MyTextUtils;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.ResultBean4Rep;
import com.hryg.model.TypePayData;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UnionPay extends BaseActivity {


    String order_id;
    String store_id;
    String post_money;
    String zf_pass;

    @Bind(R.id.tvOrderNum)
    TextView tvOrderNum;
    @Bind(R.id.tvAmount)
    TextView tvAmount;
    @Bind(R.id.tvGold)
    TextView tvGold;
    @Bind(R.id.tv_zhanghuyue)
    TextView tvZhanghuyue;
    @Bind(R.id.tv_hongbaoyue)
    TextView tvHongbaoyue;
    @Bind(R.id.radioyue)
    ImageView radioyue;
    @Bind(R.id.radioCard)
    ImageView radioCard;
    @Bind(R.id.radioWechat)
    ImageView radioWechat;
    @Bind(R.id.etPwd)
    EditText etPwd;

    @Bind(R.id.linPay)
    LinearLayout linPay;
    @Bind(R.id.linSubmitPay)
    LinearLayout linSubmitPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_type);
        ButterKnife.bind(this);
        getTopBar("选择支付方式");
        order_id = getIntent().getExtras().get("order_id").toString();
        store_id = getIntent().getExtras().get("store_id").toString();


        RxView.clicks(radioyue).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                linPay.setVisibility(View.VISIBLE);
                resetRB();
                radioyue.setBackground(getResources().getDrawable(R.drawable.rb_c));
            }
        });

        RxView.clicks(radioCard).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                linPay.setVisibility(View.GONE);
                resetRB();
                radioCard.setBackground(getResources().getDrawable(R.drawable.rb_c));
            }
        });

        RxView.clicks(radioWechat).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                linPay.setVisibility(View.GONE);
                resetRB();
                radioWechat.setBackground(getResources().getDrawable(R.drawable.rb_c));
            }
        });


        RxTextView.textChanges(etPwd).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                zf_pass = charSequence.toString();
            }
        });

        RxView.clicks(linSubmitPay).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                submitRefund();
            }
        });


        getData();

    }


    public void resetRB() {
        radioyue.setBackground(getResources().getDrawable(R.drawable.rb_n));
        radioCard.setBackground(getResources().getDrawable(R.drawable.rb_n));
        radioWechat.setBackground(getResources().getDrawable(R.drawable.rb_n));


    }


    public void submitRefund() {

        if (MyTextUtils.isEmpty(zf_pass)) {
            ToastUtils.showSuperToastAlert(this, "支付密码不能为空");
            return;
        }


        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);
        map.put("post_money", post_money);
        map.put("zf_pass", zf_pass);

        Network.getOrderApi().payByBalance(map).subscribeOn(Schedulers.io())
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


    public void getData() {
        Network.getOrderApi().getTypeData(PathConfig.user_id, order_id, store_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<TypePayData> observer = new Observer<TypePayData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(TypePayData data) {
            tvOrderNum.setText(data.getData().getOrder_sn());
            tvAmount.setText("¥" + data.getData().getOrder_amount());
            tvGold.setText("¥" + data.getData().getGold());
            tvZhanghuyue.setText("账户余额: ¥" + data.getMoney().getMoney());
            tvHongbaoyue.setText("红包余额 ¥" + data.getMoney().getGold());
            post_money = data.getData().getOrder_amount();


        }

    };


}
