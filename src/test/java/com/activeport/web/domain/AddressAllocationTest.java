package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AddressAllocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressAllocation.class);
        AddressAllocation addressAllocation1 = new AddressAllocation();
        addressAllocation1.setId(1L);
        AddressAllocation addressAllocation2 = new AddressAllocation();
        addressAllocation2.setId(addressAllocation1.getId());
        assertThat(addressAllocation1).isEqualTo(addressAllocation2);
        addressAllocation2.setId(2L);
        assertThat(addressAllocation1).isNotEqualTo(addressAllocation2);
        addressAllocation1.setId(null);
        assertThat(addressAllocation1).isNotEqualTo(addressAllocation2);
    }
}
