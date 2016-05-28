package com.hryg.ui.register;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.hryg.base.BaseActivity;
import com.hryg.base.ToastUtils;
import com.hryg.model.Auth;
import com.hryg.model.City;
import com.hryg.model.District;
import com.hryg.model.Provence;
import com.hryg.model.RegisterParameter;
import com.hryg.model.ResultBean;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Register02 extends BaseActivity {




    @Bind(R.id.spinner01)
    Spinner spinner01;
    @Bind(R.id.spinner02)
    Spinner spinner02;
    @Bind(R.id.spinner03)
    Spinner spinner03;
    @Bind(R.id.linRegister)
    LinearLayout linRegister;
    @Bind(R.id.etRealName)
    EditText etRealName;


    private List<Provence> provences;
    private Provence provence;
    ArrayAdapter<Provence> adapter01;
    ArrayAdapter<City> adapter02;
    ArrayAdapter<District> adapter03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step_02);
        ButterKnife.bind(this);
        getTopBar("注册2/2");
        try {
            provences = getProvinces();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter01 = new ArrayAdapter<Provence>(this, android.R.layout.simple_list_item_1, provences);
        spinner01.setAdapter(adapter01);
        spinner01.setSelection(0, true);

        adapter02 = new ArrayAdapter<City>(this, android.R.layout.simple_list_item_1, provences.get(0).getCitys());
        spinner02.setAdapter(adapter02);
        spinner02.setSelection(0, true);

        adapter03 = new ArrayAdapter<District>(this, android.R.layout.simple_list_item_1, provences.get(0)
                .getCitys().get(0).getDistricts());
        spinner03.setAdapter(adapter03);
        spinner03.setSelection(0, true);

        spinner01.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provence = provences.get(position);
                adapter02 = new ArrayAdapter<City>(Register02.this, android.R.layout.simple_list_item_1,
                        provences.get(position).getCitys());
                spinner02.setAdapter(adapter02);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner02.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter03 = new ArrayAdapter<District>(Register02.this, android.R.layout.simple_list_item_1,
                        provence.getCitys().get(position).getDistricts());
                spinner03.setAdapter(adapter03);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner03.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RegisterParameter.getInstance().setAdress_q(spinner03.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        RxTextView.textChanges(etRealName).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                RegisterParameter.getInstance().setReal_name(charSequence.toString());

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
//            new SweetAlertDialog(Register02.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
//                    .setTitleText("Sweet!")
//                    .setContentText("Here's a custom image.")
//                    .setCustomImage(R.drawable.spiner_jiantou)
//                    .show();
            finish();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), resultBean.getDescription());
        }
    };


    public List<Provence> getProvinces() throws XmlPullParserException, IOException {
        List<Provence> provinces = null;
        Provence province = null;
        List<City> citys = null;
        City city = null;
        List<District> districts = null;
        District district = null;
        Resources resources = getResources();

        InputStream in = resources.openRawResource(R.raw.citys_weather);

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(in, "utf-8");
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    provinces = new ArrayList<Provence>();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if ("p".equals(tagName)) {
                        province = new Provence();
                        citys = new ArrayList<City>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("p_id".equals(attrName))
                                province.setId(attrValue);
                        }
                    }
                    if ("pn".equals(tagName)) {
                        province.setName(parser.nextText());
                    }
                    if ("c".equals(tagName)) {
                        city = new City();
                        districts = new ArrayList<District>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("c_id".equals(attrName))
                                city.setId(attrValue);
                        }
                    }
                    if ("cn".equals(tagName)) {
                        city.setName(parser.nextText());
                    }
                    if ("d".equals(tagName)) {
                        district = new District();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("d_id".equals(attrName))
                                district.setId(attrValue);
                        }
                        district.setName(parser.nextText());
                        districts.add(district);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("c".equals(parser.getName())) {
                        city.setDistricts(districts);
                        citys.add(city);
                    }
                    if ("p".equals(parser.getName())) {
                        province.setCitys(citys);
                        provinces.add(province);
                    }

                    break;

            }
            event = parser.next();

        }
        return provinces;
    }

    @OnClick(R.id.linRegister)
    public void onClick() {


        Map<String, Object> map = new HashMap<>();
        map.put("phone", RegisterParameter.getInstance().getPhone());
        map.put("password", RegisterParameter.getInstance().getPassword());
        map.put("password_confirm", RegisterParameter.getInstance().getPassword_confirm());
        map.put("real_name", RegisterParameter.getInstance().getReal_name());
        map.put("adress_q", RegisterParameter.getInstance().getAdress_q());

        Network.getRegisterApi().postRegister(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }
}
