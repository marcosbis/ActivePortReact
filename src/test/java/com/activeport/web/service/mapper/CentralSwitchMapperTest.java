package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CentralSwitchMapperTest {
    private CentralSwitchMapper centralSwitchMapper;

    @BeforeEach
    public void setUp() {
        centralSwitchMapper = new CentralSwitchMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(centralSwitchMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(centralSwitchMapper.fromId(null)).isNull();
    }
}
