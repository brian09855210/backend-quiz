package com.backend.quiz.vo.resp;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public class SystemUserLoginResponse {

    private String account;

    private String name;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
