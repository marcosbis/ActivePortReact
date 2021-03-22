package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceCodeMapperTest {
    private ServiceCodeMapper serviceCodeMapper;

    @BeforeEach
    public void setUp() {
        serviceCodeMapper = new ServiceCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(serviceCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(serviceCodeMapper.fromId(null)).isNull();
    }
}
