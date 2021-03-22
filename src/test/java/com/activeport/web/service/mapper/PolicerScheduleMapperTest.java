package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolicerScheduleMapperTest {
    private PolicerScheduleMapper policerScheduleMapper;

    @BeforeEach
    public void setUp() {
        policerScheduleMapper = new PolicerScheduleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(policerScheduleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(policerScheduleMapper.fromId(null)).isNull();
    }
}
