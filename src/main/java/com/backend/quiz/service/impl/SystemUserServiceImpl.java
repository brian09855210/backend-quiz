package com.backend.quiz.service.impl;

import com.backend.quiz.entity.SystemUserEntity;
import com.backend.quiz.repository.SystemUserRepository;
import com.backend.quiz.service.SystemUserService;
import com.backend.quiz.vo.req.SystemUserEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object saveEdit(SystemUserEditRequest systemUserEditRequest) {
        SystemUserEntity userInfo = systemUserRepository.findByAccount(systemUserEditRequest.getAccount());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(systemUserEditRequest.getPassword());

        SystemUserEntity systemUserEntity = new SystemUserEntity();
        if (userInfo != null) {
            systemUserEntity.setId(userInfo.getId());
            systemUserEntity.setAccount(userInfo.getAccount());
            systemUserEntity.setPassword(encodePassword);
            systemUserEntity.setName(systemUserEditRequest.getName());
            systemUserRepository.save(systemUserEntity);
        } else {
            systemUserEntity.setAccount(systemUserEditRequest.getAccount());
            systemUserEntity.setPassword(encodePassword);
            systemUserEntity.setName(systemUserEditRequest.getName());
            systemUserRepository.save(systemUserEntity);
        }
        return "SUCCESS";
    }
}
