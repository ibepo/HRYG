package com.hryg.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hryg.adapter.SearchGridListAdapter;
import com.hryg.adapter.SearchListListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.SearchData;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.view.RxView;
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


public class Search extends BaseActivity {

    String keyword="";
    String cate_id = "";
    String order;
    String page;
    boolean flag = true;
    SearchGridListAdapter adapter = new SearchGridListAdapter();
    SearchListListAdapter adapter2 = new SearchListListAdapter();

    @Bind(R.id.etSearch)
    EditText etSearch;
    @Bind(R.id.tvSearch)
    TextView tvSearch;
    @Bind(R.id.gridRv)
    RecyclerView gridRv;

    @Bind(R.id.ivRcyType)
    ImageView ivRcyType;
    @Bind(R.id.tvLiulan)
    TextView tvLiulan;
    @Bind(R.id.tvXiaoliang)
    TextView tvXiaoliang;
    @Bind(R.id.tvJiage)
    TextView tvJiage;
    @Bind(R.id.tvXinyongdu)
    TextView tvXinyongdu;
    @Bind(R.id.ivBack)
    ImageView ivBack;
    @Bind(R.id.ivBack2)
    ImageView ivBack2;
    @Bind(R.id.tvSearchShow)
    TextView tvSearchShow;
    @Bind(R.id.rlSearch)
    RelativeLayout rlSearch;
    @Bind(R.id.rlShow)
    RelativeLayout rlShow;
    @Bind(R.id.linShow)
    LinearLayout linShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ButterKnife.bind(this);

        initView();
        try {
            if (getIntent().getExtras().get("cate_id") != null) {
                cate_id = getIntent().getExtras().get("cate_id").toString();
            } else {
                cate_id = "";
            }

            getData();
        } catch (Exception e) {
        }





    }


    public void getData() {
        showDialog();

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("keyword", keyword);
        map.put("cate_id", cate_id);
        map.put("order", order);
//        map.put("page", "");

        Network.getSearchApi().postSearch(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void initView() {

        rlSearch.setVisibility(View.VISIBLE);
        rlShow.setVisibility(View.GONE);

        resetColors();
        order = "price";
        tvJiage.setTextColor(getResources().getColor(R.color.red_orange));

        gridRv.setItemAnimator(new DefaultItemAnimator());
        gridRv.setLayoutManager(new GridLayoutManager(this, 2));
        gridRv.setAdapter(adapter);


        RxTextView.textChanges(etSearch).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                tvSearchShow.setText(charSequence.toString());
            }
        });

        RxTextView.textChanges(tvSearchShow).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                keyword = charSequence.toString();
            }
        });

        //tag 切换
        RxView.clicks(tvJiage).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                order = "price";
                resetColors();
                tvJiage.setTextColor(getResources().getColor(R.color.red_orange));
                getData();
            }
        });
        RxView.clicks(tvXiaoliang).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                order = "sales";
                resetColors();
                tvXiaoliang.setTextColor(getResources().getColor(R.color.red_orange));
                getData();
            }
        });
        RxView.clicks(tvXinyongdu).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                order = "credit_value";
                resetColors();
                tvXinyongdu.setTextColor(getResources().getColor(R.color.red_orange));
                getData();
            }
        });
        RxView.clicks(tvLiulan).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                order = "views";
                resetColors();
                tvLiulan.setTextColor(getResources().getColor(R.color.red_orange));
                getData();
            }
        });


        RxView.clicks(ivRcyType).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (flag) {
                    ivRcyType.setBackgroundDrawable(getResources().getDrawable(R.drawable.search_lv));
                    gridRv.setAdapter(adapter2);
                    gridRv.setLayoutManager(new LinearLayoutManager(Search.this));

                } else {
                    ivRcyType.setBackgroundDrawable(getResources().getDrawable(R.drawable.search_gv));
                    gridRv.setLayoutManager(new GridLayoutManager(Search.this, 2));
                    gridRv.setAdapter(adapter);
                }
                flag = !flag;
            }
        });


    }


    void resetColors() {
        tvJiage.setTextColor(getResources().getColor(R.color.dark_gray));
        tvXinyongdu.setTextColor(getResources().getColor(R.color.dark_gray));
        tvLiulan.setTextColor(getResources().getColor(R.color.dark_gray));
        tvXiaoliang.setTextColor(getResources().getColor(R.color.dark_gray));


    }

    Observer<SearchData> observer = new Observer<SearchData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(SearchData searchData) {

            dimissDialog();
            if (searchData.getCode() == 1 && searchData.getGoods().size() > 0) {
                adapter.setImages(searchData.getGoods(), Search.this);
                adapter2.setImages(searchData.getGoods(), Search.this);
                rlShow.setVisibility(View.VISIBLE);
                rlSearch.setVisibility(View.GONE);
            } else {
                adapter.setImages(searchData.getGoods(), Search.this);
                adapter2.setImages(searchData.getGoods(), Search.this);
                ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "没有收到相关商品");
            }


        }
    };


    @OnClick({R.id.ivRcyType, R.id.linShow, R.id.tvSearch, R.id.ivBack, R.id.ivBack2, R.id.tvLiulan, R.id.tvXiaoliang, R.id.tvJiage, R.id.tvXinyongdu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                getData();
                break;
            case R.id.ivBack:
                finish();
                break;

            case R.id.ivBack2 :
                finish();
                break;

            case R.id.linShow:
                rlSearch.setVisibility(View.VISIBLE);
                rlShow.setVisibility(View.GONE);
                etSearch.setFocusable(true);
                etSearch.setFocusableInTouchMode(true);
                etSearch.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;

        }
    }


}
