package com.hryg.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;

import com.hryg.base.BaseActivity;
import com.kefanbufan.fengtimo.R;

import butterknife.ButterKnife;


public class Splash extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash);
        ButterKnife.bind(this);
        start();
    }


    private void start() {
        new Thread() {
            public void run() {

                try {
                    sleep(1500);
                    Intent mIntent = new Intent(Splash.this, Login.class);
                    Splash.this.startActivity(mIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Looper.prepare();

            }
        }.start();
    }


}
