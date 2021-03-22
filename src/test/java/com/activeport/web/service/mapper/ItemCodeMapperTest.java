package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemCodeMapperTest {
    private ItemCodeMapper itemCodeMapper;

    @BeforeEach
    public void setUp() {
        itemCodeMapper = new ItemCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemCodeMapper.fromId(null)).isNull();
    }
}
