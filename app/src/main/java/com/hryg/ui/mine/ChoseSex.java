package com.hryg.ui.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hryg.base.BaseActivity;
import com.hryg.model.MessageEvent;
import com.kefanbufan.fengtimo.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoseSex extends BaseActivity {


    @Bind(R.id.ivMan)
    ImageView ivMan;
    @Bind(R.id.rlMan)
    RelativeLayout rlMan;
    @Bind(R.id.ivWomen)
    ImageView ivWomen;
    @Bind(R.id.rlWomen)
    RelativeLayout rlWomen;
    @Bind(R.id.ivSecrect)
    ImageView ivSecrect;
    @Bind(R.id.rlSecrect)
    RelativeLayout rlSecrect;


    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_sex);
        ButterKnife.bind(this);
        getTopBar("选择性别");


        flag = Integer.parseInt(getIntent().getExtras().get("flag").toString());
        reset();

        switch (flag) {

            case 0:
                ivSecrect.setVisibility(View.VISIBLE);
                break;
            case 1:
                ivMan.setVisibility(View.VISIBLE);
                break;
            case 2:
                ivWomen.setVisibility(View.VISIBLE);
                break;

        }


    }


    void reset() {
        ivMan.setVisibility(View.GONE);
        ivWomen.setVisibility(View.GONE);
        ivSecrect.setVisibility(View.GONE);
    }


    @OnClick({R.id.rlMan, R.id.rlWomen, R.id.rlSecrect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlMan:
                EventBus.getDefault().post(new MessageEvent("1"));
                finish();
                break;
            case R.id.rlWomen:
                EventBus.getDefault().post(new MessageEvent("2"));
                finish();
                break;
            case R.id.rlSecrect:
                EventBus.getDefault().post(new MessageEvent("0"));
                finish();
                break;
        }
    }
}
