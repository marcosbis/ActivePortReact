package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TemplateConfigurationMapperTest {
    private TemplateConfigurationMapper templateConfigurationMapper;

    @BeforeEach
    public void setUp() {
        templateConfigurationMapper = new TemplateConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(templateConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(templateConfigurationMapper.fromId(null)).isNull();
    }
}
