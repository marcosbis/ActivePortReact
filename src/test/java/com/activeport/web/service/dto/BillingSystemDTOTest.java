package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BillingSystemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingSystemDTO.class);
        BillingSystemDTO billingSystemDTO1 = new BillingSystemDTO();
        billingSystemDTO1.setId(1L);
        BillingSystemDTO billingSystemDTO2 = new BillingSystemDTO();
        assertThat(billingSystemDTO1).isNotEqualTo(billingSystemDTO2);
        billingSystemDTO2.setId(billingSystemDTO1.getId());
        assertThat(billingSystemDTO1).isEqualTo(billingSystemDTO2);
        billingSystemDTO2.setId(2L);
        assertThat(billingSystemDTO1).isNotEqualTo(billingSystemDTO2);
        billingSystemDTO1.setId(null);
        assertThat(billingSystemDTO1).isNotEqualTo(billingSystemDTO2);
    }
}
