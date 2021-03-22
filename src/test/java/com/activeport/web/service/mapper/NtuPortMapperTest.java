package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NtuPortMapperTest {
    private NtuPortMapper ntuPortMapper;

    @BeforeEach
    public void setUp() {
        ntuPortMapper = new NtuPortMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ntuPortMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ntuPortMapper.fromId(null)).isNull();
    }
}
