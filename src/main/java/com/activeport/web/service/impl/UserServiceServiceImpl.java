package com.activeport.web.service.impl;

import com.activeport.web.domain.UserService;
import com.activeport.web.repository.UserServiceRepository;
import com.activeport.web.service.UserServiceService;
import com.activeport.web.service.dto.UserServiceDTO;
import com.activeport.web.service.mapper.UserServiceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserService}.
 */
@Service
@Transactional
public class UserServiceServiceImpl implements UserServiceService {
    private final Logger log = LoggerFactory.getLogger(UserServiceServiceImpl.class);

    private final UserServiceRepository userServiceRepository;

    private final UserServiceMapper userServiceMapper;

    public UserServiceServiceImpl(UserServiceRepository userServiceRepository, UserServiceMapper userServiceMapper) {
        this.userServiceRepository = userServiceRepository;
        this.userServiceMapper = userServiceMapper;
    }

    @Override
    public UserServiceDTO save(UserServiceDTO userServiceDTO) {
        log.debug("Request to save UserService : {}", userServiceDTO);
        UserService userService = userServiceMapper.toEntity(userServiceDTO);
        userService = userServiceRepository.save(userService);
        return userServiceMapper.toDto(userService);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserServices");
        return userServiceRepository.findAll(pageable).map(userServiceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserServiceDTO> findOne(Long id) {
        log.debug("Request to get UserService : {}", id);
        return userServiceRepository.findById(id).map(userServiceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserService : {}", id);
        userServiceRepository.deleteById(id);
    }
}
