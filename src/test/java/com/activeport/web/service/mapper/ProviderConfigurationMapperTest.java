package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProviderConfigurationMapperTest {
    private ProviderConfigurationMapper providerConfigurationMapper;

    @BeforeEach
    public void setUp() {
        providerConfigurationMapper = new ProviderConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(providerConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(providerConfigurationMapper.fromId(null)).isNull();
    }
}
