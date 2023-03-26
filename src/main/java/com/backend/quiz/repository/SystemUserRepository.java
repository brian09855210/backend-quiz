package com.backend.quiz.repository;

import com.backend.quiz.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, UUID> {
    SystemUserEntity findByAccount(String account);
}
