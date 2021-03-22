package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JunoCommandLogMapperTest {
    private JunoCommandLogMapper junoCommandLogMapper;

    @BeforeEach
    public void setUp() {
        junoCommandLogMapper = new JunoCommandLogMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(junoCommandLogMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(junoCommandLogMapper.fromId(null)).isNull();
    }
}
