package com.backend.quiz.vo.req;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public class SystemUserLoginRequest {

    private String account;

    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
