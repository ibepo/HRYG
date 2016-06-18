package com.hryg.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseFragment;
import com.hryg.base.PathConfig;
import com.hryg.base.SharedPreferencesUtils;
import com.hryg.base.ToastUtils;
import com.hryg.model.MineData;
import com.hryg.network.Network;
import com.hryg.ui.address.AddressList;
import com.hryg.ui.buyorder.BuyOrderList;
import com.hryg.ui.buyorder.ToChangeOrderList;
import com.hryg.ui.buyorder.ToCommentOrderList;
import com.hryg.ui.buyorder.ToPayOrderList;
import com.hryg.ui.buyorder.ToReceiptOrderList;
import com.hryg.ui.buyorder.ToSendOrderList;
import com.hryg.ui.login.Login;
import com.hryg.ui.mine.ApplyBusiness;
import com.hryg.ui.mine.DownLineList;
import com.hryg.ui.mine.PersonalInfo;
import com.hryg.ui.mine.PicList;
import com.hryg.ui.mine.RechargeByNumber;
import com.hryg.ui.mine.ReviewList;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MineFragment extends BaseFragment {


    @Bind(R.id.linAddress)
    LinearLayout linAddress;
    @Bind(R.id.linApplyBusiness)
    LinearLayout linApplyBusiness;
    @Bind(R.id.linAllList)
    LinearLayout linAllList;
    @Bind(R.id.linToPay)
    LinearLayout linToPay;
    @Bind(R.id.linToSend)
    LinearLayout linToSend;
    @Bind(R.id.linTOReceipt)
    LinearLayout linTOReceipt;
    @Bind(R.id.linToComment)
    LinearLayout linToComment;
    @Bind(R.id.linToChange)
    LinearLayout linToChange;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvYuE)
    TextView tvYuE;
    @Bind(R.id.tvHongBao)
    TextView tvHongBao;
    @Bind(R.id.linRecharge)
    LinearLayout linRecharge;
    @Bind(R.id.linPersonalInfo)
    LinearLayout linPersonalInfo;
    @Bind(R.id.linPic)
    LinearLayout linPic;
    @Bind(R.id.linReview)
    LinearLayout linReview;
    @Bind(R.id.ivTouxiang)
    CircleImageView ivTouxiang;
    @Bind(R.id.linID)
    LinearLayout linID;
    @Bind(R.id.linSetting)
    LinearLayout linSetting;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_center, null, false);
        getData();
        ButterKnife.bind(this, view);
        return view;
    }


    void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        Network.getMineApi().getMineData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<MineData> observer = new Observer<MineData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getContext(), "连接服务器失败");
        }

        @Override
        public void onNext(MineData data) {
            tvName.setText(data.getData().getReal_name());
            tvYuE.setText("¥" + data.getData().getMoney());
            tvHongBao.setText("¥" + data.getData().getGold());
            Glide.with(getContext()).load(data.getData().getPortrait()).into(ivTouxiang);


            linApplyBusiness.setVisibility(View.GONE);//申请商家
            linPic.setVisibility(View.GONE);//宣传图片
            linReview.setVisibility(View.GONE);//商家审核
            linID.setVisibility(View.GONE);//分配ID


            switch (Integer.parseInt(data.getData().getPuid().toString())) {

                case 0:
                    linApplyBusiness.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    linReview.setVisibility(View.VISIBLE);
                    linID.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    linPic.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    break;


            }


        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ivTouxiang, R.id.linSetting, R.id.linID, R.id.linReview, R.id.linPic, R.id.linRecharge, R.id.linPersonalInfo, R.id.linApplyBusiness, R.id.linAddress, R.id.linAllList, R.id.linToPay, R.id.linToSend, R.id.linTOReceipt, R.id.linToComment, R.id.linToChange})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linAllList:
                Intent intent = new Intent(getActivity(), BuyOrderList.class);
                getActivity().startActivity(intent);
                break;

            case R.id.linToPay:
                Intent intent2 = new Intent(getActivity(), ToPayOrderList.class);
                getActivity().startActivity(intent2);
                break;

            case R.id.linAddress:
                Intent intent3 = new Intent(getActivity(), AddressList.class);
                getActivity().startActivity(intent3);
                break;

            case R.id.linToSend:
                Intent intent4 = new Intent(getActivity(), ToSendOrderList.class);
                getActivity().startActivity(intent4);
                break;

            case R.id.linTOReceipt:
                Intent intent5 = new Intent(getActivity(), ToReceiptOrderList.class);
                getActivity().startActivity(intent5);
                break;

            case R.id.linToComment:
                Intent intent6 = new Intent(getActivity(), ToCommentOrderList.class);
                getActivity().startActivity(intent6);
                break;

            case R.id.linToChange:
                Intent intent7 = new Intent(getActivity(), ToChangeOrderList.class);
                getActivity().startActivity(intent7);
                break;

            case R.id.linRecharge:
                Intent intent8 = new Intent(getActivity(), RechargeByNumber.class);
                getActivity().startActivity(intent8);
                break;

            case R.id.linApplyBusiness:
                Intent intent9 = new Intent(getActivity(), ApplyBusiness.class);
                getActivity().startActivity(intent9);
                break;
            case R.id.linPersonalInfo:
                Intent intent10 = new Intent(getActivity(), PersonalInfo.class);
                getActivity().startActivity(intent10);
                break;

            case R.id.linPic:
                Intent intent11 = new Intent(getActivity(), PicList.class);
                getActivity().startActivity(intent11);
                break;

            case R.id.linReview:
                Intent intent12 = new Intent(getActivity(), ReviewList.class);
                getActivity().startActivity(intent12);
                break;

            case R.id.linID:
                Intent intent13 = new Intent(getActivity(), DownLineList.class);
                getActivity().startActivity(intent13);
                break;


            case R.id.linSetting:
                new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("退出登录?")
                        .showContentText(false)
                        .setConfirmText("是的")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                SharedPreferencesUtils.setParam(getContext(), "user_id", "");
                                Intent intent14 = new Intent(getActivity(), Login.class);
                                getActivity().startActivity(intent14);
                                getActivity().finish();

                            }
                        })
                        .setCancelText("取消")
                        .setCancelClickListener(null)
                        .show();


                break;


        }
    }

}