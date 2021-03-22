package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TenantMapperTest {
    private TenantMapper tenantMapper;

    @BeforeEach
    public void setUp() {
        tenantMapper = new TenantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tenantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tenantMapper.fromId(null)).isNull();
    }
}
