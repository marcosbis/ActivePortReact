package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolicerRangeMapperTest {
    private PolicerRangeMapper policerRangeMapper;

    @BeforeEach
    public void setUp() {
        policerRangeMapper = new PolicerRangeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(policerRangeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(policerRangeMapper.fromId(null)).isNull();
    }
}
