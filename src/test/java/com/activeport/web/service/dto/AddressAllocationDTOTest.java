package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AddressAllocationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressAllocationDTO.class);
        AddressAllocationDTO addressAllocationDTO1 = new AddressAllocationDTO();
        addressAllocationDTO1.setId(1L);
        AddressAllocationDTO addressAllocationDTO2 = new AddressAllocationDTO();
        assertThat(addressAllocationDTO1).isNotEqualTo(addressAllocationDTO2);
        addressAllocationDTO2.setId(addressAllocationDTO1.getId());
        assertThat(addressAllocationDTO1).isEqualTo(addressAllocationDTO2);
        addressAllocationDTO2.setId(2L);
        assertThat(addressAllocationDTO1).isNotEqualTo(addressAllocationDTO2);
        addressAllocationDTO1.setId(null);
        assertThat(addressAllocationDTO1).isNotEqualTo(addressAllocationDTO2);
    }
}
