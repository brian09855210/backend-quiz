package com.backend.quiz.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/23
 */
@Entity
@Table(name = "system_user")
public class SystemUserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    private String account;

    private String password;

    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
