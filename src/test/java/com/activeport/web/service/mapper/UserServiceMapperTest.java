package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceMapperTest {
    private UserServiceMapper userServiceMapper;

    @BeforeEach
    public void setUp() {
        userServiceMapper = new UserServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userServiceMapper.fromId(null)).isNull();
    }
}
