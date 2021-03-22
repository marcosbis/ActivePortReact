package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeviceConfigurationMapperTest {
    private DeviceConfigurationMapper deviceConfigurationMapper;

    @BeforeEach
    public void setUp() {
        deviceConfigurationMapper = new DeviceConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deviceConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deviceConfigurationMapper.fromId(null)).isNull();
    }
}
