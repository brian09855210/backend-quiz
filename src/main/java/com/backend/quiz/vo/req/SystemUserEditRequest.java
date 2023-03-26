package com.backend.quiz.vo.req;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public class SystemUserEditRequest {

    @JsonIgnore()
    private String id;

    private String account;

    private String password;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
