package com.hryg.ui.address;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.hryg.adapter.AddressListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.City;
import com.hryg.model.District;
import com.hryg.model.Provence;
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

public class AddressAdd extends BaseActivity {

    String zipcode;        //邮编
    String consignee;     //收货人姓名
    String phone_tel;     //联系电话
    String region_name;   //邮递区域
    String address;       //详细地址
    String if_show;       //是否默认显示
    String region_qu;       //是否默认显示

    AddressListAdapter adapter = new AddressListAdapter();

    @Bind(R.id.et_consignee)
    EditText etConsignee;
    @Bind(R.id.et_phone_tel)
    EditText etPhoneTel;
    @Bind(R.id.tvdz)
    TextView tvdz;
    @Bind(R.id.spinner01)
    Spinner spinner01;
    @Bind(R.id.spinner02)
    Spinner spinner02;
    @Bind(R.id.spinner03)
    Spinner spinner03;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.et_zipcode)
    EditText etZipcode;
    @Bind(R.id.switch1)
    Switch switch1;
    @Bind(R.id.linAdd)
    LinearLayout linAdd;

    private List<Provence> provences;
    private Provence provence;
    ArrayAdapter<Provence> adapter01;
    ArrayAdapter<City> adapter02;
    ArrayAdapter<District> adapter03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);
        ButterKnife.bind(this);
        getTopBar("新建地址");


        RxTextView.textChanges(etConsignee).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                consignee = charSequence.toString();

            }
        });
        RxTextView.textChanges(etAddress).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                address = charSequence.toString();

            }
        });
        RxTextView.textChanges(etZipcode).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                zipcode = charSequence.toString();

            }
        });
        RxTextView.textChanges(etPhoneTel).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                phone_tel = charSequence.toString();

            }
        });


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    if_show = "1";
                } else {
                    if_show = "0";
                }


            }
        });


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

        spinner01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provence = provences.get(position);
                adapter02 = new ArrayAdapter<City>(AddressAdd.this, android.R.layout.simple_list_item_1,
                        provences.get(position).getCitys());
                spinner02.setAdapter(adapter02);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter03 = new ArrayAdapter<District>(AddressAdd.this, android.R.layout.simple_list_item_1,
                        provence.getCitys().get(position).getDistricts());
                spinner03.setAdapter(adapter03);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                region_name = spinner01.getSelectedItem().toString() + " " + spinner02.getSelectedItem().toString() + " " + spinner03.getSelectedItem().toString();
                region_qu = spinner03.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

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

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("zipcode", zipcode);
        map.put("consignee", consignee);
        map.put("phone_tel", phone_tel);
        map.put("region_name", region_name);
        map.put("address", address);
        map.put("region_qu", region_qu);
        map.put("if_show", if_show);


        Network.getAddressApi().add(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<ResultBean> observer = new Observer<ResultBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean data) {
            dimissDialog();
            if (data.getCode() == 1) {
                ToastUtils.showSuperToastAlertGreen(AddressAdd.this, data.getDescription());
            } else {
                ToastUtils.showSuperToastAlert(AddressAdd.this, data.getDescription());
            }


        }

    };


    @OnClick(R.id.linAdd)
    public void onClick() {

        showDialog();
        getData();
    }
}
