package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProviderLogMapperTest {
    private ProviderLogMapper providerLogMapper;

    @BeforeEach
    public void setUp() {
        providerLogMapper = new ProviderLogMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(providerLogMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(providerLogMapper.fromId(null)).isNull();
    }
}
