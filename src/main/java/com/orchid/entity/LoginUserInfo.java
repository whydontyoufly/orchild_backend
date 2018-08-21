package com.orchid.entity;

import com.orchid.tools.BaseDto;
import com.orchid.tools.annotation.Log;

/**
 * Created by ljg on 2018/5/8.
 */
public class LoginUserInfo extends BaseDto{
    @Log(title = "用户名")
    private String accountName;
    @Log(title = "密码")
    private String password;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
