package com.hryg.ui.mine;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.MessageEvent;
import com.hryg.model.MineDetailBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PersonalInfo extends BaseActivity {


    @Bind(R.id.ivTouxiang)
    CircleImageView ivTouxiang;
    @Bind(R.id.tvName)
    EditText tvName;
    @Bind(R.id.tvRealName)
    EditText tvRealName;
    @Bind(R.id.tvSex)
    TextView tvSex;
    @Bind(R.id.tvDate)
    TextView tvDate;
    @Bind(R.id.tvSave)
    TextView tvSave;
    @Bind(R.id.rlSex)
    RelativeLayout rlSex;
    @Bind(R.id.rlDate)
    RelativeLayout rlDate;
    String flag;
    String real_name, gender, birthday;
    String  portrait="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        getTopBar("我的账号");

        RxTextView.textChanges(tvRealName).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                real_name = charSequence.toString();

            }
        });

        RxTextView.textChanges(tvDate).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                birthday = charSequence.toString();

            }
        });


        getData();

    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        gender = event.message;
        switch (Integer.parseInt(event.message)) {
            case 0:
                tvSex.setText("保密");
                break;
            case 1:
                tvSex.setText("男");
                break;
            case 2:
                tvSex.setText("女");
                break;
        }
    }

    public void modify() {
        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("real_name", real_name);
        map.put("birthday", birthday);
        map.put("portrait", portrait);
        map.put("gender", gender);
        Network.getMineApi().modifyDetail(map).subscribeOn(Schedulers.io())
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

    void getData() {
        showDialog();
        Network.getMineApi().getMineDetail("profile", PathConfig.user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<MineDetailBean> observer = new Observer<MineDetailBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(PersonalInfo.this, "连接服务器失败");
        }

        @Override
        public void onNext(MineDetailBean data) {
            dimissDialog();
            if (data.getCode() == 1) {
                Glide.with(PersonalInfo.this).load(data.getData().getPortrait()).into(ivTouxiang);
                tvName.setText(data.getData().getUser_name());
                tvRealName.setText(data.getData().getReal_name());
                tvDate.setText(data.getData().getBirthday());

                flag = data.getData().getGender();
                gender=data.getData().getGender();
                switch (Integer.parseInt(data.getData().getGender())) {
                    case 0:
                        tvSex.setText("保密");
                        break;
                    case 1:
                        tvSex.setText("男");
                        break;
                    case 2:
                        tvSex.setText("女");
                        break;
                }


            } else {
                ToastUtils.showSuperToastAlertGreen(PersonalInfo.this, data.getDescription());
            }


        }
    };


    private DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            tvDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);

        }
    };

    @OnClick({R.id.rlSex, R.id.rlDate, R.id.tvSave})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tvSave:
                modify();
                break;
            case R.id.rlSex:
                Intent intent = new Intent(this, ChoseSex.class);
                intent.putExtra("flag", flag);
                this.startActivity(intent);

                break;
            case R.id.rlDate:

                final Calendar objTime = Calendar.getInstance();
                int iYear = objTime.get(Calendar.YEAR);
                int iMonth = objTime.get(Calendar.MONTH);
                int iDay = objTime.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(PersonalInfo.this, DatePickerListener, iYear, iMonth, iDay).show();
                break;
        }
    }
}
