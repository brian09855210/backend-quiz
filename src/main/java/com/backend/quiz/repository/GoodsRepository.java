package com.backend.quiz.repository;

import com.backend.quiz.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/25
 */
public interface GoodsRepository extends JpaRepository<GoodsEntity, UUID> {
    GoodsEntity findByName(String name);
}
