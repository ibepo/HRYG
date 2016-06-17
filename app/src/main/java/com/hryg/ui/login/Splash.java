package com.hryg.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;

import com.hryg.base.BaseActivity;
import com.hryg.ui.MainAct2;
import com.kefanbufan.fengtimo.R;

import butterknife.ButterKnife;


public class Splash extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);



    }


    private void start() {
        new Thread() {
            public void run() {

                try {
                    sleep(500);
                    checkUpdate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Looper.prepare();

            }
        }.start();
    }


    private void isfirst() {

        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo("com.bepo", 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        int currentVersion = info.versionCode;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int lastVersion = prefs.getInt("VERSION_KEY", 0);
        if (currentVersion > lastVersion) {
            // 如果当前版本大于上次版本，该版本属于第一次启动
            // 将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动

            Intent mIntent = new Intent(SplashAct.this, MainAct2.class);
            SplashAct.this.startActivity(mIntent);
            prefs.edit().putInt("VERSION_KEY", currentVersion).commit();
            finish();
        } else {
            Intent mIntent = new Intent(SplashAct.this, HomeAct2.class);
            // Intent mIntent = new Intent(SplashAct.this,
            // ParkDetailMain.class);
            SplashAct.this.startActivity(mIntent);
            finish();
        }
    }

}
