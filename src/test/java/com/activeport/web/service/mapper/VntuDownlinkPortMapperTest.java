package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VntuDownlinkPortMapperTest {
    private VntuDownlinkPortMapper vntuDownlinkPortMapper;

    @BeforeEach
    public void setUp() {
        vntuDownlinkPortMapper = new VntuDownlinkPortMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(vntuDownlinkPortMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(vntuDownlinkPortMapper.fromId(null)).isNull();
    }
}
