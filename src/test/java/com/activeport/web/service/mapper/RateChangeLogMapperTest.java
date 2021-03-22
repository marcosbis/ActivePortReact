package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RateChangeLogMapperTest {
    private RateChangeLogMapper rateChangeLogMapper;

    @BeforeEach
    public void setUp() {
        rateChangeLogMapper = new RateChangeLogMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rateChangeLogMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rateChangeLogMapper.fromId(null)).isNull();
    }
}
