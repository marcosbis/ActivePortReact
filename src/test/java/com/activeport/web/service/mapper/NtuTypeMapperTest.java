package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NtuTypeMapperTest {
    private NtuTypeMapper ntuTypeMapper;

    @BeforeEach
    public void setUp() {
        ntuTypeMapper = new NtuTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ntuTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ntuTypeMapper.fromId(null)).isNull();
    }
}
