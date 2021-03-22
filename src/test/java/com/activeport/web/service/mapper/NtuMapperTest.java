package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NtuMapperTest {
    private NtuMapper ntuMapper;

    @BeforeEach
    public void setUp() {
        ntuMapper = new NtuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ntuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ntuMapper.fromId(null)).isNull();
    }
}
