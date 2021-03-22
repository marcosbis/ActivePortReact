package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceConfigurationMapperTest {
    private ServiceConfigurationMapper serviceConfigurationMapper;

    @BeforeEach
    public void setUp() {
        serviceConfigurationMapper = new ServiceConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(serviceConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(serviceConfigurationMapper.fromId(null)).isNull();
    }
}
