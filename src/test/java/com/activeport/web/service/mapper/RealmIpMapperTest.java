package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RealmIpMapperTest {
    private RealmIpMapper realmIpMapper;

    @BeforeEach
    public void setUp() {
        realmIpMapper = new RealmIpMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(realmIpMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(realmIpMapper.fromId(null)).isNull();
    }
}
