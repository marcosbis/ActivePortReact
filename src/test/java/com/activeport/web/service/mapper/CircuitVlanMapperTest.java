package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircuitVlanMapperTest {
    private CircuitVlanMapper circuitVlanMapper;

    @BeforeEach
    public void setUp() {
        circuitVlanMapper = new CircuitVlanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(circuitVlanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(circuitVlanMapper.fromId(null)).isNull();
    }
}
