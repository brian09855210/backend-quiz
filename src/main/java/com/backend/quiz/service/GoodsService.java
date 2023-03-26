package com.backend.quiz.service;

import com.backend.quiz.vo.req.GoodsEditRequest;
import com.backend.quiz.vo.resp.GoodsEditResponse;
import java.util.List;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/25
 */
public interface GoodsService {
    List<GoodsEditResponse> findAll();
    GoodsEditResponse findById(String id);
    Object save(GoodsEditRequest goodsEditRequest);
    Object edit(GoodsEditRequest goodsEditRequest, String id);
    Object delete(String id);
}
