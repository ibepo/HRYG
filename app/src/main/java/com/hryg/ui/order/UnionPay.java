package com.hryg.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.ui.buyorder.BuyOrderList;
import com.kefanbufan.fengtimo.R;

import org.apache.http.util.EncodingUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UnionPay extends BaseActivity {


    @Bind(R.id.ivBack)
    ImageView ivBack;
    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.union_pay);
        ButterKnife.bind(this);

        String user_id = PathConfig.user_id;
        String order_id = getIntent().getExtras().get("order_id").toString();
        String payment_id = "250";


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnionPay.this, BuyOrderList.class);
                UnionPay.this.startActivity(intent);
                finish();

            }
        });

        String url = "http://www.hr1g.net/index_ios.php?app=cashier&act=goto_pay&order_cashier=1";
        String postData = "user_id=" + user_id + "&order_id=" + order_id + "&payment_id=" + payment_id;
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.postUrl(url, EncodingUtils.getBytes(postData, "base64"));

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(UnionPay.this, BuyOrderList.class);
            UnionPay.this.startActivity(intent);
            finish();
        }
        return true;
    }

}
