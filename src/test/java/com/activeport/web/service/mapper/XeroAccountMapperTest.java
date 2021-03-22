package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class XeroAccountMapperTest {
    private XeroAccountMapper xeroAccountMapper;

    @BeforeEach
    public void setUp() {
        xeroAccountMapper = new XeroAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(xeroAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(xeroAccountMapper.fromId(null)).isNull();
    }
}
