package com.servix.springangularscaffold.facade.impl;

import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.dto.user.UserUpdateDto;
import com.servix.springangularscaffold.facade.UserFacade;
import com.servix.springangularscaffold.mapper.UserMapper;
import com.servix.springangularscaffold.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacadeImpl(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userService.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserDto update(UserUpdateDto userUpdateDto) {
        return userMapper.toDto(userService.update(userUpdateDto));
    }
}
