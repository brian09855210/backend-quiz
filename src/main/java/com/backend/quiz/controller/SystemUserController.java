package com.backend.quiz.controller;

import com.backend.quiz.service.SystemUserService;
import com.backend.quiz.vo.req.SystemUserEditRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
@Api(tags = "system_user", description = "使用者帳號功能")
@RestController
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @ApiOperation(value = "增加使用者帳號")
    @PostMapping("/user/register")
    public @ResponseBody Object userRegister(@RequestBody SystemUserEditRequest systemUserEditRequest) {
        return systemUserService.saveEdit(systemUserEditRequest);
    }
}
