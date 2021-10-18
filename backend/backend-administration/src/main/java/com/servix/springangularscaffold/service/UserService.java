package com.servix.springangularscaffold.service;

import com.servix.springangularscaffold.dto.user.RegisterDto;
import com.servix.springangularscaffold.dto.user.UserUpdateDto;
import com.servix.springangularscaffold.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByEmail(String email);

    User findById(Long id);

    User register(RegisterDto registerDto);

    Page<User> findAll(Pageable pageable);

    User update(UserUpdateDto userUpdateDto);
}
