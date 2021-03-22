package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileConfigurationMapperTest {
    private TileConfigurationMapper tileConfigurationMapper;

    @BeforeEach
    public void setUp() {
        tileConfigurationMapper = new TileConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tileConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tileConfigurationMapper.fromId(null)).isNull();
    }
}
