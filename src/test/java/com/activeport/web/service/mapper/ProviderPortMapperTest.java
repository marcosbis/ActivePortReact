package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProviderPortMapperTest {
    private ProviderPortMapper providerPortMapper;

    @BeforeEach
    public void setUp() {
        providerPortMapper = new ProviderPortMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(providerPortMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(providerPortMapper.fromId(null)).isNull();
    }
}
