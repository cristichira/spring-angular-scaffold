package com.servix.springangularscaffold.service.impl;

import com.servix.springangularscaffold.dto.user.RegisterDto;
import com.servix.springangularscaffold.dto.user.UserUpdateDto;
import com.servix.springangularscaffold.entity.User;
import com.servix.springangularscaffold.exception.NotFoundException;
import com.servix.springangularscaffold.mapper.UserMapper;
import com.servix.springangularscaffold.repository.UserRepository;
import com.servix.springangularscaffold.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseUrlNameServiceImpl<User> implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(final Long id) {
        return loadById(id);
    }

    @Override
    public User register(final RegisterDto registerDto) {
        final String urlName = urlNameStrategyWithDelimiter(".", registerDto.getFirstName(), registerDto.getLastName());
        final User user = userMapper.toEntityRegister(registerDto, urlName);
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User update(UserUpdateDto userUpdateDto) {
        final User user = loadById(userUpdateDto.getId());
        userMapper.update(userUpdateDto, user);
        return userRepository.save(user);
    }

    private User loadById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Can not find User with id=" + id));
    }
}
