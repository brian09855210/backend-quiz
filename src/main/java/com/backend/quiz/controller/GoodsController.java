package com.backend.quiz.controller;

import com.backend.quiz.service.GoodsService;
import com.backend.quiz.vo.req.GoodsEditRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/25
 */
@Api(tags = "goods", description = "商品功能")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "全部商品")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 403, message = "權限不足"),
            @ApiResponse(code = 500, message = "系統錯誤")
    })
    @GetMapping
    public @ResponseBody Object findAll() {
        return goodsService.findAll();
    }

    @ApiOperation(value = "使用ID找商品")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 403, message = "權限不足"),
            @ApiResponse(code = 500, message = "系統錯誤")
    })
    @GetMapping("/{id}")
    public @ResponseBody Object findById(@PathVariable("id") String id) {
        return goodsService.findById(id);
    }

    @ApiOperation(value = "增加商品")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 403, message = "權限不足"),
            @ApiResponse(code = 500, message = "系統錯誤")
    })
    @PostMapping("/add")
    public @ResponseBody Object addGoods(@RequestBody GoodsEditRequest goodsEditRequest) {
        return goodsService.save(goodsEditRequest);
    }

    @ApiOperation(value = "編輯商品")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 403, message = "權限不足"),
            @ApiResponse(code = 500, message = "系統錯誤")
    })
    @PutMapping("/{id}")
    public @ResponseBody Object editGoods(@RequestBody GoodsEditRequest goodsEditRequest, @PathVariable("id") String id) {
        return goodsService.edit(goodsEditRequest, id);
    }

    @ApiOperation(value = "刪除商品")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 403, message = "權限不足"),
            @ApiResponse(code = 500, message = "系統錯誤")
    })
    @DeleteMapping("/{id}")
    public @ResponseBody Object deleteGoods(@PathVariable("id") String id) {
        return goodsService.delete(id);
    }
}
