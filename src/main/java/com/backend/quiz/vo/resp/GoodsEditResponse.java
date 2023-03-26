package com.backend.quiz.vo.resp;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/25
 */
public class GoodsEditResponse {

    private String id;

    private String goods_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
