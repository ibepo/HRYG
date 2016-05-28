package com.hryg.model;

/**
 * Created by kefanbufan on 16/5/18.
 */
public class RegisterParameter {

    private RegisterParameter() {
    }

    private static RegisterParameter instance = new RegisterParameter();

    public static RegisterParameter getInstance() {
        return instance;
    }


    //手机号
    String phone;
    //密码
    String password;
    //重复密码
    String password_confirm;
    //昵称
    String real_name;
    String adress_q;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getAdress_q() {
        return adress_q;
    }

    public void setAdress_q(String adress_q) {
        this.adress_q = adress_q;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
