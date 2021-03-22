package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NtuConfigMapperTest {
    private NtuConfigMapper ntuConfigMapper;

    @BeforeEach
    public void setUp() {
        ntuConfigMapper = new NtuConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ntuConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ntuConfigMapper.fromId(null)).isNull();
    }
}
