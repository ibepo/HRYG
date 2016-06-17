package com.hryg.ui.buyorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.CommentInfo;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.view.RxView;
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

public class SubmitComment2 extends BaseActivity {


    @Bind(R.id.ivGoods)
    ImageView ivGoods;
    @Bind(R.id.tvNum)
    TextView tvNum;
    @Bind(R.id.tvGold)
    TextView tvGold;
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.radioButton1)
    ImageView radioButton1;
    @Bind(R.id.radioButton2)
    ImageView radioButton2;
    @Bind(R.id.radioButton3)
    ImageView radioButton3;

    String user_id, order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_assess);
        ButterKnife.bind(this);
        getTopBar("发表评论");

        user_id = PathConfig.user_id;
//        order_id = getIntent().getExtras().get("order_id").toString();
        order_id = "22437";

        RxView.clicks(radioButton1).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                resetRB();
                radioButton1.setBackground(getResources().getDrawable(R.drawable.gc_p));
            }
        });

        RxView.clicks(radioButton2).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                resetRB();
                radioButton2.setBackground(getResources().getDrawable(R.drawable.mc_p));
            }
        });

        RxView.clicks(radioButton3).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                resetRB();
                radioButton3.setBackground(getResources().getDrawable(R.drawable.bc_p));
            }
        });

        getData();
    }


    public void resetRB() {
        radioButton1.setBackground(getResources().getDrawable(R.drawable.gc_n));
        radioButton2.setBackground(getResources().getDrawable(R.drawable.gc_n));
        radioButton3.setBackground(getResources().getDrawable(R.drawable.mc_n));

    }


    public void getData() {
        showDialog();
        Network.getOrderApi().getCommentInfo(user_id, order_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }


    Observer<CommentInfo> observer = new Observer<CommentInfo>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(CommentInfo data) {
            dimissDialog();
            if (data.getGoods().size() > 1) {
                Gson gson = new Gson();
                String josn = gson.toJson(data.getGoods());
                Intent intent2 = new Intent(SubmitComment2.this, SubCommentGoodsList.class);
                intent2.putExtra("data", josn);
                intent2.putExtra("order_id", order_id);
                SubmitComment2.this.startActivity(intent2);
                finish();
            }


            tvGoodsName.setText(data.getGoods().get(0).getGoods_name());
            tvNum.setText("数量: " + data.getGoods().get(0).getQuantity());
            tvGold.setText("¥" + data.getGoods().get(0).getGold());
            Glide.with(SubmitComment2.this).load(data.getGoods().get(0).getGoods_image()).into(ivGoods);

        }

    };


    public void submitComment() {
        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", PathConfig.user_id);
        map.put("order_id", order_id);

        Network.getOrderApi().submitComment(map).subscribeOn(Schedulers.io())
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
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean4Rep data) {
            dimissDialog();


        }

    };


    @OnClick(R.id.tvSubmit)
    public void onClick() {
    }
}
