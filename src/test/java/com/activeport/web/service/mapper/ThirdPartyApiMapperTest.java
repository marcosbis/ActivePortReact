package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThirdPartyApiMapperTest {
    private ThirdPartyApiMapper thirdPartyApiMapper;

    @BeforeEach
    public void setUp() {
        thirdPartyApiMapper = new ThirdPartyApiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(thirdPartyApiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(thirdPartyApiMapper.fromId(null)).isNull();
    }
}
