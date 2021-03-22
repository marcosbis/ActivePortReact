package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityManagerMapperTest {
    private EntityManagerMapper entityManagerMapper;

    @BeforeEach
    public void setUp() {
        entityManagerMapper = new EntityManagerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(entityManagerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entityManagerMapper.fromId(null)).isNull();
    }
}
