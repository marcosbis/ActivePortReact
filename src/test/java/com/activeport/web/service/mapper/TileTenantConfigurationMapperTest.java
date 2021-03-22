package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTenantConfigurationMapperTest {
    private TileTenantConfigurationMapper tileTenantConfigurationMapper;

    @BeforeEach
    public void setUp() {
        tileTenantConfigurationMapper = new TileTenantConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tileTenantConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tileTenantConfigurationMapper.fromId(null)).isNull();
    }
}
