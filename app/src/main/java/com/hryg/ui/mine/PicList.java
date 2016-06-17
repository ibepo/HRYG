package com.hryg.ui.mine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseActivity;
import com.hryg.base.BitmapUtils;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.PicListBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PicList extends BaseActivity {


    @Bind(R.id.iv01)
    ImageView iv01;
    @Bind(R.id.iv02)
    ImageView iv02;
    @Bind(R.id.iv03)
    ImageView iv03;
    @Bind(R.id.iv04)
    ImageView iv04;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    Context context;
    int flag;
    String id1, id2, id3, id4;
    String img1, img2, img3, img4;

    Map<String, String> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_list);
        ButterKnife.bind(this);
        context = this;
        getTopBar("宣传图片");
        getData();
    }

    public void getData() {
        showDialog();
        Network.getMineApi().getPicList(PathConfig.user_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    Observer<PicListBean> observer2 = new Observer<PicListBean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();

            ToastUtils.showSuperToastAlert(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(PicListBean data) {
            dimissDialog();

            Glide.with(context).load(data.getImg1().getImg1()).into(iv01);
            Glide.with(context).load(data.getImg2().getImg2()).into(iv02);
            Glide.with(context).load(data.getImg3().getImg3()).into(iv03);
            Glide.with(context).load(data.getImg4().getImg4()).into(iv04);

            img1 = data.getImg1().getImg1();
            img2 = data.getImg2().getImg2();
            img3 = data.getImg3().getImg3();
            img4 = data.getImg4().getImg4();

            map.put("1", img1);
            map.put("2", img2);
            map.put("3", img3);
            map.put("4", img4);


            id1 = data.getImg1().getId1();
            id2 = data.getImg2().getId2();
            id3 = data.getImg3().getId3();
            id4 = data.getImg4().getId4();

        }

    };

    @OnClick({R.id.iv01, R.id.iv02, R.id.iv03, R.id.iv04})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv01:
                photo(1);
                break;
            case R.id.iv02:
                photo(2);
                break;
            case R.id.iv03:
                photo(3);
                break;
            case R.id.iv04:
                photo(4);
                break;
        }
    }


    void photo(int flag) {
        this.flag = flag;
        MultiImageSelector.create(this)
                .showCamera(true) // 是否显示相机. 默认为显示
                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single()
                .start(this, 2);
    }


    void submitPic() {

        showDialog();

        File file = BitmapUtils.saveImageToGallery(this, BitmapUtils.getimage(map.get("1")), "temp1");
        RequestBody photo1 = RequestBody.create(MediaType.parse("img/png"), file);

        File file2 = BitmapUtils.saveImageToGallery(this, BitmapUtils.getimage(map.get("2")), "temp2");
        RequestBody photo2 = RequestBody.create(MediaType.parse("img/png"), file2);

        File file3 = BitmapUtils.saveImageToGallery(this, BitmapUtils.getimage(map.get("3")), "temp3");
        RequestBody photo3 = RequestBody.create(MediaType.parse("img/png"), file3);

        File file4 = BitmapUtils.saveImageToGallery(this, BitmapUtils.getimage(map.get("4")), "tem4");
        RequestBody photo4 = RequestBody.create(MediaType.parse("img/png"), file4);

        Map<String, RequestBody> map = new HashMap<>();
        map.put("user_id", RequestBody.create(null, PathConfig.user_id));
        map.put("id1", RequestBody.create(null, id1));
        map.put("id2", RequestBody.create(null, id2));
        map.put("id3", RequestBody.create(null, id3));
        map.put("id4", RequestBody.create(null, id4));
        map.put("img1\"; filename=\"icon1.png", photo1);
        map.put("img2\"; filename=\"icon2.png", photo2);
        map.put("img3\"; filename=\"icon3.png", photo3);
        map.put("img4\"; filename=\"icon4.png", photo4);


        Network.getMineApi().submitPic(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<ResultBean4Rep> observer = new Observer<ResultBean4Rep>() {
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
        }

    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Log.e("0000000000", path.get(0).toString());
                switch (flag) {
                    case 1:
                        map.put("1", path.get(0));
                        Glide.with(this).load(path.get(0)).into(iv01);
                        break;
                    case 2:
                        map.put("2", path.get(0));
                        Glide.with(this).load(path.get(0)).into(iv02);
                        break;
                    case 3:
                        map.put("3", path.get(0));
                        Glide.with(this).load(path.get(0)).into(iv03);
                        break;
                    case 4:
                        map.put("4", path.get(0));
                        Glide.with(this).load(path.get(0)).into(iv04);
                        break;
                }


            }
        }
    }


    @OnClick(R.id.tvSubmit)
    public void onClick() {
        submitPic();
    }
}
