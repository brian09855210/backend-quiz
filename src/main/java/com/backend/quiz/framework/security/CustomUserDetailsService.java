package com.backend.quiz.framework.security;

import com.backend.quiz.entity.SystemUserEntity;
import com.backend.quiz.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUserEntity user = systemUserRepository.findByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("帳號不存在!");
        }
        return new SessionUser(user);
    }
}
