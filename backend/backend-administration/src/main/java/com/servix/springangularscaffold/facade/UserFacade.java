package com.servix.springangularscaffold.facade;

import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.dto.user.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFacade {
    UserDto findById(Long id);

    Page<UserDto> findAll(Pageable pageable);

    UserDto update(UserUpdateDto userUpdateDto);
}
