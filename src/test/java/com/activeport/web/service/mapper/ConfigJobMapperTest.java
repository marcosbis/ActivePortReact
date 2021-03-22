package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigJobMapperTest {
    private ConfigJobMapper configJobMapper;

    @BeforeEach
    public void setUp() {
        configJobMapper = new ConfigJobMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(configJobMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(configJobMapper.fromId(null)).isNull();
    }
}
