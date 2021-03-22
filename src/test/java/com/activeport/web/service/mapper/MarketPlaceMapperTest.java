package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarketPlaceMapperTest {
    private MarketPlaceMapper marketPlaceMapper;

    @BeforeEach
    public void setUp() {
        marketPlaceMapper = new MarketPlaceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(marketPlaceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(marketPlaceMapper.fromId(null)).isNull();
    }
}
