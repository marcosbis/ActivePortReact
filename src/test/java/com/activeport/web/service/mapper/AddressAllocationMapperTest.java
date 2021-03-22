package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressAllocationMapperTest {
    private AddressAllocationMapper addressAllocationMapper;

    @BeforeEach
    public void setUp() {
        addressAllocationMapper = new AddressAllocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(addressAllocationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(addressAllocationMapper.fromId(null)).isNull();
    }
}
