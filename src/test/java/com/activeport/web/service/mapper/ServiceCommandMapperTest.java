package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceCommandMapperTest {
    private ServiceCommandMapper serviceCommandMapper;

    @BeforeEach
    public void setUp() {
        serviceCommandMapper = new ServiceCommandMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(serviceCommandMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(serviceCommandMapper.fromId(null)).isNull();
    }
}
