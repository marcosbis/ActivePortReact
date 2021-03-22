package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserProfileMapperTest {
    private UserProfileMapper userProfileMapper;

    @BeforeEach
    public void setUp() {
        userProfileMapper = new UserProfileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userProfileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userProfileMapper.fromId(null)).isNull();
    }
}
