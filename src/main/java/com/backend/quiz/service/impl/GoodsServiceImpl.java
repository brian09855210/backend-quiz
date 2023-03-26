package com.backend.quiz.service.impl;

import com.backend.quiz.entity.GoodsEntity;
import com.backend.quiz.repository.GoodsRepository;
import com.backend.quiz.service.GoodsService;
import com.backend.quiz.vo.req.GoodsEditRequest;
import com.backend.quiz.vo.resp.GoodsEditResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/25
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<GoodsEditResponse> findAll() {
        List<GoodsEntity> list = goodsRepository.findAll();
        List<GoodsEditResponse> result = new ArrayList<>();
        for (GoodsEntity data : list) {
            GoodsEditResponse resultData = new GoodsEditResponse();
            resultData.setId(String.valueOf(data.getId()));
            resultData.setGoods_name(data.getName());
            result.add(resultData);
        }
        return result;
    }

    @Override
    public GoodsEditResponse findById(String id) {
        Optional<GoodsEntity> goodsEntityOpt = goodsRepository.findById(UUID.fromString(id));
        GoodsEditResponse result = new GoodsEditResponse();
        if (goodsEntityOpt.isPresent()) {
            GoodsEntity entity = goodsEntityOpt.get();
            result.setId(String.valueOf(entity.getId()));
            result.setGoods_name(entity.getName());
        }
        return result;
    }

    @Override
    public Object save(GoodsEditRequest goodsEditRequest) {
        GoodsEntity goodsInfo = goodsRepository.findByName(goodsEditRequest.getGoods_name());
        GoodsEditResponse response = new GoodsEditResponse();
        if (goodsInfo == null) {
            GoodsEntity goodsEntity = new GoodsEntity();
            goodsEntity.setName(goodsEditRequest.getGoods_name());
            goodsRepository.save(goodsEntity);

            // response content
            response.setId(String.valueOf(goodsEntity.getId()));
            response.setGoods_name(goodsEditRequest.getGoods_name());
        }
        return new Gson().toJson(response);
    }

    @Override
    public Object edit(GoodsEditRequest goodsEditRequest, String id) {
        Optional<GoodsEntity> goodsEntityOpt = goodsRepository.findById(UUID.fromString(id));
        GoodsEditResponse response = new GoodsEditResponse();
        if (goodsEntityOpt.isPresent()) {
            GoodsEntity goodsEntity = goodsEntityOpt.get();
            goodsEntity.setName(goodsEditRequest.getGoods_name());
            goodsRepository.save(goodsEntity);

            // response content
            response.setId(id);
            response.setGoods_name(goodsEditRequest.getGoods_name());
        }
        return new Gson().toJson(response);
    }

    @Override
    public Object delete(String id) {
        goodsRepository.deleteById(UUID.fromString(id));
        return null;
    }
}
