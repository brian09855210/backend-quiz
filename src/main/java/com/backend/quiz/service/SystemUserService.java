package com.backend.quiz.service;

import com.backend.quiz.vo.req.SystemUserEditRequest;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public interface SystemUserService {
    Object saveEdit(SystemUserEditRequest systemUserEditRequest);
}
