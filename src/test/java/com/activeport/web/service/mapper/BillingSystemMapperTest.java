package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillingSystemMapperTest {
    private BillingSystemMapper billingSystemMapper;

    @BeforeEach
    public void setUp() {
        billingSystemMapper = new BillingSystemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billingSystemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billingSystemMapper.fromId(null)).isNull();
    }
}
